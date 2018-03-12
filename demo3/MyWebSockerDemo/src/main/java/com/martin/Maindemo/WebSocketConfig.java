package com.martin.Maindemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    
    @Autowired
    private MyHandler myhandler;
    
    @Autowired
    private MyHandshakeInterceptor myHandshake;
    
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myhandler, "/ws").addInterceptors(myHandshake).setAllowedOrigins("*");
        registry.addHandler(myhandler, "/sockjs/ws").addInterceptors(myHandshake).withSockJS();
    }

}
