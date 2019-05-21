package com.test.controller;

import java.util.HashMap;
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
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", 100);
		map.put("msg", "测试");
		return new OtherResult(200, "获取成功", map).toMap();
	}
}
