package com.icia.board.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.icia.board.dto.MemberDto;
import com.icia.board.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private final MemberService mSer;
	
  	@GetMapping("/login")
	public String login() {
		log.info("로그인 화면(폼)");
  		return "login"; //login.jsp
	}
	@PostMapping("/login")
	//public String login(@RequestParam String m_id, 
	//					@RequestParam("m_pwd") String pw) {
	public String login(@RequestParam HashMap<String, String> member, Model model, HttpSession session) {
		log.info("로그인 처리");
		log.info("id:{}, pw:{}", member.get("m_id"), member.get("m_pwd"));
		MemberDto mb=mSer.login(member);
		log.info("mb={}", mb);
		if(mb!=null) {
			session.setAttribute("mb", mb);
			return "redirect:/board/list";
//			return "boardList";  //boardList.jsp
		}else {
			model.addAttribute("msg", "로그인 실패");
			return "login";
		}
	}
	@GetMapping("/join")
	public String join() {
		log.info("회원가입 화면(폼)");
		return "join"; //join.jsp
	}
	
	//String, Integer, int, double 등 단순 타입이면 @RequestParam생략 가능
	//MemberDto 등 산순 타입이 아니면 @ModelAttribute 명시
	//예외:HashMap) (@RequestParam HashMap<String, String> member) 명시할것.
	@PostMapping("/join")
	public String join(@ModelAttribute("mb") MemberDto member, Model model,RedirectAttributes rttr) {
		model.addAttribute("mb", member);
		log.info("회원가입 처리");
		log.info("member:{}",member);
		boolean result=mSer.join(member);
		//String view=null;
		if(result) {
			model.addAttribute("msg", "가입성공");
			//session.setAttribute("msg", "가입성공");
			rttr.addFlashAttribute("msg", "가입성공"); //request영역 //1회성 data
			//rttr.addAttribute("msg", "가입성공");  //request객체에 저장,F5시 여러번 사용
			return "redirect:/";  //home.jsp   ,redirect:/url
			//view="login";
		}else {
			model.addAttribute("msg", "가입실패");
			return "join";
			//view="join";
		}
		//return view;
	}
	@GetMapping("/logout")
	public String getlogout(HttpSession session, RedirectAttributes rttr) {
		log.info("get 로그아웃");
		session.invalidate();
		rttr.addFlashAttribute("msg", "버튼을 눌러 로그아웃 하세요");
		return "redirect:/board/list";
	}
	
	@PostMapping("/logout")
	public String logout(HttpSession session, RedirectAttributes rttr) {
		log.info("Post 로그아웃");
		session.invalidate();
		rttr.addFlashAttribute("msg", "Post 로그아웃 성공");
		return "redirect:/";
	}
}//class end
