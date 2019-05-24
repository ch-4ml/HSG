package com.hsg.intro.common;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class AroundAdvice {
	@Pointcut("execution(* com.kh.tsp..*ServiceImpl.*(..))")
	public void allPointcut(){
		
	} 
	@Around("allPointcut()")
	public Object aroundLog(ProceedingJoinPoint pj) throws Throwable{
		
		// 사전, 사후 처리를 모두 해결하고자 할때 사용하는 어드바이스
		String methodName = pj.getSignature().getName();
		
		StopWatch stopWatch = new StopWatch();
		
		stopWatch.start();
		
		Object obj = pj.proceed();
		
		stopWatch.stop();
		
		System.out.println(methodName + "()메소드 수행 시간 : " + stopWatch.getTotalTimeMillis() + "(ms)초");
		
		return obj;
	}
}
