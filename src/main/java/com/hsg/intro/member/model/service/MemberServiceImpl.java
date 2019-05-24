package com.hsg.intro.member.model.service;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsg.intro.member.model.dao.MemberDaoImpl;
import com.hsg.intro.member.model.vo.Member;


@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDaoImpl md;

	@Override
	public String loginMember(Member m) throws LoginException {
		
		System.out.println("serveice m : " + m.toString());
		
		return md.loginCheck(m);
	}
	
	@Override
	public Member selectMember(Member m) throws LoginException {
		
		System.out.println("serveice m : " + m.toString());
		
		return md.selectMember(m);
	}

}
