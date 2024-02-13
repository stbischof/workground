/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/

package mondrian.rolap.agg;

import java.util.ArrayList;
import java.util.List;

import mondrian.olap.MondrianException;
import mondrian.olap.exceptions.SegmentCacheFailedToLoadSegmentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mondrian.spi.SegmentBody;
import mondrian.spi.SegmentCache;
import mondrian.spi.SegmentHeader;
import mondrian.util.ClassResolver;
import mondrian.util.ServiceDiscovery;

import static mondrian.olap.exceptions.SegmentCacheFailedToLoadSegmentException.segmentCacheFailedToLoadSegment;
import static mondrian.resource.MondrianResource.SegmentCacheFailedToDeleteSegment;
import static mondrian.resource.MondrianResource.SegmentCacheFailedToInstanciate;
import static mondrian.resource.MondrianResource.SegmentCacheFailedToSaveSegment;
import static mondrian.resource.MondrianResource.SegmentCacheFailedToScanSegments;
import static mondrian.resource.MondrianResource.SegmentCacheIsNotImplementingInterface;

/**
 * Utility class to interact with the {@link SegmentCache}.
 *
 * @author LBoudreau
 * @see SegmentCache
 */
public final class SegmentCacheWorker {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(SegmentCacheWorker.class);

    final SegmentCache cache;
    private final Thread cacheMgrThread;
    private final boolean supportsRichIndex;

    /**
     * Creates a worker.
     *
     * @param cache Cache managed by this worker
     * @param cacheMgrThread Thread that the cache manager actor is running on,
     *                       and which therefore should not be used for
     *                       potentially long-running calls this this cache.
     *                       Pass null if methods can be called from any thread.
     */
    public SegmentCacheWorker(SegmentCache cache, Thread cacheMgrThread) {
        this.cache = cache;
        this.cacheMgrThread = cacheMgrThread;

        // no need to call checkThread(): supportsRichIndex is a fast call
        this.supportsRichIndex = cache.supportsRichIndex();

        LOGGER.debug(
            "Segment cache initialized: "
            + cache.getClass().getName());
    }

    /**
     * Instantiates a cache. Returns null if there is no external cache defined.
     *
     * @return Cache
     */
    public static List<SegmentCache> initCache(final String segmentCache) {
        final List<SegmentCache> caches =
            new ArrayList<>();
        // First try to get the segmentcache impl class from
        // mondrian properties.
        final String cacheName = segmentCache;
        if (cacheName != null) {
            caches.add(instantiateCache(cacheName));
        }

        // There was no property set. Let's look for Java services.
        final List<Class<SegmentCache>> implementors =
            ServiceDiscovery.forClass(SegmentCache.class).getImplementor();
        if (implementors.size() > 0) {
            // The contract is to use the first implementation found.
            SegmentCache cache =
                instantiateCache(implementors.get(0).getName());
            if (cache != null) {
                caches.add(cache);
            }
        }

        // Check the SegmentCacheInjector
        // People might have sent instances into this thing.
        caches.addAll(SegmentCache.SegmentCacheInjector.getCaches());

        // Done.
        return caches;
    }

    /**
     * Instantiates a cache, given the name of the cache class.
     *
     * @param cacheName Name of class that implements the
     *     {@link mondrian.spi.SegmentCache} SPI
     *
     * @return Cache instance, or null on error
     */
    private static SegmentCache instantiateCache(String cacheName) {
        try {
            LOGGER.debug("Starting cache instance: " + cacheName);
            return ClassResolver.INSTANCE.instantiateSafe(cacheName);
        } catch (ClassCastException e) {
            throw new MondrianException(SegmentCacheIsNotImplementingInterface);
        } catch (Exception e) {
            LOGGER.error(
                    SegmentCacheFailedToInstanciate,
                e);
            throw new MondrianException(SegmentCacheFailedToInstanciate, e);
        }
    }

    /**
     * Returns a segment body corresponding to a header.
     *
     * <p>If no cache is configured or there is an error while
     * querying the cache, null is returned none the less.
     *
     * @param header Header to search.
     * @return Either a segment body object or null if there
     * was no cache configured or no segment could be found
     * for the passed header.
     */
    public SegmentBody get(SegmentHeader header) {
        checkThread();
        try {
            return cache.get(header);
        } catch (Throwable t) {
            LOGGER.error(segmentCacheFailedToLoadSegment,
                t);
            throw new SegmentCacheFailedToLoadSegmentException(t);
        }
    }

    /**
     * Places a segment in the cache. Returns true or false
     * if the operation succeeds.
     *
     * @param header A header to search for in the segment cache.
     * @param body The segment body to cache.
     */
    public void put(SegmentHeader header, SegmentBody body) {
        checkThread();
        try {
            final boolean result = cache.put(header, body);
            if (!result) {
                LOGGER.error(SegmentCacheFailedToSaveSegment);
                throw new MondrianException(SegmentCacheFailedToSaveSegment);
            }
        } catch (Throwable t) {
            LOGGER.error(
                    SegmentCacheFailedToSaveSegment,
                t);
            throw new MondrianException(SegmentCacheFailedToSaveSegment, t);
        }
    }

    /**
     * Removes a segment from the cache.
     *
     * @param header A header to remove in the segment cache.
     * @return Whether a segment was removed
     */
    public boolean remove(SegmentHeader header) {
        checkThread();
        try {
            return cache.remove(header);
        } catch (Throwable t) {
            LOGGER.error(SegmentCacheFailedToDeleteSegment,
                t);
            throw new MondrianException(SegmentCacheFailedToDeleteSegment, t);
        }
    }

    /**
     * Returns a list of segments present in the cache.
     *
     * @return List of headers in the cache
     */
    public List<SegmentHeader> getSegmentHeaders() {
        checkThread();
        try {
            return cache.getSegmentHeaders();
        } catch (Throwable t) {
            LOGGER.error("Failed to get a list of segment headers.", t);
            throw new MondrianException(
                SegmentCacheFailedToScanSegments, t);
        }
    }

    public boolean supportsRichIndex() {
        return supportsRichIndex;
    }

    public void shutdown() {
        checkThread();
        cache.tearDown();
    }

    private void checkThread() {
        assert cacheMgrThread != Thread.currentThread()
            : new StringBuilder("this method is potentially slow; you should not call it from ")
            .append("the cache manager thread, ").append(cacheMgrThread);
    }
}
