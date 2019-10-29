package com.zhuji.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;



import com.zhuji.dao.IDindan;
import com.zhuji.entity.Dindan;
import com.zhuji.entity.Pinglun_tupian;
import com.zhuji.util.DBCPUtil;

/***
 * 
 * @author jie
 * 
 */
public class DindanImpl implements IDindan{

	//新增订单
	@Override
	public boolean addDindan(Dindan DindanEnt){
		//定义对象
		Connection conn = null;
		PreparedStatement ps = null;
		conn=DBCPUtil.getConn();
		boolean bool =false;
		
		//添加
		String sqlAdd ="insert into dindan_table(dingdan_NO,dingdan_ID,user_ID,xianlu_ID,zifu_time,zifu_NO,dindanzongjia,zhuangtai) values(?,?,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sqlAdd);
			ps.setString(1, DindanEnt.getDingdan_NO());
			ps.setInt(2, DindanEnt.getDingdan_ID());
			ps.setInt(3, DindanEnt.getUser_ID());
			ps.setInt(4, DindanEnt.getXianlu_ID());
			ps.setString(5, DindanEnt.getZifu_time());
			ps.setString(6, DindanEnt.getZifu_NO());
			ps.setString(7, DindanEnt.getDindanzongjia());
			ps.setString(8, DindanEnt.getZhuangtai());
			
			int num = ps.executeUpdate();
			if (num>0) {
				
				System.out.println("添加成功,添加的订单信息为:"+DindanEnt.toString());

			}
							
		} catch (SQLException e) {
			System.out.println("预编译出错");
			e.printStackTrace();
		}finally {
			closeDB(null, ps, conn);
		}
				
		return bool;
	}

	private void closeDB(Object object, PreparedStatement ps, Connection conn) {
		// TODO Auto-generated method stub
		
	}
	//删除订单
	@Override
	public boolean deleteDindan(String dingdan_NO) {
		// 定义对象
		System.out.println("将要删除的订单编号为:"+dingdan_NO);
		Connection conn = null ;
		PreparedStatement ps = null;
		conn= DBCPUtil.getConn();
		boolean bool = false;
		String sqlDelete ="delete from dindan_table where dingdan_NO=?";
		try {
			ps = conn.prepareStatement(sqlDelete);
			ps.setString(1, dingdan_NO);
			
			int rowcount =ps.executeUpdate();
			if (rowcount>0) {
				
				bool=true;
				System.out.println("删除成功");
			}
		} catch (SQLException e) {
			System.out.println("预编译出错");
			e.printStackTrace();
		}finally {
			closeDB(null, ps, conn);
		}
		return false;
	}
	
	//更新订单,传入订单号，修改该订单的信息
