package com.vote.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.vote.po.Person;
import com.vote.po.Userlogin;
import com.vote.service.LoginService;
import com.vote.service.PersonService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * 
 * @ClassName:loginController
 * @Description:TODO
 * @author LTT-AH-ZY https://github.com/LTT-AH-ZY-github
 * @date 2019年5月22日
 */
@Controller
@RequestMapping(value="/common")
public class loginController {
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private PersonService personService;
	
	@RequestMapping(value="/index",method=RequestMethod.POST)
	@ApiOperation(value = "根据用户名获取用户对象", httpMethod = "POST",notes = "根据用户名获取用户对象")
	public String index(@ApiParam(name = "adminloginname", value = "管理员登录账号") @RequestParam String adminloginname) {
		try {
			List<Userlogin> list = loginService.getUsers();
			System.out.println(list.get(0).getUsername());
			
			List<Person> list1 = personService.getPersons();
			System.out.println(list1.get(1).getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "login/login";
	}
}
