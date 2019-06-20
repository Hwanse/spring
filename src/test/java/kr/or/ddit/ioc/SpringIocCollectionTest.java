package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.collection.IocCollection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-collection.xml")
public class SpringIocCollectionTest {

	private static final Logger logger = LoggerFactory.getLogger(SpringIocCollectionTest.class);
	
	@Resource(name="collectionBean")
	private IocCollection iocCollection;
	
	@Test
	public void iocCollectionBeanTest() {
		/***Given***/
		

		/***When***/

		/***Then***/
		assertNotNull(iocCollection);
		assertNotNull(iocCollection.getList());
		assertNotNull(iocCollection.getMap());
		assertNotNull(iocCollection.getSet());
		assertNotNull(iocCollection.getProperties());
		
		assertEquals(iocCollection.getList().get(0), "brown");
		assertEquals("brown", iocCollection.getMap().get("name"));
		assertTrue(iocCollection.getSet().contains("brown"));
		assertEquals("브라운", iocCollection.getProperties().get("name"));
		
		logger.debug("list : {}" , iocCollection.getList());
		logger.debug("map : {}", iocCollection.getMap());
		logger.debug("set : {}", iocCollection.getSet());
		logger.debug("properties : {} ", iocCollection.getProperties());
	}

}
