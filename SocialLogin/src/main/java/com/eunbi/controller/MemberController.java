package com.eunbi.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eunbi.service.MemberService;
import com.eunbi.service.OauthService;

@Controller
public class MemberController {
	
	@Autowired
	private OauthService service;

	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	
	@RequestMapping(value="/kakaoUrl", method=RequestMethod.GET)
	public String Kakao(Model model, HttpSession session) {
		
		log.info("kakaoLogin 호출");
		String redirect_uri = "http://localhost:8088/oauth";
		String rest_api = "9c2d5efce4c881343385d90eb3e693e4";
		String response_type="code";
		
		return "redirect:https://kauth.kakao.com/oauth/authorize?client_id="+rest_api
				+"&redirect_uri="+redirect_uri+"&response_type="+response_type;
	}
	
	
	@RequestMapping(value="/oauth", method=RequestMethod.GET)
	public String kakaologin(@RequestParam(value="code", required=false) String code, Model model ) throws IOException {
		
		log.info("code : "+code);
		
		String access_token = service.getAccessToken(code);
		log.info(access_token);
		
		
		model.addAttribute("userInfo",service.getUserInfo(access_token));

		
		
		return "/main";
	
	
	
	
	}
	
	
}
