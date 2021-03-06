package com.wxsoft.netty.websocket.controller;

import com.wxsoft.netty.websocket.util.RandomUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController {
	
	@GetMapping("/index")
	public ModelAndView index(){
		ModelAndView mav=new ModelAndView("socket");
		mav.addObject("uid", RandomUtil.generateDigitalString(6));
		return mav;
	}
	
}
