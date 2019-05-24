package com.hsg.intro.member.model.service;

import javax.security.auth.login.LoginException;

import com.hsg.intro.member.model.vo.Member;

public interface MemberService {

	String loginMember(Member m) throws LoginException;

	Member selectMember(Member m) throws LoginException;


}
