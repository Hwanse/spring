package kr.or.ddit.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;

// <context:annotation-config/>의 역할도 @Configuration에서 담당함
@Configuration
@ComponentScan(basePackages = {"kr.or.ddit.board"}
	, includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,value = Aspect.class) )
@EnableAspectJAutoProxy
public class AopScanConfig {
	
}
