package kr.or.ddit.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	public void before(JoinPoint joinPoint) {
		logger.debug("Logging Aspect before method");
	}

	public void after(JoinPoint joinPoint) {
		logger.debug("Logging Aspect after method");
	}
	
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		// business logic 실행 전
		logger.debug("Logging Aspect around method before");

		// business logic 실행중
		// 실행하는 해당 메서드명 반환
		logger.debug("method name : {}", joinPoint.getSignature().getName());
		// 실행하는 해당 메서드의 인자들을 반환
		Object[] methodArgs = joinPoint.getArgs();
		Object returnObj = joinPoint.proceed(methodArgs);
		
		// business logic 실행 후
		logger.debug("Logging Aspect around method after");
		
		return returnObj;
	}
	
}
