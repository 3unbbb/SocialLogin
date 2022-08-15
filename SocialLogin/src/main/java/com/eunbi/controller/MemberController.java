package com.eunbi.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eunbi.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService service;

	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	
	@RequestMapping(value="/kakaoLogin", method=RequestMethod.GET)
	public String Kakao(Model model, HttpSession session) {
		
		log.info("kakaoLogin 호출");
		
		
		return "redirect:/main.jsp";
	}
	
	
	
}
