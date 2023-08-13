package com.icia.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.icia.board.dto.BoardDto;

@Mapper
public interface BoardDao {

	List<BoardDto> getBoardList(Integer pageNum);

	int getBoardCount();

	boolean insertBoard(BoardDto board);

}
