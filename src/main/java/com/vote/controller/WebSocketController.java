package com.vote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @ClassName:WebSocketController
 * @Description:TODO
 * @author LTT-AH-ZY https://github.com/LTT-AH-ZY-github
 * @date 2019年5月22日
 */
@Controller
@RequestMapping(value="/common")
public class WebSocketController {
	/**
	 * 
	 * @Title:websocket
	 * @Description:TODO
	 * @param:@return
	 * @return:ModelAndView
	 */
	@RequestMapping(value="/websocket")
	public ModelAndView websocket() {
		ModelAndView model = new ModelAndView();
		model.setViewName("websocket/websocket");
		return model;
	}
}
