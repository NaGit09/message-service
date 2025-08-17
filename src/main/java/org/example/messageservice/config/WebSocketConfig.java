package org.example.messageservice.config;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private static final Long MAX = 50000000L;
    private static final Long MIN = 25000000L;
    private final AuthChannelInterceptor authChannelInterceptor;


    // A filter request !

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(authChannelInterceptor);
    }

    // config connect websocket
    @Override
    public void configureMessageBroker(@NonNull MessageBrokerRegistry config) {
        // config prefix to destination endpoint for message broker !
        config.enableSimpleBroker("/topic", "/queue")
                .setHeartbeatValue(new long[]{MAX, MIN});
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(@NonNull StompEndpointRegistry registry) {
        // register api endpoint to websocket
        // if websocket unsupported server use socketJs replace a problem!
        // config client connect
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }
}