//	@Override
//	public boolean deleteDindan(String user_ID) {
//		// TODO Auto-generated method stub
//		
//		return false;
//	}
	
	
	//更新订单,传入订单号，修改该订单的信息
	public boolean updateDindan(String dingdan_NO,  Dindan DindanEnt) {
		// 定义对象
		Connection conn = null;
		PreparedStatement ps = null;
		conn= DBCPUtil.getConn();
		boolean bool = false;
		//修改
		String sqlUddate = "update dindan_table set dingdan_NO=?,user_ID=?,xianlu_ID=?,zifu_time=?,zifu_NO=?,dindanzongjia=?,zhuangtai=?";
		try {
			ps=conn.prepareStatement(sqlUddate);
			//赋值
			ps.setString(1, DindanEnt.getDingdan_NO());
			ps.setInt(2, DindanEnt.getUser_ID());
			ps.setInt(3, DindanEnt.getXianlu_ID());
			ps.setString(4, DindanEnt.getZifu_time());
			ps.setString(5, DindanEnt.getZifu_NO());
			ps.setString(6, DindanEnt.getDindanzongjia());
			ps.setString(7, DindanEnt.getZhuangtai());
			int rowNum = ps.executeUpdate();
			if (rowNum>0) {
				bool=true;
				System.out.println("修改成功");
			}else {
				System.out.println("修改失败！该用户可能不存在");
			}
			
		} catch (SQLException e) {
			System.out.println("预编译出错");
			e.printStackTrace();
		}finally {
			closeDB(null, ps, conn);
		}
		
		return false;
	}

	@Override
	public List<Dindan> queryDindans() {
		//首先定义此方法将要用到的对象
		Connection conn = null;
		//sql语句预编译对象
		PreparedStatement ps = null;
		//结果集对象
		ResultSet rs = null;
		ArrayList<Dindan> list = new ArrayList<Dindan>();	//存放返回的网站对象
		String sql = "select * from dindan_table"; 		//sql查询语句
		
		//连接数据库，查询数据
		//创建一个JDBCUtil类的对象,并使用其连接方法
		conn= DBCPUtil.getConn();
		//用此连接对象的方法将sql语句预编译,得到一个预编译对象
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.println("预编译sql语句失败！");
			e.printStackTrace();
		}
		//预编译完成后，执行sql语句,得到结果集
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("查询失败!");
			e.printStackTrace();
		}
		//遍历并封装结果集
		try {
			while (rs.next()){
				Dindan Dindan = new Dindan(rs.getString("dingdan_NO"),
											  
											  rs.getInt("user_ID"),
											  rs.getInt("xianlu_ID"),
											  rs.getString("zifu_time"),
											  rs.getString("zifu_NO"),
											  rs.getString("dindanzongjia"),
											  rs.getString("zhuangtai"));
				list.add(Dindan);
			}
			
			
		} catch (SQLException e) {
			System.out.println("遍历并封装结果集错误");
			e.printStackTrace();
		}finally {
			closeDB(rs, ps, conn);
		}
		return list;
	}
	
	//通过订单查询，返回单个user对象
	@Override
	public Dindan querydingdanByDindan_NO(String dingdan_NO) {
		//首先定义此方法将要用到的对象
		Connection conn = DBCPUtil.getConn();
		//sql语句预编译对象
		PreparedStatement ps = null;
		//结果集对象
		ResultSet rs = null;
		String sql = "select * from dindan_table where dingdan_NO = ?";
		try {
			ps= conn.prepareStatement(sql);
			ps.setString(1, dingdan_NO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs=ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//遍历封装结果集	

		Dindan dingdan= null;
		try {
			while (rs.next()) {
				dingdan = new Dindan(
						rs.getInt("dingdan_ID"),
						rs.getString("dingdan_NO"), 
						rs.getInt("user_ID"),
						rs.getInt("xianlu_ID"),
						rs.getString("zifu_time"),
						rs.getString("zifu_NO"), 
						rs.getString("dindanzongjia"),
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
		return dingdan;
	}

	//资源关闭连接
		public void closeDB(ResultSet rs , PreparedStatement pstmt,Connection conn){
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}				
		}

	public static void main(String[] args) {
		DindanImpl dao = new DindanImpl();
//		boolean bool = dao.addDindan(new Dindan("15154", 3, 4, "3444", "sa2dfs", "sadgfs", "dr"));
//		System.out.println("添加"+bool);
		
		
//		boolean bool1 =dao.deleteDindan("346456");
//		System.out.println("删除"+bool1);
		
//		boolean bool = dao.updateDindan("22", new Dindan("12333", 3453, 434, "344344", "sa242dfs", "sa54dgfs", "dr"));
//		System.out.println("修改"+bool);
		
//		List<Dindan> list = dao.queryDindans();
//		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//			Dindan dindan = (Dindan) iterator.next();
//			System.out.println(dindan);
//		}
//		
//		Dindan dingdan = dao.querydingdanByDindan_NO("12334");
//		System.out.println(dingdan);
		
	}


			
			
			
			
		
}
