/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/

package mondrian.util;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.daanse.olap.api.Connection;
import org.eclipse.daanse.olap.api.ResultStyle;
import org.eclipse.daanse.olap.api.result.Result;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.opencube.junit5.ContextSource;
import org.opencube.junit5.context.TestingContext;
import org.opencube.junit5.dataloader.FastFoodmardDataLoader;
import org.opencube.junit5.propupdator.AppandFoodMartCatalogAsFile;

import mondrian.olap.MemoryLimitExceededException;
import mondrian.olap.MondrianProperties;
import mondrian.olap.QueryImpl;

/**
 * Test case for {@link ObjectPool}.
 *
 * @author Richard Emberson
 */
class MemoryMonitorTest {
    static final int PERCENT_100 = 100;

    protected static int convertThresholdToPercentage(
        final long threshold,
        final long maxMemory)
    {
        return (int) ((PERCENT_100 * threshold) / maxMemory);
    }


    /**
     * Get the difference between the maximum memory and the used memory
     * and divide that by 1000. This is the size of allocation chunks.
     * Keep allocating chunks until an <code>OutOfMemoryError</code> is
     * created.
     */
    public boolean causeGC(MemoryMonitor mm) {
        final int nosOfChunks = 1000;
        long maxMemory = mm.getMaxMemory();
        long usedMemory = mm.getUsedMemory();
        long delta = (maxMemory - usedMemory) / nosOfChunks;
        if (delta == 0) {
            // delta has to be greater than zero so pick 1k
            delta = 1024;
        } else if (delta > Integer.MAX_VALUE) {
            // otherwise we could get a negative value after
            // the cast to int
            delta = Integer.MAX_VALUE;
        }


        final int size = 2 * nosOfChunks;
        Object[] byteArrayHolder = new Object[size];
        for (int i = 0; i < size; i++) {
            try {
                byteArrayHolder[i] =
                        new java.lang.ref.SoftReference(new byte[(int) delta]);
            } catch (java.lang.OutOfMemoryError ex) {
                return true;
            }
        }
        // If any member is empty, then its been GCed.
        for (int i = 0; i < size; i++) {
            java.lang.ref.SoftReference ref =
                    (java.lang.ref.SoftReference) byteArrayHolder[i];
            if (ref.get() == null) {
                return true;
            }
        }
        return false;
    }

    protected boolean enabled;

    @BeforeEach
    protected void setUp() throws Exception {
        enabled = MondrianProperties.instance().MemoryMonitor.get();
    }
    @AfterEach
    protected void tearDown() throws Exception {
    }

/*
Does not work without the notify on add feature.
    void testZeroUsage() throws Exception {
        if (Util.PreJdk15 || !enabled) {
            return;
        }
        class Listener implements MemoryMonitor.Listener {
            boolean wasNotified = false;
            Listener() {
            }
            public void memoryUsageNotification(long used, long max) {
                wasNotified = true;
            }
        }
        Listener listener = new Listener();
        MemoryMonitor mm = MemoryMonitorFactory.getMemoryMonitor();
        try {
            // We use a percentage of '0' because we know that value is
            // less than or equal to the lowest JVM memory usage.
            mm.addListener(listener, 0);
            if (! listener.wasNotified) {
                fail("Listener callback not called");
            }
        } finally {
            mm.removeListener(listener);
        }
    }
*/
    @Test
    void testDeltaUsage() throws Exception {
        if (!enabled) {
            return;
        }
        class Listener implements MemoryMonitor.Listener {
            boolean wasNotified = false;
            Listener() {
            }
            @Override
			public void memoryUsageNotification(long used, long max) {
                wasNotified = true;
            }
        }
        Listener listener = new Listener();
        MemoryMonitor mm = MemoryMonitorFactory.getMemoryMonitor();
        // we will set a percentage slightly above the current
        // used level, and then allocate some objects that will
        // force a notification.
        long maxMemory = mm.getMaxMemory();
        long usedMemory = mm.getUsedMemory();
        int currentPercentage =
            convertThresholdToPercentage(usedMemory, maxMemory);
        int delta = (int) (maxMemory - usedMemory) / 10;
        int percentage = convertThresholdToPercentage(delta, maxMemory);
        try {
            byte[][] bytes = new byte[10][];
            mm.addListener(listener, percentage + currentPercentage);
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = new byte[delta];
                if (listener.wasNotified) {
                    bytes = null;
                    break;
                }
            }
            if (! listener.wasNotified) {
                Assertions.fail("Listener callback not called");
            }
        } finally {
            mm.removeListener(listener);
        }
    }
