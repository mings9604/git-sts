package com.icia.board.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.icia.board.dao.MemberDao;
import com.icia.board.dto.MemberDto;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class MemberService {  //회원관리 서비스
	@Autowired
	private MemberDao mDao;
	
	//비번이 암호화된 회원만 로그인가능하도록 수정
	public MemberDto login(HashMap<String, String> member) {
		//복호화는 안되지만 비교는 가능
		BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
		//특정회원의 암호화된 비번 구하기--->'kdjlfkasjlkfjsldkjflksdjlfkjsadl'
		String encoPwd = mDao.getSecurityPw(member.get("m_id"));
		if(encoPwd !=null) {  //아이디가 존재함
			//matches('입력된 비번', '암호화된 비번')
			if(pwEncoder.matches(member.get("m_pwd"), encoPwd)) {
				log.info("비번 일치");  //로그인 성공
				return mDao.getMemberInfo(member.get("m_id"));
//				return true;  //수정???
			}else {
				log.info("비번 불일치");
				return null;
			}
		}else {
			//아이디가 부존재
			log.info("아이디 오류");
			return null;
		}
	}//end login

	public boolean join(MemberDto member) {
		// Encoder(암호화)<----->Decoder(복호화)
		//스프링은 복호화 불가능
		BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
		//1111--->dsjfkldsjfkljsdlkjflksdajflkasfadsf
		member.setM_pwd(pwEncoder.encode(member.getM_pwd()));
		return	mDao.join(member);
	}

}
