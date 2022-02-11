package com.bu.firstdoc.ws;

import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author haizhuangbu
 * @date 3:31 下午 2022/2/10
 * @mark SessionManager
 */
public class SessionManager {

    private static final Map<String, WebSocketSession> sessions = new HashMap<>();

    private SessionManager() {
    }

    public static WebSocketSession put(String key,WebSocketSession webSocketSession) {
        return sessions.put(key,webSocketSession);
    }

    public static Set<String> sessions() {
        return sessions.keySet();
    }

    public static WebSocketSession get(String key) {

        return sessions.get(key);
    }

    public static WebSocketSession remove(String key) {
        return sessions.remove(key);
    }
}
