package com.zhuji.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhuji.dao.Ijingdian;
import com.zhuji.entity.Ejingdian;
import com.zhuji.util.DBCPUtil;

public class jingdianImpl implements Ijingdian{

	//资源初始化
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	@Override
	public List<Ejingdian> qurtlist(int jingdaian_ID) {
		List<Ejingdian> jingdianList = new ArrayList<Ejingdian>();
		// TODO Auto-generated method stub
		conn = DBCPUtil.getConn();
		
		String qurySql = "select (jingdian_ID,tupian_ID,mingcheng,dizhi,miaosu) from xingcheng";
		
		try {
			ps = conn.prepareStatement(qurySql);
			
			//获取头部信息
			//ResultSetMetaData headData = ps.getMetaData();
			
			//获取内容
			rs = ps.executeQuery();
			while(rs.next()){
				Ejingdian JDEnt = new Ejingdian(
						rs.getInt("jingdian_ID"),
						rs.getInt("tupian_ID"),
						rs.getString("mingcheng"),
						rs.getString("dizhi"),
						rs.getString("miaosu"));
				jingdianList.add(JDEnt);
			}
			DBCPUtil.closeRs(conn, rs, ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jingdianList;
	}

	@Override
	public void Up(Ejingdian JDEnt) {
		// TODO Auto-generated method stub
		String upSql = "updata jingdian_table set "
					+ "jingdian_ID = ?,"		
					+"tupian_ID = ?,"
					+ "mingcheng = ?"
					+ "dizhi = ?"
					+ "miaosu = ？";
		
		try {
			ps = conn.prepareStatement(upSql);
			
			//赋值
			ps.setInt(1,JDEnt.getJingdaian_ID());
			ps.setInt(2,JDEnt.getTupian_ID());
			ps.setString(3,JDEnt.getMingcheng());
			ps.setString(4,JDEnt.getDizhi());
			ps.setString(5,JDEnt.getMiaosu());
			
			DBCPUtil.closeRs(conn, rs, ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void remove(int jingdian_ID) {
		// TODO Auto-generated method stub
		conn = DBCPUtil.getConn();
		
		String detSql = "delete from jingdian_table where jingdian_ID="+jingdian_ID;
		
		try {
			ps = conn.prepareStatement(detSql);
			ps.executeQuery();
			
			DBCPUtil.closeRs(conn, rs, ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void add(Ejingdian JDEnt) {
		// TODO Auto-generated method stub
		conn = DBCPUtil.getConn();
		String AddSql = "insert into jingdian_table("
				+"jingdian_ID,"
				+ "tupian_ID,"
				+ "mingcheng,"
				+ "dizhi,"
				+ "miaosu"+"values(?,?,?,?,?)";
		
		try {
			ps = conn.prepareStatement(AddSql);
			
			//赋值
			ps.setInt(1,JDEnt.getJingdaian_ID());
			ps.setInt(2,JDEnt.getTupian_ID());
			ps.setString(3,JDEnt.getMingcheng());
			ps.setString(4,JDEnt.getDizhi());
			ps.setString(5,JDEnt.getMiaosu());
			
			DBCPUtil.closeRs(conn, rs, ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
