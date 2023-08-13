package com.icia.board.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.experimental.Accessors;
@Accessors(chain = true)
@Data
//view(MemberDTO)---->controller--->service(DTO<--->Entity)---->dao--->db(Table:MemberEntity)
//@Alias("member")
//@Alias("memberDto")
public class MemberDto {
	private String m_id; //파라미터명==필드명==컬럼명
	private String m_pwd;
	private String m_name;
	private String m_birth;
	private String m_addr;
	private String m_phone;
	//minfo 뷰 공유
	private String m_point;  //회원포인트
	private String m_grade;  //회원등급
	
}
