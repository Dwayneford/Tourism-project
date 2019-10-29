package com.zhuji.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.zhuji.util.DBCPUtil;

import com.zhuji.dao.IJindian;
import com.zhuji.entity.Hezuoshangjia;
import com.zhuji.entity.Jingdian;
/**
 *  @author lhq
 */
public class JindianIMPL implements IJindian {
	
//	Connection conn = null;
//	PreparedStatement ps = null;
//	ResultSet rs = null;
	//添加合作景点
	@Override
	public boolean addJindian(Jingdian jingdian) {
		//定义对象
				Connection conn = DBCPUtil.getConn() ;
				PreparedStatement ps = null;
				ResultSet rs = null;
				JindianIMPL dao = new JindianIMPL();
				boolean bool = false;
				String sqlAdd = "insert into jingdian_table(tupian_ID,mingcheng,dizhi,miaosu) values(?,?,?,?)";
				try {
					ps = conn.prepareStatement(sqlAdd);
					
					ps.setInt(1, jingdian.getTupian_ID());
					ps.setString(2, jingdian.getMingcheng());
					ps.setString(3, jingdian.getDizhi());
					ps.setString(4, jingdian.getMiaosu());	
					
				} catch (SQLException e) {
					System.out.println("预编译出错");
					e.printStackTrace();
				}
				try {
					int num = ps.executeUpdate();
					if (num>0) {
						bool = true ;
						System.out.println("添加成功,添加的元素为:"+jingdian.toString());
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
	//删除景点
	@Override
	public boolean deleteJindian(int jingdian_ID) {
		//定义对象
				System.out.println("将要删除的为:"+jingdian_ID);
				Connection conn = null ;
				PreparedStatement ps = null;
				conn = DBCPUtil.getConn();
				boolean bool = false;
				//修改;
				String sqlDelete = "delete from jingdian_table where jingdian_ID=?";
				try {
					ps = conn.prepareStatement(sqlDelete);
					ps.setInt(1, jingdian_ID);
					
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
	//修改景点
	@Override
	public boolean updataJindian(int jingdian_ID, Jingdian jingdian) {
		//定义对象
				Connection conn = DBCPUtil.getConn();
				PreparedStatement ps = null;
				boolean bool = false;
				//修改
				String sqlUddate = "update jingdian_table set tupian_ID=?,mingcheng=?,dizhi=?,miaosu=? where jingdian_ID=?";
				try {
					ps = conn.prepareStatement(sqlUddate);
					ps.setInt(1, jingdian.getTupian_ID());
					ps.setString(2, jingdian.getMingcheng());
					ps.setString(3, jingdian.getDizhi());
					ps.setString(4, jingdian.getMiaosu());
					ps.setInt(5, jingdian_ID);
						
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
	//查询所有景点
	@Override
	public List<Jingdian> queryJindian() {
		//首先定义此方法将要用到的对象
				Connection conn = DBCPUtil.getConn();
				//sql语句预编译对象
				PreparedStatement ps = null;
				//结果集对象
				ResultSet rs = null;
				ArrayList<Jingdian> list = new ArrayList<Jingdian>();
				String sql = "select * from jingdian_table";
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
						Jingdian jingdian = new Jingdian(
														rs.getInt("jingdian_ID"),
														rs.getInt("tupian_ID"), 
														rs.getString("mingcheng"), 
														rs.getString("dizhi"),
														rs.getString("miaosu"));
														
						list.add(jingdian);
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
	//按名称查询景点
	@Override
	public List<Jingdian> queryJindianByName(String mingcheng) {
		//首先定义此方法将要用到的对象
				Connection conn = DBCPUtil.getConn();
				//sql语句预编译对象
				PreparedStatement ps = null;
				//结果集对象
				ResultSet rs = null;
				ArrayList<Jingdian> list = new ArrayList<Jingdian>();
				String sql = "select * from jingdian_table where mingcheng=?";
				try {
					ps= conn.prepareStatement(sql);
					ps.setString(1, mingcheng);
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
						Jingdian jingdian = new Jingdian(
								rs.getInt("jingdian_ID"),
								rs.getInt("tupian_ID"), 
								rs.getString("mingcheng"), 
								rs.getString("dizhi"),
								rs.getString("miaosu"));
						list.add(jingdian);
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
		JindianIMPL dao=new JindianIMPL();
		//boolean bool = dao.addJindian(new Jingdian(10000, "mingcheng1", "dizhi1","miaosu1"));
		//System.out.println("添加"+bool);
		
		//boolean bool = dao.deleteJindian(2);
		//System.out.println("删除"+bool);
		
//		boolean bool = dao.updataJindian(3, new Jingdian(1999, "asd11", "das11", "fgg"));
//		System.out.println("修改"+bool);
		
//		List<Jingdian> list=dao.queryJindian();
//		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//			Jingdian jingdian = (Jingdian) iterator.next();
//			System.out.println(jingdian.toString());
//		}
		
//		List<Jingdian> list=dao.queryJindianByName("d");
//		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//			Jingdian jingdian = (Jingdian) iterator.next();
//			System.out.println(jingdian.toString());		
//	}
	
}
	/**
	 * 通过mingcheng查询数据
	 * 方法名：quryJindian
	 * @return 模糊查询
	 * @throws Exception */
//	@Override
//	public List<Jingdian> queryJindian(String mingcheng) {
//		List<Jingdian> list= new ArrayList<Jingdian>();
//		String qurySql = "SELECT * FROM jingdian_table  WHERE book_name LIKE '%"+mingcheng+"%'";;		
//		try {
//			ps = conn.prepareStatement(qurySql);
//			//获取头部信息
//			//ResultSetMetaData header = ps.getMetaData();			
//			//获取内容
//			rs = ps.executeQuery();			
//			while(rs.next()){
//				Jingdian jingdianlist = new Jingdian(
//						rs.getInt("jingdian_ID"),
//						rs.getInt("tupian_ID"),
//						rs.getString("mingcheng"),
//						rs.getString("dizhi"),
//						rs.getString("miaosu"));				
//				list.add(jingdianlist);
//			}
//		} catch (SQLException e) {			
//			e.printStackTrace();
//		}
//		try {
//			DBCPUtil.closeRs(conn, rs, ps);
//		} catch (SQLException e) {			
//			e.printStackTrace();
//		}		
//		return list;		
//	}
	

}
	

