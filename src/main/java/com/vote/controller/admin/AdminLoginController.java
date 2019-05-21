package com.vote.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vote.interceptor.IPFilter;
import com.vote.po.Userlogin;
import com.vote.service.admin.adminLoginService;
import com.wordnik.swagger.annotations.ApiOperation;

/**
* @author LTT-AH-ZY
* @version 创建时间：2019年5月18日 下午11:04:30
* @version 1.0
* @类说明
*/
@Controller
@RequestMapping(value="/admin/login")
public class AdminLoginController {
	
	@Autowired
	private adminLoginService adminloginService;
	
	@RequestMapping(value="/index")
	@IPFilter(allow= {"127.0.0.1","192.168.2.4"})
	@ApiOperation(value = "管理员登录", httpMethod = "POST",notes = "获取登录姓名。。。")
	public String index() {
		try {
			List<Userlogin> list = adminloginService.getUsers();
			System.out.println("登录姓名："+list.get(0).getUsername());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "login/login";
	}
}
