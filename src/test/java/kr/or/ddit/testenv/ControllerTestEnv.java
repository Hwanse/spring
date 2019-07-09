package kr.or.ddit.testenv;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.config.ApplicationDatasource_dev;
import kr.or.ddit.config.spring.ApplicationContext;
import kr.or.ddit.config.spring.ApplicationDatasource;
import kr.or.ddit.config.spring.ApplicationTransaction;
import kr.or.ddit.config.spring.RootContext;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({
//	   "classpath:kr/or/ddit/config/spring/application-context.xml",
//	   "classpath:kr/or/ddit/config/spring/root-context.xml",
//	   "classpath:kr/or/ddit/config/spring/application-datasource-dev.xml",
//	   "classpath:kr/or/ddit/config/spring/application-transaction.xml"})
//일반 자바 환경 -> 웹 환경
//applicationContext --> 웹 환경의 applicationContext생성 
@ContextConfiguration(classes = {ApplicationContext.class,
								 RootContext.class,
								 ApplicationDatasource_dev.class,
								 ApplicationTransaction.class})
@WebAppConfiguration
public class ControllerTestEnv {
	
	@Resource(name="datasource")
	private DataSource datasource;
	
	@Autowired
	protected WebApplicationContext ctx; //webApplicationContext == spring container
	
	protected MockMvc mockMvc; // mockMvc  == dispatcher servlet 
	
	@Before
	public void setup() {
		// 컨테이너를 받아서 mockMvc객체 생성 
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		
		ResourceDatabasePopulator rdp = new ResourceDatabasePopulator();
		// 에러가 발생했을시 계속 진행할지 물어보는 기능 (true: 무시하고 계속진행/ false: 중지)
		rdp.setContinueOnError(false);
		rdp.addScript(new ClassPathResource("kr/or/ddit/testenv/dbInit.sql"));
		DatabasePopulatorUtils.execute(rdp, datasource);
	}
	
	@Ignore
	@Test
	public void test() {}
	
}
