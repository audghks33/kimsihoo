package com.kimsihoo.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
public class Printing {

	@Setter(onMethod_ = @Autowired)
	private Printer printer;
	
	@Test
	public void printing() {
		
		assertNotNull(printer);
		
		log.info(printer);
		log.info("----------------------------------------");
		log.info(printer.getPrintPage());
	}
}
