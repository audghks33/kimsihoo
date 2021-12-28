package com.kimsihoo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kimsihoo.domain.BoardVO;
import com.kimsihoo.domain.Criteria;
import com.kimsihoo.domain.PageDTO;
import com.kimsihoo.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {

	private BoardService service;
	
	/*
	 * @GetMapping("/list") public void list(Model model) {
	 * 
	 * log.info("컨트롤러 list"); model.addAttribute("list", service.get()); }
	 */
	
	@GetMapping("/list")
	public void list(Model model, Criteria cri) {
		
		log.info("컨트롤러 list + cri : " +cri);
		model.addAttribute("list", service.getList(cri));
		//model.addAttribute("pageMaker", new PageDTO(cri, 123));
		
		int total = service.getTotal(cri);
		
		log.info("total: "+ total);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	@PostMapping("/register2")
	public String register2(BoardVO board, RedirectAttributes rttr) {
		
		log.info("컨트롤러 등록.......");
		
		service.register(board);
		
		rttr.addFlashAttribute("result", board.getBno());
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/register2")
	public void register2() {
		
	}
	
	@GetMapping({"/get","/modify"})
	public void get(@RequestParam("bno") Long bno, Model model, @ModelAttribute("cri")Criteria cri) {
		log.info("컨트롤러 get or modify");
		model.addAttribute("board", service.get(bno));
		log.info("컨트롤러 get");
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr, @ModelAttribute("cri")Criteria cri) {
		log.info("modify : "+ board);
		
		if(service.modify(board)) {
			rttr.addFlashAttribute("modifyBno", board.getBno());
			rttr.addFlashAttribute("result", "success");
		
		}
		
		/*
		 * rttr.addAttribute("pageNum", cri.getPageNum()); rttr.addAttribute("amount",
		 * cri.getAmount()); rttr.addAttribute("type", cri.getType());
		 * rttr.addAttribute("keyword", cri.getKeyword());
		 */
		return "redirect:/board/list" + cri.getListLink();
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr, @ModelAttribute("cri") Criteria cri) {
		log.info("remove...."  + bno);
		if(service.remove(bno)) {
			rttr.addFlashAttribute("modifyBno", bno);
			rttr.addFlashAttribute("result", "del");
		}
		
		/*
		 * rttr.addAttribute("pageNum", cri.getPageNum()); rttr.addAttribute("amount",
		 * cri.getAmount()); rttr.addAttribute("type", cri.getType());
		 * rttr.addAttribute("keyword", cri.getKeyword());
		 */
		
		return "redirect:/board/list" + cri.getListLink();
	}
	
	/////////test canvas
	@GetMapping("/canvas")
	public void canvas() {
		
	}
	
	@GetMapping("/canvas2")
	public void canvas2() {
		
	}
	
	@GetMapping("/canvas3")
	public void canvas3() {
		
	}
	
	
	
	/////////test canvas
	
	
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
			
			uploadFileName = uuid.toString() + "_" + uploadFileName + ".jpg";
			//uuid 추가
			
			log.info(uploadFileName);
			
			String getFile = uploadPath+"\\"+uploadFileName;
			
			
			//File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			try {
			File saveFile = new File(uploadPath, uploadFileName);
				
				multipartFile.transferTo(saveFile);
				
			
				log.info("세입파일 :" +saveFile);
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
}
