package com.icia.board.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.icia.board.dto.MemberDto;

//DB 프레임워크: (i)Mybatis 

//회원관리 db로직
@Mapper   //resoureces/mppers/MemberDao.xml
public interface MemberDao {
	//@Select("SELECT COUNT(*) FROM M WHERE M_ID=#{m_id} AND M_PWD=#{m_pwd}")
	boolean login(MemberDto member);

	boolean join(MemberDto member);

	String getSecurityPw(String id);

	MemberDto getMemberInfo(String id); 

}
