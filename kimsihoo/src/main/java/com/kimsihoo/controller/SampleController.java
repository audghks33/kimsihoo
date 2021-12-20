package com.kimsihoo.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kimsihoo.domain.SampleDTO;
import com.kimsihoo.domain.SampleDTOList;
import com.kimsihoo.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	
	/*
	 * @InitBinder public void initBinder(WebDataBinder binder) { SimpleDateFormat
	 * dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	 * binder.registerCustomEditor(java.util.Date.class, new
	 * CustomDateEditor(dateFormat, false)); }
	 */
	
	@GetMapping("/TodoDTO")
	public String TodoDTOTest(TodoDTO todo) {
		log.info("todoDTO : "+todo);
		
		return "TodoDTO";
		//http://localhost:8080/sample/TodoDTO?title=test&dueDate=2018/01/01
	}

	@RequestMapping("")
	//@RequestMapping(value="/basic", method={RequestMethod.GET, RequestMethod.POST})
	public void basic() {
		log.info("basic....................................");
	}
	
	@GetMapping("basicOnlyGet")
	public void basicGet2() {
		log.info("basic get only 2222222.....................");
	}
	
	@GetMapping("/dto")
	public String dtoTest(SampleDTO dto) {
		log.info("" + dto);
		
		return "dto";
		//http://localhost:8080/sample/dto?name=%EB%8B%A5%EC%B3%90&age=7
	}
	
	@GetMapping("/requestParam")
	public String requestParam(@RequestParam("name")String name,@RequestParam("age") int age) {
		log.info("name : "+name);
		log.info("age : "+ age);
		
		return "rP";
		//http://localhost:8080/sample/requestParam?name=gg&age=4
	}
	
	@GetMapping("/ArrayList")
	public String ArrayListTest(@RequestParam("ids")ArrayList<String> ids) {
		log.info("ids : "+ids);
		
		return "ArrayList";
		//http://localhost:8080/sample/ArrayList?ids=1111&ids=22222
	}
	
	@GetMapping("/StringArray")
	public String StringArrayTest(@RequestParam("ids")String[] ids) {
		log.info("ids : "+ Arrays.toString(ids));
		
		return "StringArray";
		//http://localhost:8080/sample/StringArray?ids=111111&ids=4444444444
	}
	
	@GetMapping("/SampleDTOList")
	public String SampleDTOListTest(SampleDTOList list) {
		
		log.info("list dtos:  " +list);
		
		return "SampleDTOList";
		//http://localhost:8080/sample/SampleDTOList?list%5B0%5D.name=aaa
	}
	
	@GetMapping("/SampleDTOInt")
	//public String SampleDTOInt(SampleDTO dto, int page) {
	public String SampleDTOInt(SampleDTO dto, @ModelAttribute("page")int page) {
		log.info("dto  : " + dto);
		log.info("page :  "+ page);
		
		return "/sample/SampleDTOInt";
		//http://localhost:8080/sample/SampleDTOInt?page=11&name=spdls&age=777777
	}
	
	@GetMapping("ResponseBody")
	public @ResponseBody SampleDTO ResponseBodyTest() {
		log.info(".......ResponseBodyTest");
		SampleDTO dto = new SampleDTO();
		dto.setAge(999999);
		dto.setName("hhhhhhhh");
		
		return dto;
		//http://localhost:8080/sample/ResponseBody
	}
	
	@GetMapping("/ResponseEntity")
	public ResponseEntity<String> ResponseEntityTest(){
		log.info("ResponseEntityTest.........................");
		
		String msg = "{\"name\": \"홍길동\"}";
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<>(msg, header, HttpStatus.OK);
		//http://localhost:8080/sample/ResponseEntity
	}
	
	@GetMapping("/exUpload")
	public void exUpload() {
		log.info("exupload............................................");
		//http://localhost:8080/sample/exUpload
	}
	
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files ) {
		files.forEach(file -> {
			log.info(".............................................");
			log.info("name : "+file.getOriginalFilename());
			log.info("size:  " + file.getSize());
			
		});
		//http://localhost:8080/sample/SampleDTOInt?page=11&name=spdls&age=777777
	}
}
