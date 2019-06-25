package kr.or.ddit.ioc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


// defaultFilter : @Controller, @Service, @Repository, @Component
// userDefaultFilter를 false로 선언하면 개발자가 원하는 어노테이션만 스캔 가능
// ex : @Controller만 스캔 or @Service만 스캔 등등
@Configuration
@ComponentScan(basePackages = {"kr.or.ddit.board", "kr.or.ddit.aop"}, useDefaultFilters = true)
public class ApplicationIocBeanScanConfig {
	
	
}
