package com.hsg.intro.member.model.dao;

import javax.security.auth.login.LoginException;

import org.mybatis.spring.SqlSessionTemplate;

import com.hsg.intro.member.model.vo.Member;

public interface MemberDao {

	String loginCheck(Member m) throws LoginException;

	Member selectMember(Member m) throws LoginException;

}
