package org.example.messageservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class PresenceService {
    private final Map<String , String > sessionUserMap  = new ConcurrentHashMap<>();

    public void addSession(String sessionId, String userId) {
        sessionUserMap.put(sessionId, userId);
        log.info("User {} connected with session {}", userId, sessionId);
    }
    public void removeSession(String sessionId) {
        sessionUserMap.remove(sessionId);

    }

    public Optional<String> getUserBySession(String sessionId) {
        return Optional.ofNullable(sessionUserMap.get(sessionId));
    }

    public boolean isUserOnline(String userId) {
        return sessionUserMap.containsValue(userId);
    }
}

