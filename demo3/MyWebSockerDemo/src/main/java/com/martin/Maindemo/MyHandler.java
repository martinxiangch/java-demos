package com.martin.Maindemo;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class MyHandler implements WebSocketHandler  {

    @Override
    public void afterConnectionClosed(WebSocketSession arg0, CloseStatus arg1) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("Connection closed..."+arg0.getRemoteAddress().toString());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession arg0) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("Connection established..."+arg0.getRemoteAddress().toString());
    }

    @Override
    public void handleMessage(WebSocketSession arg0, WebSocketMessage<?> arg1) throws Exception {
        // TODO Auto-generated method stub
         try {
             System.out.println("Req: "+arg1.getPayload());
            TextMessage returnMessage = new TextMessage(arg1.getPayload()  
                      + " received at server");  
             arg0.sendMessage(returnMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }

    @Override
    public void handleTransportError(WebSocketSession arg0, Throwable arg1) throws Exception {
        // TODO Auto-generated method stub
        if(arg0.isOpen()){
            arg0.close();
        }
        System.out.println(arg1.toString());
        System.out.println("WS connection error,close...");
    }

    @Override
    public boolean supportsPartialMessages() {
        // TODO Auto-generated method stub
        return false;
    }

}
