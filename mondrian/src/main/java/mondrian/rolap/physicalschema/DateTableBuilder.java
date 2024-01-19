/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2012-2012 Pentaho
// All Rights Reserved.
*/
package mondrian.rolap.physicalschema;

import mondrian.olap.MondrianException;
import mondrian.olap.Util;
import mondrian.rolap.RolapConnection;
import mondrian.rolap.RolapUtil;
import mondrian.rolap.SqlStatement;
import mondrian.server.Execution;
import mondrian.server.Locus;
import org.eclipse.daanse.db.dialect.api.Dialect;
import org.eclipse.daanse.olap.api.Context;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingColumnDef;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.TypeEnum;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Tests for, and if necessary creates and populates, a date dimension table.
 *
 * @author jhyde
*/
public class DateTableBuilder implements PhysTable.Hook {
    private final Map<String, TimeColumnRole.Struct> columnRoleMap;
    private final List<MappingColumnDef> xmlColumnDefs;
    private final Instant startDate;
    private final Instant endDate;

    DateTableBuilder(
        Map<String, TimeColumnRole.Struct> columnRoleMap,
        List<MappingColumnDef> xmlColumnDefs,
        Instant startDate,
        Instant endDate)
    {
        this.columnRoleMap = columnRoleMap;
        this.xmlColumnDefs = xmlColumnDefs;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean apply(
        PhysTable table,
        RolapConnection connection)
    {
        boolean tableExists = tableExists(connection, table);
        if (tableExists) {
            return false;
        }

        final DataSource dataSource = connection.getDataSource();
        final Dialect dialect = connection.getSchema().getDialect();
        String ddl = generateDdl(table, dialect);
        createTable(dataSource, ddl);
        String insert =
            generateInsert(
                table.schemaName, table.name, dialect, xmlColumnDefs.size());
        populateTable(table, dataSource, insert);
        return true;
    }

    private String generateInsert(
        String schemaName, String name, Dialect dialect, int columnCount)
    {
        StringBuilder buf = new StringBuilder();
        buf.append("INSERT INTO ");
        dialect.quoteIdentifier(buf, schemaName, name);
        buf.append(" VALUES (");
        for (int i = 0; i < columnCount; i++) {
            if (i > 0) {
                buf.append(", ");
            }
            buf.append("?");
        }
        buf.append(")");
        return buf.toString();
    }

    private void createTable(DataSource dataSource, String ddl) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(ddl);
        } catch (SQLException e) {
            throw Util.newError(
                e,
                "Error while creating date dimension table; DDL=[" + ddl + "]");
        } finally {
            Util.close(null, statement, connection);
        }
    }

    private void populateTable(
        PhysTable table,
        DataSource dataSource,
        String insert)
    {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(insert);
            List<TimeColumnRole.Struct> roles =
                new ArrayList<TimeColumnRole.Struct>();
            for (MappingColumnDef xmlColumnDef : xmlColumnDefs) {
                roles.add(columnRoleMap.get(xmlColumnDef.name()));
            }
            populate(roles, pstmt, startDate, endDate, Locale.getDefault());
        } catch (SQLException e) {
            throw Util.newError(
                e,
                "Error while creating date dimension table; DDL=[" + insert
                + "]");
        } finally {
            Util.close(null, pstmt, connection);
        }
    }

    public static void populate(
        List<TimeColumnRole.Struct> roles,
        PreparedStatement pstmt,
        Instant startDate,
        Instant endDate,
        Locale locale)
        throws SQLException
    {
        Object[] states = new Object[roles.size() + 1];
        for (int j = 0; j < roles.size(); j++) {
            states[j + 1] = roles.get(j).initialize(locale);
        }
        //Calendar calendar = Calendar.getInstance();
        //calendar.setTime(startDate);
        while (startDate.compareTo(endDate) < 0) {
            for (int i = 0, rolesSize = roles.size(); i < rolesSize; i++) {
                TimeColumnRole.Struct role = roles.get(i);
                role.bind(states, i + 1, startDate, pstmt);
            }
            pstmt.execute();
            startDate.plus(1, ChronoUnit.DAYS);
        }
    }

    private String generateDdl(
        PhysTable table, Dialect dialect)
    {
        StringBuilder buf = new StringBuilder();
        buf.append("CREATE TABLE ");
        dialect.quoteIdentifier(buf, table.schemaName, table.name);
        buf.append(" (\n");
        int i = 0;
        PhysKey primaryKey = table.keysByName.get("primary");
        String singletonKey;
        if (primaryKey != null
            && primaryKey.columnList.size() == 1)
        {
            singletonKey = primaryKey.columnList.get(0).name;
        } else {
            singletonKey = null;
        }
        for (MappingColumnDef xmlColumnDef : xmlColumnDefs) {
            if (i++ > 0) {
                buf.append(",\n");
            }
            buf.append("    ");
            dialect.quoteIdentifier(buf, xmlColumnDef.name());
            buf.append(" ");
            buf.append(
                datatypeToString(
                    xmlColumnDef.type(),
                    20));
            buf.append(" NOT NULL");
            if (xmlColumnDef.name().equals(singletonKey)) {
                buf.append(" PRIMARY KEY");
            }
        }
        buf.append(")");
        return buf.toString();
    }

    public String datatypeToString(
        TypeEnum datatype, int precision)
    {
        switch (datatype) {
            case STRING:
                return "VARCHAR(" + precision + ")";
            default:
                return datatype.name();
        }
    }

    private boolean tableExists(
        RolapConnection connection,
        PhysTable table)
    {
        //final DataSource dataSource = connection.getDataSource();
        final Context context = connection.getContext();
        final Dialect dialect = connection.getSchema().getDialect();

        StringBuilder buf = new StringBuilder();
        buf.append("select count(*) from ");
        dialect.quoteIdentifier(buf, table.schemaName, table.name);
        int rowCount;
        SqlStatement sqlStatement = null;
        try {
            sqlStatement = RolapUtil.executeQuery(
                context,
                buf.toString(),
                new Locus(
                    new Execution(connection.getInternalStatement(), 0),
                    "Auto-create date table: existence check",
                    null));
            ResultSet resultSet = sqlStatement.getResultSet();
            if (resultSet.next()) {
                rowCount = resultSet.getInt(1);
            }
            resultSet.close();
            return true;
        } catch (SQLException e) {
            throw Util.newError(e, "While validating auto-create table");
        } catch (MondrianException e) {
            if (e.getCause() instanceof SQLException) {
                // There was an error. Assume that this is because the
                // table does not exist.
                return false;
            }
            throw e;
        } finally {
            if (sqlStatement != null) {
                sqlStatement.close();
            }
        }
    }
}

// End DateTableBuilder.java
