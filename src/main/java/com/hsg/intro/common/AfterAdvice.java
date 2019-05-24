package com.hsg.intro.common;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterAdvice {
	@Pointcut("execution(* com.kh.tsp..*ServiceImpl.*(..))")
	public void allPointcut(){
		
	}
	
	@After("allPointcut()")
	public void finallyLog(){
		//에외 발생 여부와 상관없이 무조건 수행되는 어드바이스
		
		System.out.println("[후 실행] 비즈니스 로직 수행 후 무조건 작동");
		
	}
}
