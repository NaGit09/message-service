package org.example.messageservice.config;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PresenceSessionRegistry {
    private final Map<String,String> sessionToUser = new ConcurrentHashMap<>();
    private final Map<String, Set<String>> userToSessions = new ConcurrentHashMap<>();

    public void bind(String sessionId, String userId){
        sessionToUser.put(sessionId, userId);
        userToSessions.computeIfAbsent(userId, k -> ConcurrentHashMap.newKeySet()).add(sessionId);
    }
    public String unbind(String sessionId){
        String userId = sessionToUser.remove(sessionId);
        if (userId != null) {
            Set<String> sessions = userToSessions.getOrDefault(userId, Collections.emptySet());
            sessions.remove(sessionId);
            if (sessions.isEmpty()) userToSessions.remove(userId);
        }
        return userId;
    }
    public boolean isOnline(String userId){ return userToSessions.containsKey(userId); }
}
