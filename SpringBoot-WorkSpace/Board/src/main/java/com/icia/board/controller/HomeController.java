package com.icia.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;


@Controller

public class HomeController {
  	@GetMapping("/")   
	public String home(Model model) {
		//model객체 데이터 저장용,request영역에 저장 
		//model.addAttribute("msg", "jsp이식 성공"); 
		return "home"; //home.jsp로 포워딩
	}
}
