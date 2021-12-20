package com.kimsihoo.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {

	@Select("select sysdate From dual")
	public String getTime();
	
	public String getTime2();
}
 