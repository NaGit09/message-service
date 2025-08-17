package org.example.messageservice.config;

import com.sun.security.auth.UserPrincipal;
import io.jsonwebtoken.JwtException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.messageservice.utils.JwtUtils;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class AuthChannelInterceptor implements ChannelInterceptor {

    private final JwtUtils jwtUtils;
    private final PresenceSessionRegistry registry;

    @Override
    public Message<?> preSend(@NonNull Message<?> message, @NonNull MessageChannel channel) {

        StompHeaderAccessor accessor =
                MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        assert accessor != null;
        if (StompCommand.CONNECT == accessor.getCommand()) {

            String token = accessor.getFirstNativeHeader("token");

            if (token == null || jwtUtils.validateToken(token)) {
                    throw new JwtException("Validate fail with token :"+ token);
            }

            String  userId = jwtUtils.getUserIdFromToken(token);
            accessor.setUser(new UserPrincipal(userId));

            log.info("✅ User CONNECTED:{}", userId);

            registry.bind(accessor.getSessionId(), userId);
        } else if (StompCommand.DISCONNECT == accessor.getCommand()) {

            registry.unbind(accessor.getSessionId());

            log.info("❌ User DISCONNECTED: sessionId={}", accessor.getSessionId());

        }
        return message;
    }
}
