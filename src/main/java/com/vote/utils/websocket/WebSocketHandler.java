package com.vote.utils.websocket;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * 
 * @ClassName:WebSocketHandler
 * @Description:TODO
 * @author LTT-AH-ZY https://github.com/LTT-AH-ZY-github
 * @date 2019年5月22日
 */
public class WebSocketHandler extends TextWebSocketHandler{
	 //已建立连接的用户  
    private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();  
    
    /** 
     * 处理前端发送的文本信息 
     * js调用websocket.send时候，会调用该方法 
     * @param session 
     * @param message 
     * @throws Exception 
     */  
    @Override  
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {  
        String userId = (String) session.getHandshakeAttributes().get("userId");  
        String ToUser = (String) session.getHandshakeAttributes().get("ToUser");  
        String MsgType = (String) session.getHandshakeAttributes().get("MsgType");  
        System.out.println("handleTextMessage输出这个userId是："+userId);
        if(MsgType.equals("all")) {
        	sendMessageToUsers(new TextMessage("reply msg:" + message.getPayload()));
        }else if(MsgType.equals("alone")) {
        	System.out.println("这里是单独发送，发送给用户："+ToUser);
        	sendMessageToUser(ToUser,new TextMessage("reply msg:" + message.getPayload()));
        }
        
    }
    
    /** 
     * 当新连接建立的时候，被调用 
     * 连接成功时候，会触发页面上onOpen方法 
     * 
     * @param session 
     * @throws Exception 
     */  
    @Override  
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {  
    	System.out.println("afterConnectionEstablished: \n"+session.getId());
    	System.out.println("session值"+session+(String) session.getHandshakeAttributes().toString());
        users.add(session);  
        String userId = (String) session.getHandshakeAttributes().get("userId");  
        // session.sendMessage(new TextMessage(username + " connect"));  
        //session.sendMessage(new TextMessage("hello wellcome"));  
        super.afterConnectionEstablished(session);
    }
    
    /** 
     * 当连接关闭时被调用 
     * 
     * @param session 
     * @param status 
     * @throws Exception 
     */  
    @Override  
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {  
        String userId = (String) session.getHandshakeAttributes().get("userId");  
        users.remove(session);  
        super.afterConnectionClosed(session, status);
    }
    
    /** 
     * 传输错误时调用 
     * 
     * @param session 
     * @param exception 
     * @throws Exception 
     */  
    @Override  
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {  
        String userId = (String) session.getHandshakeAttributes().get("userId");  
        if (session.isOpen()) {  
            session.close();  
        }  
        users.remove(session);  
    }
    
    /** 
     * 给所有在线用户发送消息 
     * 
     * @param message 
     */  
    public static void sendMessageToUsers(TextMessage message) {  
        for (WebSocketSession user : users) {  
            try {  
                if (user.isOpen()) {  
                    user.sendMessage(message);  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }
    
    /** 
     * 给某个用户发送消息 
     * 
     * @param userName 
     * @param message 
     */  
    public static void sendMessageToUser(String userName, TextMessage message) {  
        for (WebSocketSession user : users) {  
            if (user.getHandshakeAttributes().get("userId").equals(userName)) {  
                try {  
                    if (user.isOpen()) {
                    	System.out.println("给用户 "+userName+"发送消息");
                        user.sendMessage(message);  
                    }  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
                break;  
            }  
        }  
    }
}
