/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2001-2005 Julian Hyde and others
// Copyright (C) 2005-2019 Hitachi Vantara and others
// All Rights Reserved.
*/
package mondrian.rolap;

import static mondrian.rolap.RolapConnectionProperties.JdbcConnectionUuid;
import static mondrian.rolap.RolapConnectionProperties.PinSchemaTimeout;
import static mondrian.rolap.RolapConnectionProperties.UseSchemaPool;

import java.io.IOException;
import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.sql.DataSource;

import org.eclipse.daanse.olap.api.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mondrian.olap.Util;
import mondrian.resource.MondrianResource;
import mondrian.rolap.aggmatcher.JdbcSchema;
import mondrian.spi.DynamicSchemaProcessor;
import mondrian.util.ByteString;
import mondrian.util.ClassResolver;
import mondrian.util.ExpiringReference;

/**
 * A collection of schemas, identified by their connection properties
 * (catalog name, JDBC URL, and so forth).
 *
 * <p>To lookup a schema, call
 * <code>RolapSchemaPool.{@link #instance}().{@link #get}</code>.</p>
 */
public class RolapSchemaPool {
    static final Logger LOGGER = LoggerFactory.getLogger(RolapSchemaPool.class);

    private static final RolapSchemaPool INSTANCE = new RolapSchemaPool();

    private final Map<SchemaKey, ExpiringReference<RolapSchema>>
        mapKeyToSchema =
            new HashMap<>();

    // REVIEW: This map is now considered unsafe. If two schemas have identical
    // metadata but a different underlying database connection, we should not
    // share a cache. Since SchemaContentKey is now a hash of the schema
    // definition, this field can probably be removed.
    private final Map<ByteString, ExpiringReference<RolapSchema>>
        mapMd5ToSchema =
            new HashMap<>();

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private RolapSchemaPool() {
    }

    public static RolapSchemaPool instance() {
        return INSTANCE;
    }

    RolapSchema get(
        final String catalogUrl,
        final String connectionKey,
        final String jdbcUser,
        final String dataSourceStr,
        final Util.PropertyList connectInfo)
    {
        return get(
            catalogUrl,
            connectionKey,
            jdbcUser,
            dataSourceStr,
            null,
            connectInfo);
    }

    RolapSchema get(
        final String catalogUrl,
        final Context context,
        final Util.PropertyList connectInfo)
    {
        return get(
            catalogUrl,
            null,
            null,
            null,
            context,
            connectInfo);
    }

    public RolapSchema get(
        final String catalogUrl,
        final String connectionKey,
        final String jdbcUser,
        final String dataSourceStr,
        final Context context,
        final Util.PropertyList connectInfo)
    {
        final String connectionUuidStr = connectInfo.get(
            JdbcConnectionUuid.name());
        final boolean useSchemaPool =
            Boolean.parseBoolean(
                connectInfo.get(UseSchemaPool.name(), "true"));
        final String pinSchemaTimeout =
            connectInfo.get(PinSchemaTimeout.name(), "-1s");
        final boolean useContentChecksum =
            Boolean.parseBoolean(
                connectInfo.get(
                    RolapConnectionProperties.UseContentChecksum.name()));
        final String sessionId = connectInfo.get(
                "sessionId");
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(
                new StringBuilder("get: catalog=").append(catalogUrl)
                    .append(", connectionKey=").append(connectionKey)
                    .append(", jdbcUser=").append(jdbcUser)
                    .append(", dataSourceStr=").append(dataSourceStr)
                    .append(", dataSource=").append((context == null ? "" : context.getDataSource()))
                    .append(", jdbcConnectionUuid=").append(connectionUuidStr)
                    .append(", useSchemaPool=").append(useSchemaPool)
                    .append(", useContentChecksum=").append(useContentChecksum)
                    .append(", map-size=").append(mapKeyToSchema.size())
                    .append(", md5-map-size=").append(mapMd5ToSchema.size()).toString());
        }
        final ConnectionKey connectionKey1 =
            ConnectionKey.create(
                connectionUuidStr,
                context == null ? null : context.getDataSource(),
                catalogUrl,
                connectionKey,
                jdbcUser,
                dataSourceStr,
                sessionId);

