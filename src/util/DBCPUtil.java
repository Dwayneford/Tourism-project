package com.journey.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
/**
 * 
 * @author dyb
 * 
 *获得一个数据库链接的方法：getConn()
 *
 */
public class DBCPUtil {
	//定义数据源
	private static DataSource ds = null;
	//初始化对象
	private static BasicDataSource bds = new BasicDataSource();
	
	/**
	 * 获得链接
	 */
	public Connection getConn(){
		//获取外部资源文件
		ResourceBundle rd = ResourceBundle.getBundle("dbcp");
		if(rd==null){
			System.out.println("加载资源文件失败！！1");
		}else if(ds==null){
			//获取参数
			String url= rd.getString("url");
			String userName= rd.getString("userName");
			String driverClassName= rd.getString("driverClassName");
			String maxActive= rd.getString("maxActive");
			String minIdle= rd.getString("minIdle");
			String maxWait= rd.getString("maxWait");
			String userPwd= rd.getString("userPwd");
			
			//存入初始化对象中
			bds.setUsername(userName);
			bds.setPassword(userPwd);
			bds.setUrl(url);
			bds.setDriverClassName(driverClassName);
			//bds.setMaxActive(Integer.parseInt(maxActive));
			bds.setMinIdle(Integer.parseInt(minIdle));
			//bds.setMaxWait(Integer.parseInt(maxWait));
			
			//将初始化对象 赋值给数据源
			ds = bds;
		}
		
		Connection conn = null;
		// 返回链接
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	/**
	 * 测试方法
	 * @param args
	 */
	
	/**
	public static void main(String[] args) {
		
		for (int i = 0; i < 190; i++) {
			DBCPUtil dp = new DBCPUtil();
			Connection conn = dp.getConn();
			System.out.println(i+"==="+conn);
			//关闭
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		 
		
	}
	*/
}