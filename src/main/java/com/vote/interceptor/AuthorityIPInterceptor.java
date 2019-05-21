package com.vote.interceptor;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.common.collect.Lists;
import com.vote.utils.IpAddress;

/**
* @author LTT-AH-ZY
* @version 创建时间：2019年5月21日 下午4:43:30
* @version 1.0
* @类说明
*/
public class AuthorityIPInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		if (handler instanceof HandlerMethod) {
			IPFilter ipFilter = ((HandlerMethod) handler).getMethodAnnotation(IPFilter.class);
			if (ipFilter == null) {
				return true;
			}
			String ipAddress = IpAddress.getIpAddress(request);
			System.out.println("拦截的IP是："+ipAddress);
			ArrayList<String> deny = Lists.newArrayList(ipFilter.deny());
			ArrayList<String> allow = Lists.newArrayList(ipFilter.allow());
			
			//设置了黑名单,  黑名单内ip不给通过
			if (CollectionUtils.isNotEmpty(deny)) {
				if (deny.contains(ipAddress)) {
					response.setStatus(500);
					return false;
				}
			}
			
			 //设置了白名单,  只有白名单内的ip给通过
			if (CollectionUtils.isNotEmpty(allow)) {
				if (allow.contains(ipAddress)) {
					return true;
				}else {//既不在白名单中也不再黑名单中的未知IP
					response.setStatus(500);
					return false;
				}
			}
		}
		return true;
	}
}
