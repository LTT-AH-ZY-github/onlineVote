package com.vote.utils.websocket;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * 
 * @ClassName:WebSocketHandshakeInterceptor
 * @Description:TODO
 * @author LTT-AH-ZY https://github.com/LTT-AH-ZY-github
 * @date 2019年5月22日
 */
public class WebSocketHandshakeInterceptor implements HandshakeInterceptor{

	public void afterHandshake(ServerHttpRequest arg0, ServerHttpResponse arg1, WebSocketHandler arg2, Exception arg3) {
		System.out.println("After Handshake");  
	}

	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wshandler,
			Map<String, Object> attributes) throws Exception {
		//获取请求参数，首先我们要获取HttpServletRequest对象才能获取请求参数；当ServerHttpRequset的层次结构打开后其子类可以获取到我们想要的http对象，那么就简单了。
		//我这里是把获取的请求数据绑定到session的map对象中（attributes）
		HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
		String id = servletRequest.getSession().getId();
		System.out.println("beforeHandshake: \n"+id);
		String userId = servletRequest.getParameter("userId");
		String MsgType = servletRequest.getParameter("MsgType");
		String ToUser = servletRequest.getParameter("ToUser");
		attributes.put("userId",userId);//后面可以加一个请求类型，同样put进attributes中，用来判断是群发还是单独发送
		attributes.put("MsgType",MsgType);
		attributes.put("ToUser",ToUser);
		return true;
	}
	
}
