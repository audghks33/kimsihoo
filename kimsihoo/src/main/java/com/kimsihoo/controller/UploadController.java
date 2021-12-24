package com.kimsihoo.controller;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class UploadController {

	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("upload form");
	}
	
	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
		
		String uploadFolder = "D:\\upload\\temp2";
		
		for(MultipartFile multipartFile : uploadFile) {
			log.info("-------------------업로드내용확인-----------------");
			log.info("Upload File Name: " +multipartFile.getOriginalFilename());
			log.info("Upload File Size: " +multipartFile.getSize());
			
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			log.info("---------------------업로드내용확인---------------");
		}
	}
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("upload ajax");
	}
	
	@PostMapping("/uploadAjaxAction")
	public void uploadAjaxPost(MultipartFile[] uploadFile) {
		
		log.info(" ajax post 업뎃");
		
		String uploadFolder = "D:\\upload\\temp2";
		
		//폴더 생성
		File uploadPath = new File(uploadFolder, getFolder());
		log.info("upload Path : " +uploadPath  );
		
		if(uploadPath.exists()  == false) {
			uploadPath.mkdirs();
		}
		//
		
		
		for(MultipartFile multipartFile : uploadFile) {
			log.info("-------------------AJAX업로드내용확인-----------------");
			log.info("Upload File Name: " +multipartFile.getOriginalFilename());
			log.info("Upload File Size: " +multipartFile.getSize());
			
			String uploadFileName= multipartFile.getOriginalFilename();
			
			//uploadFileName= uploadFileName.substring(uploadFileName.indexOf("\\")+1);
			//폴더 추가 이후 
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") +1);
			
			log.info("only file name: " + uploadFileName);
			
			//uuid 추가
			UUID uuid = UUID.randomUUID();
			
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			//uuid 추가
			
			//File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			try {
			File saveFile = new File(uploadPath, uploadFileName);
				
				multipartFile.transferTo(saveFile);
				
				log.info(uploadFileName);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			log.info("---------------------AJAX업로드내용확인---------------");
		}
	}
	
	public String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		String str  = sdf.format(date);
		
		return str.replace("-", File.separator);
	}
	
	private boolean checkImageType(File file) {
		
		try {
			String contentType = Files.probeContentType(file.toPath());
			
			return contentType.startsWith("image");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
