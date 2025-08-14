package org.example.messageservice.config;

import lombok.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker( @NonNull  MessageBrokerRegistry config) {
        // config prefix to destination endpoint for message broker !
        config.enableSimpleBroker("/topic","/queue");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(@NonNull  StompEndpointRegistry registry) {
        // register api endpoint to websocket
        // if websocket unsupported server use socketJs replace a problem!
        // config client connect
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }
}
