/*
 * This software is subject to the terms of the Eclipse Public License v1.0
 * Agreement, available at the following URL:
 * http://www.eclipse.org/legal/epl-v10.html.
 * You must accept the terms of that agreement to use this software.
 *
 * Copyright (c) 2021 Sergei Semenkov.  All rights reserved.
 */

package mondrian.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import org.olap4j.OlapException;
import org.olap4j.Scenario;

import mondrian.olap.MondrianServer;
import mondrian.rolap.RolapSchema;
import mondrian.rolap.RolapSchemaPool;
import mondrian.rolap.agg.SegmentCacheManager;
import mondrian.rolap.agg.SegmentCacheWorker;

public class Session
{
    static final Map<String, Session> sessions = new HashMap<>();
    public static final String SESSION_WITH_ID = "Session with id \"";

    static java.util.Timer timer = new Timer(true);
    static java.util.TimerTask timerTask = new java.util.TimerTask() {
        @Override
		public void run() {
            List<String> toRemove = new ArrayList<>();
            for(Map.Entry<String, Session> entry : sessions.entrySet()) {
                Session session = entry.getValue();
                java.time.Duration duration = java.time.Duration.between(
                        session.checkInTime,
                        java.time.LocalDateTime.now());
                if(duration.getSeconds() >
                        mondrian.olap.MondrianProperties.instance().IdleOrphanSessionTimeout.get()) {
                    toRemove.add(entry.getKey());
                }
            }
            for(String sessionIdInner : toRemove) {
                closeInternal(sessionIdInner);
            }
        }
    };
    static
    {
        timer.scheduleAtFixedRate(timerTask, 0, 60*1000l);
    }

    String sessionId;
    Session(String sessionId)
    {
        this.sessionId = sessionId;
    }
    public static Session create(String sessionId)
    {
        if(sessions.containsKey(sessionId)) {
            throw new mondrian.xmla.XmlaException(
                    "XMLAnalysisError",
                    "0xc10c000a",
                    new StringBuilder(SESSION_WITH_ID).append(sessionId).append("\" already exists.").toString(),
                    new OlapException(new StringBuilder(SESSION_WITH_ID).append(sessionId)
                        .append("\" already exists.").toString())
            );
        }

        Session session = new Session(sessionId);

        sessions.put(sessionId, session);
        session.checkInTime = java.time.LocalDateTime.now();

        return session;
    }

    public static Session getWithoutCheck(String sessionId)
    {
        return sessions.get(sessionId);
    }

    public static Session get(String sessionId)
    {
        if(!sessions.containsKey(sessionId)) {
            throw new mondrian.xmla.XmlaException(
                    "XMLAnalysisError",
                    "0xc10c000a",
                    new StringBuilder(SESSION_WITH_ID).append(sessionId).append("\" does not exists.").toString(),
                    new OlapException(new StringBuilder(SESSION_WITH_ID)
                        .append(sessionId).append("\" does not exist").toString())
            );
        }
        return sessions.get(sessionId);
    }

    java.time.LocalDateTime checkInTime = null;

    public static void checkIn(String sessionId)
    {
        Session session = get(sessionId);
        session.checkInTime = java.time.LocalDateTime.now();
    }

    static void closeInternal(String sessionId)
    {
        List<RolapSchema> rolapSchemas = RolapSchemaPool.instance().getRolapSchemas();
        for(RolapSchema rolapSchema: rolapSchemas) {
            final String rolapSchemaSessionId = rolapSchema.getInternalConnection().getConnectInfo().get("sessionId");
            if(sessionId.equals(rolapSchemaSessionId)) {
                RolapSchemaPool.instance().remove(rolapSchema);
            }
        }

        Session session = sessions.get(sessionId);
        shutdownCacheManager(session);

        sessions.remove(sessionId);
    }

    static void shutdownCacheManager(Session session) {
        if(session.segmentCacheManager != null) {
            // Send a shutdown command and wait for it to return.
            session.segmentCacheManager.shutdown();
            // Now we can cleanup.
            for (SegmentCacheWorker worker : session.segmentCacheManager.segmentCacheWorkers) {
                worker.shutdown();
            }
        }
    }

    public static void shutdown()
    {
        for(Map.Entry<String, Session> entry : sessions.entrySet()) {
            shutdownCacheManager(entry.getValue());
        }
    }

    public static void close(String sessionId)
    {
        Session.get(sessionId);

        closeInternal(sessionId);
    }

    private SegmentCacheManager segmentCacheManager = null;

    public SegmentCacheManager getOrCreateSegmentCacheManager(MondrianServer server){
        if(this.segmentCacheManager == null) {
            this.segmentCacheManager = new SegmentCacheManager(server);
        }
        return this.segmentCacheManager;
    }

    private Scenario scenario = null;
    private org.eclipse.daanse.olap.api.result.Scenario scenarioNew = null;

    public void setScenario(org.eclipse.daanse.olap.api.result.Scenario scenario) {
        this.scenarioNew = scenario;
    }

    public void setScenarioNew(Scenario scenario) {
        this.scenario = scenario;
    }

    public Scenario getScenario() {
        return this.scenario;
    }
}
