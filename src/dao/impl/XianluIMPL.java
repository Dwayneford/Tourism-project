package com.zhuji.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.zhuji.dao.IXianlu;
import com.zhuji.entity.Hezuoshangjia;
import com.zhuji.entity.Xianlu;
import com.zhuji.util.DBCPUtil;
/**
 *  @author lhq
 */
public class XianluIMPL implements IXianlu {

	@Override
	public boolean addXianlu(Xianlu xianlu) {
		//定义对象
				Connection conn = DBCPUtil.getConn() ;
				PreparedStatement ps = null;
				ResultSet rs = null;
				XianluIMPL dao = new XianluIMPL();
				boolean bool = false;
				String sqlAdd = "insert into xianlu_table(jingdian_ID,pingjia) values(?,?)";
				try {
					ps = conn.prepareStatement(sqlAdd);
					ps.setInt(1, xianlu.getJingdian_ID());
					ps.setString(2, xianlu.getPingjia());
					
				} catch (SQLException e) {
					System.out.println("预编译出错");
					e.printStackTrace();
				}
				try {
					int num = ps.executeUpdate();
					if (num>0) {
						bool = true ;
						System.out.println("添加成功,添加的元素为:"+xianlu.toString());
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
	public boolean deleteXianlu(int xianlu_ID) {
		//定义对象
				System.out.println("将要删除线路id:"+xianlu_ID);
				Connection conn = null ;
				PreparedStatement ps = null;
				conn = DBCPUtil.getConn();
				boolean bool = false;
				//修改;
				String sqlDelete = "delete from xianlu_table where xianlu_ID=?";
				try {
					ps = conn.prepareStatement(sqlDelete);
					ps.setInt(1, xianlu_ID);
					
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
	public boolean updataXianlu(int xianlu_ID,Xianlu xianlu) {
		//定义对象
				Connection conn = DBCPUtil.getConn();
				PreparedStatement ps = null;
				boolean bool = false;
				//修改
				String sqlUddate = "update xianlu_table set jingdian_ID=?,pingjia=? where xianlu_ID=?";
				try {
					ps = conn.prepareStatement(sqlUddate);
					ps.setInt(1, xianlu.getJingdian_ID());
					ps.setString(2, xianlu.getPingjia());
					ps.setInt(3, xianlu_ID);
					
					
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
						System.out.println("修改失败！该景点可能不存在");
					}
				} catch (SQLException e) {
					System.out.println("执行sql语句失败");
					e.printStackTrace();
				}
				
				
				
				return bool;
	}

	@Override
	public List<Xianlu> queryXianlu() {
		//首先定义此方法将要用到的对象
				Connection conn = DBCPUtil.getConn();
				//sql语句预编译对象
				PreparedStatement ps = null;
				//结果集对象
				ResultSet rs = null;
				ArrayList<Xianlu> list = new ArrayList<Xianlu>();
				String sql = "select * from xianlu_table";
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
						Xianlu xianlu = new Xianlu(
														rs.getInt("xianlu_ID"),
														rs.getInt("jingdian_ID"), 														 
														rs.getString("pingjia"));
						list.add(xianlu);
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
	public List<Xianlu> queryXianluByName(int xianlu_ID) {
		//首先定义此方法将要用到的对象
				Connection conn = DBCPUtil.getConn();
				//sql语句预编译对象
				PreparedStatement ps = null;
				//结果集对象
				ResultSet rs = null;
				ArrayList<Xianlu> list = new ArrayList<Xianlu>();
				String sql = "select * from xianlu_table where xianlu_ID=?";
				try {
					ps= conn.prepareStatement(sql);
					ps.setInt(1, xianlu_ID);
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
						Xianlu xianlu = new Xianlu(
														rs.getInt("xianlu_ID"),
														rs.getInt("jingdian_ID"),  
														rs.getString("pingjia"));
						list.add(xianlu);
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
	public static void main(String[] args) {
		XianluIMPL dao=new XianluIMPL();
//		boolean bool = dao.addXianlu(new Xianlu(6, "1ag"));
//		System.out.println("添加"+bool);
//		
//		boolean bool = dao.deleteXianlu(1);
//		System.out.println("删"+bool);
		
//		boolean bool = dao.updataXianlu(2, new Xianlu(4, "dfa"));
//		System.out.println("改"+bool);	
		
//		List<Xianlu> list=dao.queryXianlu();
//		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//			Xianlu xianlu = (Xianlu) iterator.next();
//			System.out.println(xianlu.toString());
//		}
		
//		List<Xianlu> list =dao.queryXianluByName(3);
//		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//			Xianlu xianlu = (Xianlu) iterator.next();
//			System.out.println(xianlu.toString());
//		
//	}

   }
}