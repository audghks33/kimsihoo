package com.kimsihoo.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kimsihoo.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Test
	public void testGetList() {
		mapper.getList().forEach(board -> log.info(board));
	}

	/*
	 * @Test public void testInsertSelectKey() {
	 * 
	 * BoardVO board = new BoardVO(); board.setTitle("영속성계층Test insertKey Title");
	 * board.setContent("영속성계층Test insertKey Content");
	 * board.setWriter("영속성계층Test insertKey writer");
	 * 
	 * mapper.insertSelectKey(board);
	 * 
	 * log.info("영속성계층 insertSelectKey" + board); }
	 * 
	 * @Test public void testInsert() {
	 * 
	 * BoardVO board = new BoardVO(); board.setTitle("영속성계층Test insert");
	 * board.setContent("영속성계층Test Content"); board.setWriter("영속성계층Test writer");
	 * 
	 * mapper.insert(board);
	 * 
	 * log.info("영속성계층 인서트" + board); }
	 */

	
	/*
	 * @Test public void testRead() {
	 * 
	 * BoardVO board = mapper.read(5L);
	 * 
	 * log.info("영속성계층 read : "+board); }
	 * 
	 * @Test public void testDelete() {
	 * 
	 * 
	 * log.info("영속성계층 delete :" +mapper.delete(3L)); }
	 */
	
	@Test
	public void testUpdate() {
		
		BoardVO board = new BoardVO();
		
		board.setBno(4L);
		board.setContent("영속성계층 update 내용");
		board.setTitle("영속성계층 update 제목");
		board.setWriter("영속성계층 update 글쓴이");
		
		int count = mapper.update(board);
		log.info("영속성계층 update count :" + count);
	}
}
