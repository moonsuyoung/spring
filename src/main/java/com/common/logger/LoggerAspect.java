package com.common.logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LoggerAspect {
	protected Log log = LogFactory.getLog(LoggerAspect.class);
	static String name = "";
	static String type = "";
	
	@Around("execution(* com..controller.*Controller.*(..)) or execution(* com..service.*Impl.*(..)) or execution(* com..dao.*Dao.*(..)) ")
	public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {
		type = joinPoint.getSignature().getDeclaringTypeName();
		
		if(type.indexOf("Controller") > -1) {
			name = "Controller \t: ";
		} else if(type.indexOf("Service") > -1) {
			name = "Service \t: ";
		} else if(type.indexOf("Dao") > -1) {
			name = "Dao \t: ";
		}
		
		log.debug(name +  type + "." + joinPoint.getSignature().getName() + "()");
		
		return joinPoint.proceed();
	}
	
}
