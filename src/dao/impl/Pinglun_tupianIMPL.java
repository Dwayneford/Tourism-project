package com.zhuji.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.zhuji.dao.Ipinglun_tupian;
import com.zhuji.entity.Hezuoshangjia_tupian;
import com.zhuji.entity.Pinglun_tupian;
import com.zhuji.util.DBCPUtil;

public class Pinglun_tupianIMPL implements Ipinglun_tupian{

	@Override
	public boolean addPinglun_tupian(Pinglun_tupian pinglun_tupian) {
		//定义对象
		Connection conn = DBCPUtil.getConn() ;
		PreparedStatement ps = null;
		ResultSet rs = null;
		HezuoshangjiaIMPL dao = new HezuoshangjiaIMPL();
		boolean bool = false;
//		Pinglun_tupian pinglun_tupian1 =new Pinglun_tupian(xingcheng_ID, tupianURL, zhuangtai);
		String sqlAdd = "insert into pinlun_tupian_table(xingcheng_ID,tupianURL,zhuangtai) values(?,?,?)";
		try {
			ps = conn.prepareStatement(sqlAdd);
			ps.setInt(1, pinglun_tupian.getXingcheng_ID());
			ps.setString(2, pinglun_tupian.getTupianURL());
			ps.setString(3, pinglun_tupian.getZhuangtai());
		} catch (SQLException e) {
			System.out.println("预编译出错");
			e.printStackTrace();
		}
		try {
			int num = ps.executeUpdate();
			if (num>0) {
				bool = true ;
				System.out.println("添加成功,添加的元素为:"+pinglun_tupian.toString());
			}
		} catch (SQLException e) {
			System.out.println("执行sql语句出错");
			e.printStackTrace();
		}finally {
			try {
				DBCPUtil.closeRs(conn, rs, ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return bool;
	}

	@Override
	public boolean deletePinglun_tupian(int ID) {
		//定义对象
		System.out.println("将要删除的评论图片ID:"+ID);
		Connection conn = null ;
		PreparedStatement ps = null;
		conn = DBCPUtil.getConn();
		boolean bool = false;
		//修改;
		String sqlDelete = "delete from pinlun_tupian_table where ID=?";
		try {
			ps = conn.prepareStatement(sqlDelete);
			ps.setInt(1, ID);
			
			int rowcount =ps .executeUpdate();
			if (rowcount>0) {
				
				bool=true;
				System.out.println("删除成功");
			}
		} catch (SQLException e) {
			System.out.println("预编译出错");
			e.printStackTrace();
		}finally {
			try {
				DBCPUtil.closeRs(conn, null, ps);
			} catch (SQLException e) {
				System.out.println("资源关闭失败!");
				e.printStackTrace();
			}
		}					
		return bool;
	}

	@Override
	public boolean updatePinglun_tupian(int ID, Pinglun_tupian pinglun_tupian) {
		//定义对象
		Connection conn = DBCPUtil.getConn();
		PreparedStatement ps = null;
		boolean bool = false;
		//修改
//		Pinglun_tupian pinglun_tupian1 =new Pinglun_tupian(xingcheng_ID, tupianURL, zhuangtai);

		String sqlUddate = "update pinlun_tupian_table set xingcheng_ID=?,tupianURL=?,zhuangtai=? where ID= ?";
		try {
			ps = conn.prepareStatement(sqlUddate);
			ps.setInt(1, pinglun_tupian.getXingcheng_ID());
			ps.setString(2, pinglun_tupian.getTupianURL());
			ps.setString(3, pinglun_tupian.getZhuangtai());
			ps.setInt(4, ID);
			
			
		} catch (SQLException e) {
			System.out.println("预编译失败");
			e.printStackTrace();
		}		
		try {
			int rowNum = ps.executeUpdate();
			if (rowNum>0) {
				bool=true;
				System.out.println("修改成功");
			}else {
				System.out.println("修改失败！该商家可能不存在");
			}
		} catch (SQLException e) {
			System.out.println("执行sql语句失败");
			e.printStackTrace();
		}
		
		return bool;
	}

	@Override
	public List<Pinglun_tupian> queryPinglun_tupians() {
		//首先定义此方法将要用到的对象
		Connection conn = DBCPUtil.getConn();
		//sql语句预编译对象
		PreparedStatement ps = null;
		//结果集对象
		ResultSet rs = null;
		ArrayList<Pinglun_tupian> list = new ArrayList<Pinglun_tupian>();
		String sql = "select * from pinlun_tupian_table";
		try {
			ps= conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs=ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//遍历封装结果集
//		Pinglun_tupian pinglun_tupian1 =new Pinglun_tupian(xingcheng_ID, tupianURL, zhuangtai);

		try {
			while (rs.next()) {
				Pinglun_tupian pinglun_tupian = new Pinglun_tupian(
												rs.getInt("ID"),
												rs.getInt("xingcheng_ID"),
												rs.getString("tupianURL"),
												rs.getString("zhuangtai"));
				list.add(pinglun_tupian);
			}
		} catch (SQLException e) {
			System.out.println("遍历并封装结果集错误");
			e.printStackTrace();
		}finally {
			try {
				DBCPUtil.closeRs(conn, rs, ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
				
		return list;
	}

	@Override
	public Pinglun_tupian queryPinglun_tupianByxingcheng_ID(int xingcheng_ID) {
		//首先定义此方法将要用到的对象
		Connection conn = DBCPUtil.getConn();
		//sql语句预编译对象
		PreparedStatement ps = null;
		//结果集对象
		ResultSet rs = null;
		String sql = "select * from pinlun_tupian_table where xingcheng_ID=?";
		try {
			ps= conn.prepareStatement(sql);
			ps.setInt(1, xingcheng_ID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs=ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//遍历封装结果集	
//		Pinglun_tupian pinglun_tupian1 =new Pinglun_tupian(xingcheng_ID, tupianURL, zhuangtai);

		Pinglun_tupian pinglun_tupian= null;
		try {
			while (rs.next()) {
				pinglun_tupian = new Pinglun_tupian(
						rs.getInt("ID"),
						rs.getInt("xingcheng_ID"), 
						rs.getString("tupianURL"),	
						rs.getString("zhuangtai"));				
			}			
		} catch (SQLException e) {
			System.out.println("遍历封装出错");
			e.printStackTrace();
		}finally {
			try {
				DBCPUtil.closeRs(conn, rs, ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return pinglun_tupian;
	}

	@Override
	public Pinglun_tupian queryPinglun_tupianByID(int ID) {
		//首先定义此方法将要用到的对象
		Connection conn = DBCPUtil.getConn();
		//sql语句预编译对象
		PreparedStatement ps = null;
		//结果集对象
		ResultSet rs = null;
		String sql = "select * from pinlun_tupian_table where ID=?";
		try {
			ps= conn.prepareStatement(sql);
			ps.setInt(1, ID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs=ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//遍历封装结果集	
//				Pinglun_tupian pinglun_tupian1 =new Pinglun_tupian(xingcheng_ID, tupianURL, zhuangtai);

		Pinglun_tupian pinglun_tupian= null;
		try {
			while (rs.next()) {
				pinglun_tupian = new Pinglun_tupian(
						rs.getInt("ID"),
						rs.getInt("xingcheng_ID"), 
						rs.getString("tupianURL"),	
						rs.getString("zhuangtai"));				
			}			
		} catch (SQLException e) {
			System.out.println("遍历封装出错");
			e.printStackTrace();
		}finally {
			try {
				DBCPUtil.closeRs(conn, rs, ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return pinglun_tupian;
	}
	//测试方法
	public static void main(String[] args) {
		Pinglun_tupianIMPL dao = new Pinglun_tupianIMPL();
//		boolean bool = dao.addPinglun_tupian(new Pinglun_tupian(1234, "sadgg", "sdg"))	;	
//		System.out.println("添加"+bool);
		
//		boolean bool= dao.deletePinglun_tupian(2);
//		System.out.println("添加"+bool);
		
//		boolean bool= dao.updatePinglun_tupian(1, new Pinglun_tupian(235, "asdfurl", "zt"));
//		System.out.println("添加"+bool);
		
//		List<Pinglun_tupian> list = dao.queryPinglun_tupians();
//		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//			Pinglun_tupian pinglun_tupian = (Pinglun_tupian) iterator.next();
//			System.out.println(pinglun_tupian);
//		}
		
//		Pinglun_tupian Pinglun_tupian=dao.queryPinglun_tupianByxingcheng_ID(235);
//		System.out.println(Pinglun_tupian);
		
		Pinglun_tupian Pinglun_tupian= dao.queryPinglun_tupianByID(1);
		System.out.println(Pinglun_tupian);
	}
	

}
