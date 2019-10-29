package com.zhuji.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhuji.dao.IYoujiShaer;
import com.zhuji.entity.Jingdian;
import com.zhuji.entity.YoujiShare;
import com.zhuji.util.DBCPUtil;

public class YoujiShareIMPL implements IYoujiShaer {

	@Override
	public boolean addYoujiShare(YoujiShare youjiShare) {
		//定义对象
		Connection conn = DBCPUtil.getConn() ;
		PreparedStatement ps = null;
		ResultSet rs = null;
		YoujiShareIMPL dao = new YoujiShareIMPL();
		boolean bool = false;
		String sqlAdd = "insert into youji_table(youji_Adress,youji_time,youji_Share) values(?,?,?)";
		try {
			ps = conn.prepareStatement(sqlAdd);
			
			ps.setString(1, youjiShare.getYouji_Adress());
			ps.setString(2, youjiShare.getYouji_time());
			ps.setString(3, youjiShare.getYouji_Share());
				
			
		} catch (SQLException e) {
			System.out.println("预编译出错");
			e.printStackTrace();
		}
		try {
			int num = ps.executeUpdate();
			if (num>0) {
				bool = true ;
				System.out.println("添加成功,添加的元素为:"+youjiShare.toString());
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
	public boolean deleteYoujiShare(int youji_ID) {
		
		System.out.println("将要删除的为:"+youji_ID);
		Connection conn = null ;
		PreparedStatement ps = null;
		conn = DBCPUtil.getConn();
		boolean bool = false;
		//修改;
		String sqlDelete = "delete from youji_table where youji_ID=?";
		try {
			ps = conn.prepareStatement(sqlDelete);
			ps.setInt(1, youji_ID);
			
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
	public boolean updataJindian(int youji_ID, YoujiShare youjiShare) {
		
		Connection conn = DBCPUtil.getConn();
		PreparedStatement ps = null;
		boolean bool = false;
		//修改
		String sqlUddate = "update youji_table set youji_Adress=?,youji_time=?,youji_Share=? where youji_ID=?";
		try {
			ps = conn.prepareStatement(sqlUddate);
			ps.setString(1, youjiShare.getYouji_Adress());
			ps.setString(2, youjiShare.getYouji_time());
			ps.setString(3, youjiShare.getYouji_Share());
			
			ps.setInt(4, youji_ID);
				
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
	public List<YoujiShare> queryYoujiShare() {
		
		Connection conn = DBCPUtil.getConn();
		//sql语句预编译对象
		PreparedStatement ps = null;
		//结果集对象
		ResultSet rs = null;
		ArrayList<YoujiShare> list = new ArrayList<YoujiShare>();
		String sql = "select * from youji_table";
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
				YoujiShare youjiShare = new YoujiShare(
												rs.getInt("youji_ID"),
												rs.getString("youji_Adress"), 
												rs.getString("youji_time"),
												rs.getString("youji_Share"));
												
				list.add(youjiShare);
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

//	@Override
//	public List<YoujiShare> queryJindianByName(String youji_Adress) {
//		
//		return null;
//	}
//	public static void main(String[] args) {
//		YoujiShareIMPL dao=new YoujiShareIMPL();
//		boolean bool = dao.addYoujiShare(new YoujiShare("dads", "sas", "s大是大非第三代"));
//		System.out.println("添加"+bool);}


}