        final String catalogStr = getSchemaContent(connectInfo, catalogUrl);
        final SchemaContentKey schemaContentKey =
            SchemaContentKey.create(connectInfo, catalogUrl, catalogStr);
        final SchemaKey key =
            new SchemaKey(
                schemaContentKey,
                connectionKey1);

        // Use the schema pool unless "UseSchemaPool" is explicitly false.
        if (!useSchemaPool) {
            RolapSchema schema = createRolapSchema(
                catalogUrl, context, connectInfo, catalogStr, key, null);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(
                    "create (no pool): schema-name={}, schema-id={}",
                    schema.getName(), Integer.toHexString(System.identityHashCode(schema)));
            }
            return schema;
        }

        if (useContentChecksum) {
            return getByChecksum(
                catalogUrl,
                context,
                connectInfo,
                pinSchemaTimeout,
                catalogStr,
                key);
        }
        return getByKey(
            catalogUrl, context, connectInfo, pinSchemaTimeout,
            catalogStr, key);
    }

    private <T> RolapSchema lookUp(
        Map<T, ExpiringReference<RolapSchema>> map,
        T key,
        String pinSchemaTimeout)
    {
        lock.readLock().lock();
        try {
            ExpiringReference<RolapSchema> ref = map.get(key);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("get(key={}) returned {}", key, toString(ref));
            }

            if (ref != null) {
                RolapSchema schema = ref.get(pinSchemaTimeout);
                if (schema != null) {
                    return schema;
                }
            }
        } finally {
            lock.readLock().unlock();
        }

        return null;
    }

    private RolapSchema getByKey(
        String catalogUrl,
        Context context,
        Util.PropertyList connectInfo,
        String pinSchemaTimeout,
        String catalogStr,
        SchemaKey key)
    {
        RolapSchema schema = lookUp(mapKeyToSchema, key, pinSchemaTimeout);
        if (schema != null) {
            return schema;
        }

        lock.writeLock().lock();
        try {
            // We need to check once again, now under
            // write lock's protection, because it is possible,
            // that another thread has already replaced old ref
            // with a new one, having the same key.
            // If the condition were not checked, then this thread
            // would remove the newborn schema
            ExpiringReference<RolapSchema> ref = mapKeyToSchema.get(key);
            if (ref != null) {
                schema = ref.get(pinSchemaTimeout);
                if (schema == null) {
                    mapKeyToSchema.remove(key);
                } else {
                    return schema;
                }
            }

            schema = createRolapSchema(
                catalogUrl, context, connectInfo, catalogStr, key, null);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("create: " + schema);
            }
            putSchema(schema, null, pinSchemaTimeout);
            return schema;
        } finally {
            lock.writeLock().unlock();
        }
    }

    private RolapSchema getByChecksum(
        String catalogUrl,
        Context context,
        Util.PropertyList connectInfo,
        String pinSchemaTimeout,
        String catalogStr,
        SchemaKey key)
    {
        final ByteString sha512Bytes = new ByteString(Util.digestSHA(catalogStr));
        RolapSchema schema = lookUp(mapMd5ToSchema, sha512Bytes, pinSchemaTimeout);
        if (schema != null) {
            return schema;
        }

        lock.writeLock().lock();
        try {
            // The motivation for repeating lookup attempt is the same
            // as described in getByKey()
            ExpiringReference<RolapSchema> ref = mapMd5ToSchema.get(sha512Bytes);
            if (ref != null) {
                schema = ref.get(pinSchemaTimeout);
                if (schema == null) {
                    // clear out the reference since schema is null
                    mapKeyToSchema.remove(key);
                    mapMd5ToSchema.remove(sha512Bytes);
                } else {
                    // someone has updated the schema for us
                    return schema;
                }
            }

            schema = createRolapSchema(
                catalogUrl, context, connectInfo, catalogStr,
                key, sha512Bytes);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(
                    "create: schema-name={}, schema-id={}",
                    schema.getName(), System.identityHashCode(schema));
            }
            putSchema(schema, sha512Bytes, pinSchemaTimeout);
            return schema;
        } finally {
            lock.writeLock().unlock();
        }
    }

    // is extracted and made package-local for testing purposes
    RolapSchema createRolapSchema(
        String catalogUrl,
        Context context,
        Util.PropertyList connectInfo,
        String catalogStr,
        SchemaKey key,
        ByteString sha512Bytes)
    {
        return new RolapSchema(
            key,
            sha512Bytes,
            catalogUrl,
            catalogStr,
            connectInfo,
            context);
    }

    /**
     * Adds <tt>schema</tt> to the pool.
     * <b>Attention!</b> This method is not doing any synchronization
     * internally and relies on the assumption that it is invoked
     * inside a critical section
     * @param schema        schema to be stored
     * @param md5Bytes      md5 hash, can be <tt>null</tt>
     * @param pinTimeout    timeout mark
     */
    private void putSchema(
        final RolapSchema schema,
        final ByteString md5Bytes,
        final String pinTimeout)
    {
        final ExpiringReference<RolapSchema> reference =
            new ExpiringReference<>(
                schema, pinTimeout);
        if (md5Bytes != null) {
            mapMd5ToSchema.put(md5Bytes, reference);
        }
        mapKeyToSchema.put(schema.key, reference);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(
                "put: schema={}, key={}, checksum={}, map-size={}, md5-map-size={}",
                schema,
                schema.key,
                md5Bytes,
                mapKeyToSchema.size(),
                mapMd5ToSchema.size());
        }
    }

    private static String getSchemaContent(
        final Util.PropertyList connectInfo,
        final String catalogUrl)
    {
        // We will return the first of the following:
        //  1. CatalogContent property if set
        //  2. DynamicSchemaProcessor#processSchema if set
        //  3. Util.readVirtualFileAsString(catalogUrl)

        // check for a DynamicSchemaProcessor
        String dynProcName = connectInfo.get( RolapConnectionProperties.DynamicSchemaProcessor.name() );
        String catalogStr = connectInfo.get( RolapConnectionProperties.CatalogContent.name() );

        if (Util.isEmpty(catalogStr)) {
            if (Util.isEmpty(catalogUrl)) {
                throw MondrianResource.instance().ConnectStringMandatoryProperties.ex(
                        RolapConnectionProperties.Catalog.name(),
                        RolapConnectionProperties.CatalogContent.name());
            }

            if (!Util.isEmpty(dynProcName)) {
                catalogStr = processDynamicSchema( dynProcName, catalogUrl, null, connectInfo);
            }

            if (Util.isEmpty(catalogStr)) {
                // read schema from file
                try {
                    catalogStr = Util.readVirtualFileAsString(catalogUrl);
                } catch (IOException e) {
                    throw Util.newError( e,"loading schema from url " + catalogUrl);
                }
            }
        }
        else {
            if (!Util.isEmpty(dynProcName)) {
                catalogStr = processDynamicSchema( dynProcName, null, catalogStr, connectInfo );
            }
        }

        return catalogStr;
    }

    private static String processDynamicSchema(
      final String dynProcName,
      final String catalogUrl,
      final String catalogStr,
      final Util.PropertyList connectInfo )
    {
        if (RolapSchema.LOGGER.isDebugEnabled()) {
            RolapSchema.LOGGER.debug(
                new StringBuilder("Pool.get: create schema \"").append(catalogUrl)
                .append("\" using dynamic processor").toString());
        }
        try {
            final DynamicSchemaProcessor dynProc =
                ClassResolver.INSTANCE.instantiateSafe(dynProcName);

            if( catalogUrl != null ) {
                return dynProc.processSchema(catalogUrl, connectInfo);
            }

            if( catalogStr != null ) {
                return dynProc.processCatalog( catalogStr, connectInfo);
            }

            throw new IllegalArgumentException( "At least one of catalogUrl and catalogStr should not be null" );

        } catch (Exception e) {
            throw Util.newError(
                e,
                "loading DynamicSchemaProcessor " + dynProcName);
        }
    }

    void remove(
        final String catalogUrl,
        final String connectionKey,
        final String jdbcUser,
        final String dataSourceStr)
    {
        final SchemaContentKey schemaContentKey =
            SchemaContentKey.create(
                new Util.PropertyList(),
                catalogUrl,
                null);
        final ConnectionKey connectionUuid =
            ConnectionKey.create(
                null,
                null,
                catalogUrl,
                connectionKey,
                jdbcUser,
                dataSourceStr);
        final SchemaKey key =
            new SchemaKey(schemaContentKey, connectionUuid);
        if (RolapSchema.LOGGER.isDebugEnabled()) {
            RolapSchema.LOGGER.debug(
                new StringBuilder("Pool.remove: schema \"").append(catalogUrl)
                .append("\" and datasource string \"").append(dataSourceStr).append("\"").toString());
        }
        remove(key);
    }

    void remove(
        final String catalogUrl,
        final DataSource dataSource)
    {
        final SchemaContentKey schemaContentKey =
            SchemaContentKey.create(
                new Util.PropertyList(),
                catalogUrl,
                null);
        final ConnectionKey connectionKey =
            ConnectionKey.create(
                null,
                dataSource,
                catalogUrl,
                null,
                null,
                null);
        final SchemaKey key =
            new SchemaKey(schemaContentKey, connectionKey);
        if (RolapSchema.LOGGER.isDebugEnabled()) {
            RolapSchema.LOGGER.debug(
                new StringBuilder("Pool.remove: schema \"").append(catalogUrl)
                .append("\" and datasource object").toString());
        }
        remove(key);
    }

    public void remove(RolapSchema schema) {
        if (schema != null) {
            if (RolapSchema.LOGGER.isDebugEnabled()) {
                RolapSchema.LOGGER.debug(
                    new StringBuilder("Pool.remove: schema \"").append(schema.getName())
                    .append("\" and datasource object").toString());
            }
            remove(schema.key);
        }
    }

    private void remove(SchemaKey key) {
        lock.writeLock().lock();
        RolapSchema schema = null;
        try {
            Reference<RolapSchema> ref = mapKeyToSchema.get(key);
            if (ref != null) {
                schema = ref.get();
                if (schema != null) {
                    mapMd5ToSchema.remove(schema.getChecksum());
                }
            }
            mapKeyToSchema.remove(key);
        } finally {
            lock.writeLock().unlock();
        }

        if (schema != null) {
            schema.finalCleanUp();
        }
    }

    public void clear() {
        lock.writeLock().lock();
        List<RolapSchema> schemas = new ArrayList<>();
        try {
            if (RolapSchema.LOGGER.isDebugEnabled()) {
                RolapSchema.LOGGER.debug(
                    "Pool.clear: clearing all RolapSchemas");
            }

            for (Reference<RolapSchema> ref : mapKeyToSchema.values()) {
                if (ref != null) {
                    RolapSchema schema = ref.get();
                    if (schema != null) {
                        schemas.add(schema);
                    }
                }
            }
            mapKeyToSchema.clear();
            mapMd5ToSchema.clear();
        } finally {
            lock.writeLock().unlock();
        }

        for (RolapSchema schema : schemas) {
            schema.finalCleanUp();
        }
        JdbcSchema.clearAllDBs();
    }

    /**
     * Returns a list of schemas in this pool.
     *
     * @return List of schemas in this pool
     */
    public List<RolapSchema> getRolapSchemas() {
        lock.readLock().lock();
        try {
            List<RolapSchema> list = new ArrayList<>();
            for (RolapSchema schema : Util.GcIterator
                .over(mapKeyToSchema.values()))
            {
                list.add(schema);
            }
            return list;
        } finally {
            lock.readLock().unlock();
        }
    }

    boolean contains(RolapSchema rolapSchema) {
        lock.readLock().lock();
        try {
            return mapKeyToSchema.containsKey(rolapSchema.key);
        } finally {
            lock.readLock().unlock();
        }
    }

    private static <T> String toString(Reference<T> ref) {
        if (ref == null) {
            return "null";
        } else {
            T t = ref.get();
            if (t == null) {
                return "ref(null)";
            } else {
                return new StringBuilder("ref(").append(t)
                    .append(", id=").append(Integer.toHexString(System.identityHashCode(t)))
                    .append(")").toString();
            }
        }
    }
}
