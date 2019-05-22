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
 * 
 * @ClassName:AdminLoginController
 * @Description:TODO
 * @author LTT-AH-ZY https://github.com/LTT-AH-ZY-github
 * @date 2019年5月22日
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
