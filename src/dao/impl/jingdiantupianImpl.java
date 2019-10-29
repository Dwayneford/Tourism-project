package com.zhuji.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhuji.entity.Ejingdiantupian;
import com.zhuji.util.DBCPUtil;

import com.zhuji.dao.Ijingdiantupian;

public class jingdiantupianImpl implements Ijingdiantupian{

	//资源初始化
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	@Override
	public List<Ejingdiantupian> ShowAll(){
		
		List<Ejingdiantupian> tulist = new ArrayList<Ejingdiantupian>();
		// TODO Auto-generated method stub
		conn = DBCPUtil.getConn();
		String qurySql = "select tupian_ID,jingdian_ID,tupianURL from jingdiantupian_table";
		
		try {
			ps = conn.prepareStatement(qurySql);
			
			//获取头部信息
			//ResultSetMetaData headData = ps.getMetaData();
			
			//获取内容
			rs = ps.executeQuery();
			while(rs.next()){
				Ejingdiantupian jdtpLit = new Ejingdiantupian(
						rs.getInt("tupian_ID"),
						rs.getInt("jingdian_ID"),
						rs.getString("tupianURL"));
				tulist.add(jdtpLit);
			}
			DBCPUtil.closeRs(conn, rs, ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tulist;
	}
	
	@Override
	public boolean remove(int tupian_ID) {
		boolean bool = false;
		// TODO Auto-generated method stub
		conn = DBCPUtil.getConn();
		
		String detSql = "delete from jingdiantupian_table where tupian_ID="+tupian_ID;
		
		try {
			ps = conn.prepareStatement(detSql);
			int row = ps.executeUpdate();
			if(row > 0) bool = true;
			DBCPUtil.closeRs(conn, rs, ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}

	@Override
	public boolean addOrUp(Ejingdiantupian jdtpEnt) {
		boolean bool = false;
		// TODO Auto-generated method stub
		conn = DBCPUtil.getConn();
		if(("").equals(jdtpEnt.getTupian_ID())){
			String AddSql = "insert into jingdiantupian_table("
					+"tupian_ID,"
					+ "jingdian_ID,"
					+ "tupianURL)"
					+"values(?,?,?)";
			
			try {
				ps = conn.prepareStatement(AddSql);
				
				//赋值
				ps.setInt(1,jdtpEnt.getTupian_ID());
				ps.setInt(2,jdtpEnt.getJingdian_ID());
				ps.setString(3,jdtpEnt.getTupianURL());
				
				int row = ps.executeUpdate();
				if(row>0)
				{
					bool = true;
				}
				DBCPUtil.closeRs(conn, rs, ps);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
				String upSql = "updata jingdiantupian_table set "
						+ "tupian_ID = ?,"		
						+"jingdian_ID = ?,"
						+ "tupianURL = ?";
			
			try {
				ps = conn.prepareStatement(upSql);
				
				//赋值
				ps.setInt(1,jdtpEnt.getTupian_ID());
				ps.setInt(2,jdtpEnt.getJingdian_ID());
				ps.setString(3,jdtpEnt.getTupianURL());
				
				int row = ps.executeUpdate();
				if(row > 0) bool = true;
				DBCPUtil.closeRs(conn, rs, ps);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bool;
	}
	public static List<Ejingdiantupian> list(String sql,Object obj){
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Ejingdiantupian> jingdiantupianList = new ArrayList<Ejingdiantupian>();
		// TODO Auto-generated method stub
		conn = DBCPUtil.getConn();
		String qurySql = "select tupian_ID,jingdian_ID,tupianURL from jingdiantupian_table where "+sql+"="+obj+";";
		System.out.println(qurySql);
		try {
			ps = conn.prepareStatement(qurySql);
			
			//获取头部信息
			//ResultSetMetaData headData = ps.getMetaData();
			
			//获取内容
			rs = ps.executeQuery();
			while(rs.next()){
				Ejingdiantupian jdtpEnt = new Ejingdiantupian(
						rs.getInt("tupian_ID"),
						rs.getInt("jingdian_ID"),
						rs.getString("tupianURL"));
				jingdiantupianList.add(jdtpEnt);
			}
			DBCPUtil.closeRs(conn, rs, ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jingdiantupianList;	
	}

	@Override
	public List<Ejingdiantupian> qurylistBytupian_ID(int tupian_ID) {
		// TODO Auto-generated method stub
		return list("tupian_ID",tupian_ID);
	}

	@Override
	public List<Ejingdiantupian> qurylistByjingdian_ID(int jingdian_ID) {
		// TODO Auto-generated method stub
		return list("jingdian_ID",jingdian_ID);
	}

	@Override
	public List<Ejingdiantupian> qurylistBytupianURL(String tupianURL) {
		// TODO Auto-generated method stub
		return list("tupianURL",tupianURL);
	}
}
