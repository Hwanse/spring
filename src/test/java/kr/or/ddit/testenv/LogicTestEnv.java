package kr.or.ddit.testenv;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:kr/or/ddit/config/spring/root-context.xml",
	   "classpath:kr/or/ddit/config/spring/application-datasource-dev.xml",
	   "classpath:kr/or/ddit/config/spring/application-transaction.xml"})
public class LogicTestEnv {

	@Resource(name="datasource")
	private DataSource datasource;
	
	
	// 개발 중에 테스트하기 위한 db환경 구축 
	// (db환경을 항상 동일하게 만들어주기 때문에 db를 자유롭게 테스팅이 가능함) 
	// 주의!! : 개발할때 개인용 개발환경에서는 사용이 가능하나 실무 및 운영 환경에서는 절대 금지
	@Before
	public void setup() {
		ResourceDatabasePopulator rdp = new ResourceDatabasePopulator();
		// 에러가 발생했을시 계속 진행할지 물어보는 기능 (true: 무시하고 계속진행/ false: 중지)
		rdp.setContinueOnError(false);
		rdp.addScript(new ClassPathResource("kr/or/ddit/testenv/dbInit.sql"));
		DatabasePopulatorUtils.execute(rdp, datasource);
	}
}
