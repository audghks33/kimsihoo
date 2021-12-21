package com.kimsihoo.service;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kimsihoo.domain.BoardVO;
import com.kimsihoo.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardServiceTests {

	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	
	@Test
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setTitle("비즈니스 등록 제목");
		board.setContent("비즈니스 등록 내용");
		board.setWriter("비즈니스 등록 글쓴이");
		
		service.register(board);
		log.info("비즈니스 테스트 등록.........................");
		log.info("비즈니스 등록된 게시물의 번호:   " + board.getBno());
		log.info("비즈니스 테스트 등록.........................");
	}
	
	@Test
	public void testGetlist() {
		
		log.info("비즈니스 테스트 getlist.........................");
		service.getList(new Criteria(2,10)).forEach(board -> log.info(board));
		log.info("비즈니스 테스트 getlist.........................");
	}
	
	@Test
	public void testGet() {
		
		log.info("비즈니스 테스트 get.........................");
		log.info(service.get(21L));
		log.info("비즈니스 테스트 get.........................");
	}
	
	/*
	 * @Test public void testDelete() {
	 * log.info("비즈니스 테스트 delete........................."); log.info("삭제 결과 : "+
	 * service.remove(7L)); log.info("비즈니스 테스트 delete........................."); }
	 */
	
	@Test
	public void testUpdate() {
		
		BoardVO board = service.get(21L);
		
		if(board == null) {
			return;
		}
		
		board.setTitle("비즈니스 테스트 제목 변경");
		log.info("비즈니스 테스트 update.........................");
		log.info("변경 결과 : " +service.modify(board));
		log.info("비즈니스 테스트 update.........................");
	}
}
