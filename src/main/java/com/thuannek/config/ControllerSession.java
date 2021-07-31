package com.thuannek.config;

import org.springframework.web.socket.WebSocketSession;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ControllerSession {
    private static List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    public synchronized static List<WebSocketSession> getSessions() {
        return sessions;
    }

    public synchronized static void setSessions(WebSocketSession session){
        sessions.add(session);
    }

    public synchronized static void removeSessions(WebSocketSession session){
        sessions.remove(session);
    }
}
