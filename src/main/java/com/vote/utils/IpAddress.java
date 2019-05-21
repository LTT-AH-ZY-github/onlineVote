package com.vote.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

/**
* @author LTT-AH-ZY
* @version 创建时间：2019年5月21日 下午4:08:11
* @version 1.0
* @类说明 
*/
public class IpAddress {
	
	
	/**
	 * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
	 * @param request
	 * @return
	 */
	public final static String getIpAddress(HttpServletRequest request)throws IOException {
		// TODO Auto-generated method stub
		String ip = request.getHeader("X-Forwarded-For");  
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
				ip = request.getHeader("Proxy-Client-IP");  
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
				ip = request.getHeader("WL-Proxy-Client-IP");  
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
				ip = request.getHeader("HTTP_CLIENT_IP");  
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
				ip = request.getRemoteAddr();  
			}
		} else if (ip.length() > 15) {
			String[] ips = ip.split(",");
			for (int index = 0; index < ips.length; index++) { 
				 String strIp = (String) ips[index];
				 if (!("unknown".equalsIgnoreCase(strIp))) {
					 ip = strIp;
					 break;
				 }
			}
		}
		return ip;
	}
}
