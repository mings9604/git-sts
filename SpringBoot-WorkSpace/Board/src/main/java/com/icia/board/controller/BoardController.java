package com.icia.board.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icia.board.dto.BoardDto;
import com.icia.board.service.BoardService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService bSer;
	
	@GetMapping({"/list","/List"})  //board/list              ?pageNum=3
	public String boardList(@RequestParam(defaultValue = "1") Integer pageNum,Model model) throws JsonProcessingException {
		//if(pageNum==null) pageNum=1;
		log.info("boardList call");
		List<BoardDto> bList=bSer.getBoardList(pageNum);
		String pageHtml = bSer.getPaging(pageNum);
		log.info("bList:{}",bList.size());
		if(bList!=null) {
			model.addAttribute("bList", bList); //3, jstl(쉽다,협업X)
			model.addAttribute("paging", pageHtml);
			//model.addAttribute("bList", makeHtml(bList));  //1 서버
			//model.addAttribute("bList", 
			//		new ObjectMapper().writeValueAsString(bList)); //2,js(어렵다, 협업O)
		}
		return "boardList";
	}
	
	@GetMapping("/write")
	public String boardWrite() {
		log.info("글쓰기 창 열기");
		return "boardWrite";
	}
	
	@PostMapping("/write")
	public String boardWrite(BoardDto board, HttpSession session) {
		log.info("글쓰기 처리");
		log.info("board={}",board);
		//서비스 --> dao --> insert
		BoardDto bd = bSer.write(board);
		log.info("board={}",board);
		if(bd != null) {
			session.setAttribute("bd", bd);
			return "redirect:/board/list";
		}else {
			session.setAttribute("bd", bd);
			return "redirect:/board/write";
		}
//		return "boardList";
	}

	
}//class
