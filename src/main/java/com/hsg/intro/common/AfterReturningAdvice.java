package com.hsg.intro.common;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.hsg.intro.member.model.vo.Member;

@Component
@Aspect
public class AfterReturningAdvice {
	@Pointcut("execution(* com.kh.tsp..*ServiceImpl.*(..))")
	public void allPointcut(){
		
	}
	@AfterReturning(pointcut="allPointcut()", returning="returnObj")
	public void afterLog(JoinPoint jp, Object returnObj){
		//비즈니스 메소드가 리턴한 결과 데이터를 다른 용도로 처리할 때 사용한다.
		String methodName = jp.getSignature().getName();
		
		if(returnObj instanceof Member){
			Member m = (Member)returnObj;
			if(m.getUserId().equals("user99")){
				System.out.println("user99 님이 로그인 하였습니다.");
			}
		}
		
		System.out.println("[메소드 리턴] " + methodName + "()메소드 리턴 값 : " + returnObj.toString());
		
	}
}
