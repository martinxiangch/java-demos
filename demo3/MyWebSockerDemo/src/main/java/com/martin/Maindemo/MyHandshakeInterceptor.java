package com.martin.Maindemo;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Component
public class MyHandshakeInterceptor  extends HttpSessionHandshakeInterceptor{
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        // TODO Auto-generated method stub
        System.out.println("After handshake "+request.getRemoteAddress().toString());
        super.afterHandshake(request, response, wsHandler, ex);
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handler, Map<String, Object> map) throws Exception {
        // TODO Auto-generated method stub
        
        System.out.println("Before handshake "+request.getRemoteAddress().toString()+", URL:"+request.getURI());
        return super.beforeHandshake(request, response, handler, map);
    }
}
