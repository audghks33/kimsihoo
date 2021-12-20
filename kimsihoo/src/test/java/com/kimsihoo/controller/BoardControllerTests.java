package com.kimsihoo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class BoardControllerTests {

	@Setter(onMethod_ = { @Autowired })
	private WebApplicationContext ctx;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	@Test
	public void testList() throws Exception {
		log.info("컨트롤러 테스트 getlist");
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/board/list")).andReturn().getModelAndView().getModelMap());
		log.info("컨트롤러 테스트 getlist");
	}

	/*
	 * @Test public void testRegister() throws Exception {
	 * 
	 * String resultPage = mockMvc
	 * .perform(MockMvcRequestBuilders.post("/board/register") .param("title",
	 * "컨트롤테스트 새글 제목") .param("content", "컨트롤테스트 새글 내용") .param("writer",
	 * "컨트롤테스트 새글 작성자")) .andReturn().getModelAndView().getViewName();
	 * 
	 * log.info("컨트롤러 테스트 등록.............."); log.info(resultPage);
	 * log.info("컨트롤러 테스트 등록.............."); }
	 */

	/*
	 * @Test public void testGet() throws Exception{
	 * 
	 * log.info("컨트롤러 테스트 겟..........");
	 * log.info(mockMvc.perform(MockMvcRequestBuilders .get("/board/get")
	 * .param("bno","2")) .andReturn() .getModelAndView());
	 * log.info("컨트롤러 테스트 겟..........");
	 * 
	 * }
	 */

	@Test
	public void testModify() throws Exception {

		log.info("컨트롤러 테스트 modify");
		String resultPage = mockMvc
				.perform(
						MockMvcRequestBuilders.post("/board/modify").param("bno", "12").param("title", "컨트롤러 테스트 변경 제목")
								.param("content", "컨트롤러 테스트 변경 내용").param("writer", "컨트롤러 테스트 변경 작성자"))
				.andReturn().getModelAndView().getViewName();

		log.info(resultPage);
		log.info("컨트롤러 테스트 modify");
	}
	
	/*
	 * @Test public void testRemove() throws Exception{
	 * log.info("컨트롤러 테스트 remove....."); String resultPage =
	 * mockMvc.perform(MockMvcRequestBuilders.post("/board/remove") .param("bno",
	 * "4")) .andReturn().getModelAndView().getViewName(); log.info(resultPage);
	 * log.info("컨트롤러 테스트 remove....."); }
	 */
}
