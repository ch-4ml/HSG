package com.hsg.intro.common;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hsg.intro.member.model.vo.Member;

@Repository
@Aspect
public class LoginLogging {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginLogging.class);
	@Pointcut("execution(* com.hsg.intro..*DaoImpl.login*(..))")
	public void loginPointcut(){
		
	}
	@AfterReturning(pointcut="loginPointcut()",returning="returnObj")
	public Object loginLing(JoinPoint jp, Object returnObj){
		if(returnObj instanceof Member){
			Member m = (Member)returnObj;
			
			LOGGER.info(new Date() + " : " + m.getUserId() + " - login");
		}
		
		return returnObj;
	}
}
