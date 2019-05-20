package com.test.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vote.utils.result.OtherResult;

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
	public @ResponseBody String index() {
		return new OtherResult(200, "获取成功", 123).toJson();
	}
	
	@RequestMapping(value="/index2")
	public @ResponseBody Map<String,Object> index2() {
		return new OtherResult(200, "获取成功", 123).toMap();
	}
}
