package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @author LTT-AH-ZY
* @version 创建时间：2019年5月19日 下午1:20:51
* @version 1.0
* @类说明
*/
@Controller
@RequestMapping(value="/test")
public class TestController {
	@RequestMapping(value="/index")
	public String index() {
		return "login/login";
	}
}
