package com.kimsihoo.mapper;

import java.util.List;

import com.kimsihoo.domain.BoardVO;

public interface BoardMapper {

	//@Select("select * from tbl_board where bno> 0")
	
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO board);
	
	public List<BoardVO> getList();
	
	public BoardVO read(Long bno);
	
	public int delete(Long bno);
	
	public int update(BoardVO board);
	
}
