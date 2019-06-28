package kr.or.ddit.user.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.user.model.UserVO;

public class UserDaoTest extends LogicTestEnv{

	private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
		
	@Resource(name="userDao")
	private IuserDao userDao;
	
	/**
	* Method : userListTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 사용자 전체 리스트 조회 테스트
	*/
	@Test
	public void userListTest() {
		/***Given***/
		

		/***When***/
		List<UserVO> userList = userDao.userList();
		/***Then***/
		assertNotNull(userDao);
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
			userVO = new UserVO("애옹이", "testing3", "중앙로", "test1234", "대전광역시 중구 중앙로 76"
					, "영민빌딩 2층 204호", "34940", sdf.parse("2019-05-31"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		/***When***/
		// userDao.insertUser();
		int insertRes = userDao.insertUser(userVO);
		/***Then***/
		// insertCnt(1)
		assertEquals(1, insertRes);
		// data 삭제
		int result = userDao.deleteUser(userVO.getUserId());
		assertTrue(result > 0);
		
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
		UserVO userVO = userDao.getUser(userId);
		
		/***Then***/
		assertEquals("브라운", userVO.getName());
		assertEquals("곰", userVO.getAlias());
			
	}
	
	/**
	 * 
	* Method : userPagingListTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 사용자 페이징 리스트 조회 테스트
	 */
	@Test
	public void userPagingListTest(){
		
		/***Given***/
		PageVO pageVO = new PageVO(1, 10);
		
		/***When***/
		List<UserVO> userList = userDao.userPagingList(pageVO);
		
		/***Then***/
		assertNotNull(userList);
		assertEquals(10, userList.size());
		
		for(UserVO userVO : userList){
			logger.debug("userLists : {}", userVO);
		}

	}
	
	/**
	 * 
	* Method : usersCntTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 사용자 전체 수 조회 테스트
	 */
	@Test
	public void usersCntTest(){
		
		int usersCnt = userDao.usersCnt();
		
		assertEquals(109, usersCnt);
		
	}
	
	/**
	* Method : updateUserTest
	* 작성자 : PC14
	* 변경이력 :
	* Method 설명 : 사용자 정보 수정
	*/
	@Test
	public void updateUserTest(){
		/***Given***/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		UserVO userVO = null;
		try {
			userVO = new UserVO("황선홍", "userTest2", "중앙", "1234", "대전광역시 중구 중구청역", "대덕인재개발원"
								, "3412" , sdf.parse("2019-06-25"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		/***When***/
		int updateUser = userDao.updateUser(userVO);
		
		/***Then***/
		assertEquals(1, updateUser);
		
	}
	
}
