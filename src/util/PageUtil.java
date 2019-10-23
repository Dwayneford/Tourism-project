package com.journey.util;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.journey.dao.DataTransmission;

/**
 * <b> 分页工具类 </b>
 * @author zhangxiang
 * 
 */
public class PageUtil implements Serializable {
    /**
     * currentPage 当前页
     */
    private int currentPage = 1;
    /**
     * pageSize 每页条数
     */
    private int pageSize = 5;
    /**
     * pageTotal 总页数
     */
    private int pageTotal;
    /**
     * recordTotal 总条数
     */
    private int recordTotal = 0;
    
	public PageUtil(int currentPage, int pageSize) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}
    
	public String getJson(String sql,DataTransmission dt){
		
		//从数据库中查询
		ResultSet rs=DBCPUtil.getRs(sql);
		//调用接口中的初始化方法,将数据集以及页面信息传过去
		dt.init(rs, currentPage, pageSize, pageTotal, recordTotal);
		//获取实体类处理的结果
		List list=dt.end();
		//将结果转换为json并返回
		return new Gson().toJson(list);
	}
    
    
   
    
    
    
}