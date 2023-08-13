package com.icia.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.board.common.Paging;
import com.icia.board.dao.BoardDao;
import com.icia.board.dto.BoardDto;

@Service
public class BoardService {

	@Autowired
	private BoardDao bDao;
	
	public List<BoardDto> getBoardList(Integer pageNum) {
		List<BoardDto> bList=bDao.getBoardList(pageNum);
		return bList;
	}

	public String getPaging(Integer pageNum) {
		int totalNum = bDao.getBoardCount(); //전체 글의 갯수
		int listCount = 10; //페이지당 글의 갯수
		int pageCount = 2; //
		String boardName = "/board/list";
		Paging paging = new Paging(totalNum, pageNum, listCount, pageCount, boardName);
		return paging.makeHtmlPaging(); //<a>태그 호출
	}

	public BoardDto write(BoardDto board) {
	    return bDao.insertBoard(board) ?board : null;
	}

}
