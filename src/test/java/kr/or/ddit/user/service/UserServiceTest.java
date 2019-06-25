package kr.or.ddit.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.user.model.UserVO;

public class UserServiceTest extends LogicTestEnv{

	private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);
	
	@Resource(name="userService")
	private IuserService userService;
	
	
	/**
	* Method : userListTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 사용자 전체 리스트 조회
	*/
	@Test
	public void userListTest() {
		/***Given***/
		

		/***When***/
		List<UserVO> userList = userService.userList();
		
		/***Then***/
		assertNotNull(userService);
		assertNotNull(userList);
		assertTrue(userList.size() >= 100);
	}

	
	/**
	* Method : insertUserTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 사용자 등록 테스트
	*/
	@Test
	public void insertUserTest(){
		/***Given***/
		// 사용자 정보를 담고있는 vo객체 준비
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		UserVO userVO=null;
		try {
			userVO = new UserVO("애옹이", "testing1", "중앙로", "test1234", "대전광역시 중구 중앙로 76"
					, "영민빌딩 2층 204호", "34940", sdf.parse("2019-05-31"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		/***When***/
		// userDao.insertUser();
		int insertRes = userService.insertUser(userVO);
		/***Then***/
		// insertCnt(1)
		assertEquals(1, insertRes);
		// data 삭제
		userService.deleteUser(userVO.getUserId());
		
	}
	
	/**
	* Method : getUserTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 사용자 정보 조회 테스트
	*/
	@Test
	public void getUserTest() {
		/***Given***/
		String userId = "brown";

		/***When***/
		UserVO userVO = userService.getUser(userId);
		
		/***Then***/
		assertEquals("브라운", userVO.getName());
		assertEquals("곰", userVO.getAlias());
			
	}
	
	
	/**
	* Method : userPagingListTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 사용자 페이징 처리 테스트
	*/
	@Test
	public void userPagingListTest(){
		
		/***Given***/
		PageVO pageVO = new PageVO(1, 10);

		/***When***/
		Map<String, Object> resultMap = userService.userPagingList(pageVO);
		List<UserVO> userList = (List<UserVO>) resultMap.get("userList");
		int	paginationSize = (Integer)resultMap.get("paginationSize");
		/***Then***/

		assertNotNull(userList);
		assertEquals(10, userList.size());
		assertEquals(11, paginationSize);
	}
	
	
	
	/**
	* Method : updateUserTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 사용자 수정 테스트
	*/
	@Test
	public void updateUserTest() {
		/***Given***/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		UserVO userVO = null;
		try {
			userVO = new UserVO("황의조", "userTest2", "빛의조", "1234", "대전광역시 중구 중구청역", "대덕인재개발원"
								, "3412" , sdf.parse("2019-06-25"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		/***When***/
		int updateUser = userService.updateUser(userVO);
		
		/***Then***/
		assertEquals(1, updateUser);
	}
	
	
	/**
	* Method : ceilTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 사용자 페이지 길이 계산 테스트
	*/
	@Test
	public void ceilTest(){
		
		/***Given***/
		int usersCnt = 105;
		int pageSize = 10;
		
		/***When***/
		double paginationSize = Math.ceil( (double)usersCnt / pageSize);
		logger.debug("paginationSize : {} " , paginationSize);
		
		/***Then***/
		assertEquals(11, (int)paginationSize);
		
	}
	
}
