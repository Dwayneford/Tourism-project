package com.journey.dao;

import java.sql.ResultSet;
import java.util.List;

// 该接口对应分页处理
public interface DataTransmission {
	
	//传入从数据库中查询到的结果
	public void init(ResultSet rs,int currentPage,int pageSize,int pageTotal,int recordTotal);
	//获取实现类处理后的数据
	public List end();
	
	
}
