/*
 * Copyright (c) 2023 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   SmartCity Jena - initial
 *   Stefan Bischof (bipolis.org) - initial
 */
package org.eclipse.daanse.olap.xmla.bridge.execute;

import org.eclipse.daanse.olap.api.result.Scenario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Session
{
    private static Map<String, Session> sessions = new HashMap<String, Session>();
    private java.time.LocalDateTime checkInTime = null;
    private String sessionId;

    static Timer timer = new Timer(true);
    static TimerTask timerTask = new TimerTask() {
        public void run() {
            List<String> toRemove = new ArrayList<String>();
            for(Map.Entry<String, Session> entry : sessions.entrySet()) {
                Session session = entry.getValue();
                java.time.Duration duration = java.time.Duration.between(
                        session.checkInTime,
                        java.time.LocalDateTime.now());
                //TODO use configuration parameter 3600
                if(duration.getSeconds() >
                        3600) {
                    toRemove.add(entry.getKey());
                }
            }
            for(String sessionId : toRemove) {
                close(sessionId);
            }
        }
    };
    static
    {
        timer.scheduleAtFixedRate(timerTask, 0, 60*1000);
    }


    private Session(String sessionId)
    {
        this.sessionId = sessionId;
    }

    public static Session create(String sessionId)
    {
        if(sessions.containsKey(sessionId)) {
            throw new RuntimeException("Session with id \"" + sessionId + "\" already exists.");
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


    public static Session get(String sessionId) {
        if(!sessions.containsKey(sessionId)) {
            throw new RuntimeException("Session with id \"" + sessionId + "\" does not exist");
        }
        return sessions.get(sessionId);
    }


    public static void close(String sessionId)
    {
        sessions.remove(sessionId);
    }

    private Scenario scenario = null;

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public Scenario getScenario() {
        return this.scenario;
    }
}
