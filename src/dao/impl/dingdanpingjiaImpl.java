package com.zhuji.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhuji.dao.Idingdanpingjia;
import com.zhuji.entity.Edingdanpingjia;
import com.zhuji.util.DBCPUtil;

	/**
	 * @author xxy
	 *@time 2019-10-24*/
public class dingdanpingjiaImpl implements Idingdanpingjia{
	
	//资源初始化
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	@Override
	public List<Edingdanpingjia> qurtlistbyxingcheng_ID(int xingcheng_ID) {
		List<Edingdanpingjia> ddpjList = new ArrayList<Edingdanpingjia>();
		// TODO Auto-generated method stub
		conn = DBCPUtil.getConn();
		
		String qurySql = "select pinjia_ID,jingdian_ID,xingcheng_ID,user_ID,pingjia_time from pingjia_table where xingcheng_ID"+xingcheng_ID;
		
		try {
			ps = conn.prepareStatement(qurySql);
			
			//获取头部信息
			//ResultSetMetaData headData = ps.getMetaData();
			
			//获取内容
			rs = ps.executeQuery();
			while(rs.next()){
				Edingdanpingjia ddpjEnt = new Edingdanpingjia(
						rs.getInt("jingdian_ID"),
						rs.getInt("xingcheng_ID"),
						rs.getInt("user_ID"),
						rs.getString("pingjia"),
						rs.getString("pingjia_time"));
				ddpjList.add(ddpjEnt);
			}
			DBCPUtil.closeRs(conn, rs, ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ddpjList;
	}
	
	@Override
	public boolean remove(int xingcheng_ID) {
		boolean bool = false;
		// TODO Auto-generated method stub
		conn = DBCPUtil.getConn();
		
		String detSql = "delete from pingjia_table where xingcheng_ID="+xingcheng_ID;
		
		try {
			ps = conn.prepareStatement(detSql);
			
			int row = ps.executeUpdate();
			if(row > 0)System.out.println("操作成功");
			DBCPUtil.closeRs(conn, rs, ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}

	@Override
	public boolean addOrUp(Edingdanpingjia ddpj){
		Edingdanpingjia ddpjEnt = new Edingdanpingjia();
		boolean bool = false;
		System.out.println("程序执行...............");
		if(("").equals(ddpj.getPinjia_ID())){
			// TODO Auto-generated method stub
			conn = DBCPUtil.getConn();
			String AddSql = "insert into pingjia_table(jingdian_ID, xingcheng_ID,user_ID,pingjia,pingjia_time) values(?,?,?,?,?)";
			
			System.out.println(AddSql);
//			Edingdanpingjia sdf = new Edingdanpingjia(jingdian_ID, xingcheng_ID, user_ID, pingjia, pingjia_time)
			try {
				ps = conn.prepareStatement(AddSql);
				
				//赋值
				ps.setInt(1,ddpjEnt.getJingdian_ID());
				ps.setInt(2,ddpjEnt.getXingcheng_ID());
				ps.setInt(3,ddpjEnt.getUser_ID());
				ps.setString(4,ddpjEnt.getPingjia());
				ps.setString(5,ddpjEnt.getPingjia_time());
				System.out.println(ps);
				int row = ps.executeUpdate();
				if(row > 0){
					bool = true;
				}
				DBCPUtil.closeRs(conn, rs, ps);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			// TODO Auto-generated method stub
			String upSql = "updata pingjia_table set "	
						+"jingdian_ID = ?,"
						+ "xingcheng_ID = ?"
						+ "user_ID = ?"
						+ "pingjia = ?"
						+ "pingjia_time = ?";
			
			try {
				ps = conn.prepareStatement(upSql);
				
				//赋值
				ps.setInt(1,ddpjEnt.getJingdian_ID());
				ps.setInt(2,ddpjEnt.getXingcheng_ID());
				ps.setInt(3,ddpjEnt.getUser_ID());
				ps.setString(4,ddpjEnt.getPingjia());
				ps.setString(5,ddpjEnt.getPingjia_time());
				
				int row = ps.executeUpdate();
				if(row>0) bool=true;
				DBCPUtil.closeRs(conn, rs, ps);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bool;
	}
	public List<Edingdanpingjia> list1(String keyName,Object Value){
		
		//资源初始化
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		// TODO Auto-generated method stub
		List<Edingdanpingjia> ddpjList = new ArrayList<Edingdanpingjia>();
		// TODO Auto-generated method stub
		conn = DBCPUtil.getConn();
		
		String qurySql = "select pinjia_ID,jingdian_ID,xingcheng_ID,pingjia,user_ID,pingjia_time from pingjia_table where "+keyName+"="+Value;
		
		try {
			ps = conn.prepareStatement(qurySql);
			
			//获取头部信息
			//ResultSetMetaData headData = ps.getMetaData();
			
			//获取内容
			rs = ps.executeQuery();
			while(rs.next()){
				Edingdanpingjia ddpjEnt = new Edingdanpingjia(
						rs.getInt("jingdian_ID"),
						rs.getInt("xingcheng_ID"),
						rs.getInt("user_ID"),
						rs.getString("pingjia"),
						rs.getString("pingjia_time"));
				ddpjList.add(ddpjEnt);
			}
			DBCPUtil.closeRs(conn, rs, ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ddpjList;
	}
	@Override
	public List<Edingdanpingjia> qurtlistbypinjia_ID(int jingdian_ID) {
		return list1("jingdian_ID",jingdian_ID);
	}

	@Override
	public List<Edingdanpingjia> ShowAll() {
		// TODO Auto-generated method stub
		List<Edingdanpingjia> ddpjList = new ArrayList<Edingdanpingjia>();
		// TODO Auto-generated method stub
		conn = DBCPUtil.getConn();
		
		String qurySql = "select pinjia_ID,jingdian_ID,xingcheng_ID,pingjia,user_ID,pingjia_time from pingjia_table";
		try {
			ps = conn.prepareStatement(qurySql);
			
			//获取头部信息
			//ResultSetMetaData headData = ps.getMetaData();
			
			//获取内容
			rs = ps.executeQuery();
			while(rs.next()){
				Edingdanpingjia ddpjEnt = new Edingdanpingjia(
						rs.getInt("jingdian_ID"),
						rs.getInt("xingcheng_ID"),
						rs.getInt("user_ID"),
						rs.getString("pingjia"),
						rs.getString("pingjia_time"));
				ddpjList.add(ddpjEnt);
			}
			DBCPUtil.closeRs(conn, rs, ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ddpjList;
	}

	@Override
	public List<Edingdanpingjia> qurtlistbyjingdian_ID(int xingcheng_ID) {
		return list1("xingcheng_ID",xingcheng_ID);
	}

	@Override
	public List<Edingdanpingjia> qurtlistbyuser_ID(int user_ID) {
		return list1("user_ID",user_ID);
	}

	@Override
	public List<Edingdanpingjia> qurtlistbypingjia(String pingjia) {
		return list1("pingjia",pingjia);
	}

	@Override
	public List<Edingdanpingjia> qurtlistbypingjia_time(String pingjia_time) {
		return list1("pingjia_time",pingjia_time);
	}
}
