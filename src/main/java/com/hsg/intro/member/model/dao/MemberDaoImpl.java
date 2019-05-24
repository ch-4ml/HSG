package com.hsg.intro.member.model.dao;


import javax.security.auth.login.LoginException;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.hsg.intro.member.model.vo.Member;
@Repository
public class MemberDaoImpl implements MemberDao{
	@Autowired
	private SqlSessionTemplate sqlSession;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Override
	public String loginCheck(Member m) throws LoginException {
		System.out.println("dao userId : " + m.getUserId());
		System.out.println("dao userPw : " + m.getUserPw());
		System.out.println("sqlSession : " + sqlSession);
		
		String cryptPwd = sqlSession.selectOne("Member.selectPwd",m.getUserId());

		String result = "";
		
		System.out.println("암호화된 비밀번호 : " + cryptPwd);
		
		if (!passwordEncoder.matches(m.getUserPw(), cryptPwd)) {
			result = "F";
		} else {
			result = "T";
		}
		
		return result;
	}
	@Override
	public Member selectMember(Member m) throws LoginException {
		
		Member member = sqlSession.selectOne("Member.loginCheck",m);
		
		return member;
	}
	
}
