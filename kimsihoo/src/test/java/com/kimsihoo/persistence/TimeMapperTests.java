package com.kimsihoo.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kimsihoo.mapper.TimeMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class TimeMapperTests {

	@Setter(onMethod_ = @Autowired)
	private TimeMapper timeMapper;
	
	@Test
	public void testGetTime() {
		log.info("Mapper 테스트------------------------------------");
		log.info(timeMapper.getClass().getName());
		log.info(timeMapper.getTime());
		log.info("Mapper 테스트------------------------------------");
		log.info(timeMapper.getTime2());
		log.info("Mapper 테스트2------------------------------------");
	}
	
}
