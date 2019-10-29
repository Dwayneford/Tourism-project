package com.zhuji.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.zhuji.dao.IHezuoshangjia_tupian;
import com.zhuji.entity.Hezuoshangjia;
import com.zhuji.entity.Hezuoshangjia_tupian;
import com.zhuji.util.DBCPUtil;

/**
 * 
 * @author DYB
 *
 */
public class Hezuoshangjia_tupianIMPL implements IHezuoshangjia_tupian {

	@Override
	public boolean addHezuoshangjia_tupian(Hezuoshangjia_tupian hezuoshangjia_tupian) {
		//定义对象
		Connection conn = DBCPUtil.getConn() ;
		PreparedStatement ps = null;
		ResultSet rs = null;
		HezuoshangjiaIMPL dao = new HezuoshangjiaIMPL();
		boolean bool = false;
//		hezuoshangjia_tupian hezuoshangjia_tupian= new Hezuoshangjia_tupian(shangjia_ID, tupianURL);
		String sqlAdd = "insert into hezuoshangjiatupian_table(shangjia_ID,tupianURL) values(?,?)";
		try {
			ps = conn.prepareStatement(sqlAdd);
			ps.setInt(1, hezuoshangjia_tupian.getShangjia_ID());
			ps.setString(2, hezuoshangjia_tupian.getTupianURL());
			
		} catch (SQLException e) {
			System.out.println("预编译出错");
			e.printStackTrace();
		}
		try {
			int num = ps.executeUpdate();
			if (num>0) {
				bool = true ;
				System.out.println("添加成功,添加的元素为:"+hezuoshangjia_tupian.toString());
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
	public boolean deleteHezuoshangjia_tupian(int tupian_ID) {
		//定义对象
		System.out.println("将要删除的商家图片ID:"+tupian_ID);
		Connection conn = null ;
		PreparedStatement ps = null;
		conn = DBCPUtil.getConn();
		boolean bool = false;
		//修改;
		String sqlDelete = "delete from hezuoshangjiatupian_table where tupian_ID=?";
		try {
			ps = conn.prepareStatement(sqlDelete);
			ps.setInt(1, tupian_ID);
			
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
	public boolean updataHezuoshangjia_tupian(int tupian_ID, Hezuoshangjia_tupian hezuoshangjia_tupian) {
		//定义对象
		Connection conn = DBCPUtil.getConn();
		PreparedStatement ps = null;
		boolean bool = false;
		//修改
		String sqlUddate = "update hezuoshangjiatupian_table set shangjia_ID=?,tupianURL=? where tupian_ID= ?";
		try {
			ps = conn.prepareStatement(sqlUddate);
			ps.setInt(1, hezuoshangjia_tupian.getShangjia_ID());
			ps.setString(2, hezuoshangjia_tupian.getTupianURL());
			
			ps.setInt(3, tupian_ID);
			
			
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
	public List<Hezuoshangjia_tupian> queryHezuoshangjia_tupians() {
		//首先定义此方法将要用到的对象
		Connection conn = DBCPUtil.getConn();
		//sql语句预编译对象
		PreparedStatement ps = null;
		//结果集对象
		ResultSet rs = null;
		ArrayList<Hezuoshangjia_tupian> list = new ArrayList<Hezuoshangjia_tupian>();
		String sql = "select * from hezuoshangjiatupian_table";
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
		try {
			while (rs.next()) {
				Hezuoshangjia_tupian hezuoshangjia_tupian = new Hezuoshangjia_tupian(
												rs.getInt("tupian_ID"),
												rs.getInt("shangjia_ID"), 
												rs.getString("tupianURL"));
				list.add(hezuoshangjia_tupian);
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
	public Hezuoshangjia_tupian queryHezuoshangjia_tupianByID(int tupian_ID) {
		//首先定义此方法将要用到的对象
		Connection conn = DBCPUtil.getConn();
		//sql语句预编译对象
		PreparedStatement ps = null;
		//结果集对象
		ResultSet rs = null;
		String sql = "select * from hezuoshangjiatupian_table where tupian_ID=?";
		try {
			ps= conn.prepareStatement(sql);
			ps.setInt(1, tupian_ID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs=ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//遍历封装结果集					
		Hezuoshangjia_tupian hezuoshangjia_tupian= null;
		try {
			while (rs.next()) {
				hezuoshangjia_tupian = new Hezuoshangjia_tupian(
						rs.getInt("tupian_ID"),
						rs.getInt("shangjia_ID"), 
						rs.getString("tupianURL"));				
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
		return hezuoshangjia_tupian;
		
	}
	//测试
	public static void main(String[] args) {
		Hezuoshangjia_tupianIMPL dao = new Hezuoshangjia_tupianIMPL();
//		dao.addHezuoshangjia_tupian(new Hezuoshangjia_tupian(23028, "testURL"));
//		dao.deleteHezuoshangjia_tupian(2);
		
//		dao.updataHezuoshangjia_tupian(3, new Hezuoshangjia_tupian(4896, "updatatest"));
		
//		List<Hezuoshangjia_tupian> list = dao.queryHezuoshangjia_tupians();
//		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//			Hezuoshangjia_tupian hezuoshangjia_tupian = (Hezuoshangjia_tupian) iterator.next();
//			System.out.println(hezuoshangjia_tupian);
//		}
		
//		Hezuoshangjia_tupian hezuoshangjia_tupian = dao.queryHezuoshangjia_tupianByID(3);
//		System.out.println(hezuoshangjia_tupian);
		
		
	}
	
}
