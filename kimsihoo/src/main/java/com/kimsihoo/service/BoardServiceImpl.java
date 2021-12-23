package com.kimsihoo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kimsihoo.domain.BoardVO;
import com.kimsihoo.domain.Criteria;
import com.kimsihoo.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	
	@Override
	public void register(BoardVO board) {
		 
		log.info("비즈니스 등록........" +board);
		
		mapper.insertSelectKey(board);
	}

	@Override
	public BoardVO get(Long bno) {
		
		log.info("비즈니스 get........ "+bno +"번");
		
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {

		log.info("비즈니스 변경.......... ");
		return mapper.update(board) == 1;
	}

	@Override
	public boolean remove(Long bno) {
		
		log.info("비즈니스 제거..........");
		return mapper.delete(bno) == 1;
	}

	/*
	 * @Override public List<BoardVO> getList() {
	 * 
	 * log.info("비즈니스 getlist........ "); return mapper.getList(); }
	 */
	
	@Override
	public List<BoardVO> getList(Criteria cri) {
		
		log.info("비즈니스 getlist........ Cri 확인 : " + cri);
		return mapper.getListWithPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		log.info("총 게시글 개수 "  + cri);
		
		return mapper.getTotalCount(cri);
	}
	
	

	
}