/*
Does not work without the notify on add feature.
    void testUpdatePercent() throws Exception {
        if (Util.PreJdk15 || !enabled) {
            return;
        }
        class Listener implements MemoryMonitor.Listener {
            boolean wasNotified = false;
            Listener() {
            }
            public void memoryUsageNotification(long used, long max) {
                wasNotified = true;
            }
        }
        Listener listener = new Listener();
        // we will set a percentage well above the current
        // used level, and then allocate an object, and then
        // update percentage to below new usage level.
        long maxMemory = mm.getMaxMemory();
        long usedMemory = mm.getUsedMemory();
        int currentPercentage =
            convertThresholdToPercentage(usedMemory, maxMemory);
        int delta = (int) (maxMemory - usedMemory)/10;
        int percentage = convertThresholdToPercentage(delta, maxMemory);
        try {
            mm.addListener(listener, 2 * percentage + currentPercentage);
            byte[] bytes = new byte[(int) (1.5 * delta)];
            if (listener.wasNotified) {
                fail("Listener callback was called");
            }
            mm.updateListenerThreshold(listener,
                        percentage + currentPercentage);
            if (! listener.wasNotified) {
                fail("Listener callback was not called");
            }
        } finally {
            mm.removeListener(listener);
        }
    }
*/
    private static int THRESHOLD_PERCENTAGE = 90;
    public static class TestMM extends NotificationMemoryMonitor {
        public TestMM() {
        }
        @Override
		public int getDefaultThresholdPercentage() {
            return THRESHOLD_PERCENTAGE;
        }
    }
    public static class TestMM2 extends NotificationMemoryMonitor {
        public TestMM2() {
        }
        @Override
		public int getDefaultThresholdPercentage() {
            return 98;
        }
    }

    /**
     * Run this by itself and it works across 2 orders of magnitude.
     * Run it with other tests and its hard to pick the right
     * values for the percentage and how much to allocate for it
     * to always work.
     *
     * @throws Exception
     */
	@ParameterizedTest
	@ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class )
    public void _testQuery(TestingContext context) throws Exception {
        if (!enabled) {
            return;
        }

        class Listener implements MemoryMonitor.Listener {
            boolean wasNotified = false;
            Listener() {
            }
            @Override
			public void memoryUsageNotification(long used, long max) {
                wasNotified = true;
            }
        }
        Listener listener = new Listener();
        final String queryString =
            "select \n"
            + "{ \n"
            /*
            + "[Measures].[Unit Sales], \n"
            + "[Measures].[Store Cost], \n"
            */
            + "[Measures].[Store Sales], \n"
            + "[Measures].[Sales Count], \n"
            + "[Measures].[Customer Count] \n"
            + "} \n"
            + "ON COLUMNS, \n"
            + "Crossjoin(\n"
            + "  Descendants([Store].[All Stores]), \n"
            + "  Descendants([Product].[All Products]) \n"
            + ") \n"
            + "ON ROWS \n"
            + "from [Sales]";

        List<Result> list = new ArrayList<>();
        MemoryMonitor mm = null;
        try {
            MemoryMonitorFactory.setThreadLocalClassName(
                TestMM.class.getName());
            mm = MemoryMonitorFactory.getMemoryMonitor();
            boolean b = causeGC(mm);
//System.out.println("causeGC="+b);
            long neededMemory = 5000000;
            long maxMemory = mm.getMaxMemory();
            long usedMemory = mm.getUsedMemory();
//System.out.println("maxMemory ="+maxMemory);
//System.out.println("usedMemory="+usedMemory);

            // the 10% here and 90% below are related: change one, change
            // the other.
            long tenPercentMaxMemory = maxMemory / 10;
            long level = maxMemory - tenPercentMaxMemory;
            long buf;
//System.out.println("level     ="+level);
            if (level > usedMemory) {
                buf = level - usedMemory - neededMemory;
                if (buf <= 0) {
                    buf = level - usedMemory;
                }
//int currentPercentage = convertThresholdToPercentage(level, maxMemory);
//System.out.println("currentPercentage="+currentPercentage);
                THRESHOLD_PERCENTAGE = 90;
            } else {
                buf = 0;
                double dp = (100.0 * (maxMemory - usedMemory)) / maxMemory;
                THRESHOLD_PERCENTAGE = 100 - (int) Math.ceil(dp);
            }
//System.out.println("buf       ="+buf);
//System.out.println("THRESHOLD_PERCENTAGE="+THRESHOLD_PERCENTAGE);



            byte[] bytes = new byte[(int) ((buf > 0) ? buf : 0)];

            mm.addListener(listener);
            // Check to see if we have been notified.
            // We might be notified if memory usage is already above 90%!!
            if (listener.wasNotified) {
//System.out.println("allready notified");
                return;
            }
            Connection conn = context.createConnection();

            final int MAX = 100;

//System.out.println("BEFORE");
            for (int i = 0; i < MAX; i++) {
//System.out.println("i=" +i);
                QueryImpl query = conn.parseQuery(queryString);
                query.setResultStyle(ResultStyle.MUTABLE_LIST);
                Result result = conn.execute(query);

                list.add(result);

                if (listener.wasNotified) {
                    // should never happen
                    break;
                }
            }

            fail("Memory Notification Exception did not occur");
        } catch (MemoryLimitExceededException ex) {
            if (! listener.wasNotified) {
                fail("Listener callback not called");
            }
            // pass
//System.out.println("MemoryMonitorTest: PASS");
        } finally {
            if (mm != null) {
                mm.removeListener(listener);
            }
            for (Result result : list) {
                result.close();
            }
            MemoryMonitorFactory.clearThreadLocalClassName();
//System.out.println("MemoryMonitorTest: BOTTOM");
//System.out.flush();
        }
    }
}
