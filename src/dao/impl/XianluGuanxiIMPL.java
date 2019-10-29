package com.zhuji.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.zhuji.dao.IXainlu_jingdain_guanxi;
import com.zhuji.entity.Xainlu_jingdain_guanxi;
import com.zhuji.entity.Xianlu;
import com.zhuji.util.DBCPUtil;
/**
 *  @author lhq
 */
public class XianluGuanxiIMPL  implements IXainlu_jingdain_guanxi{

	@Override
	public boolean addguanxi(Xainlu_jingdain_guanxi  guanxi) {
		//定义对象
		Connection conn = DBCPUtil.getConn();
		PreparedStatement ps = null;
		boolean bool = false;
		//修改
		String sqlUddate = "insert into xainlu_jingdain_guanxi_table (xianlu_ID,jingdian_ID) values(?,?)";
		try {
			ps = conn.prepareStatement(sqlUddate);
			ps.setInt(1, guanxi.getJingdian_ID());
			ps.setInt(2, guanxi.getXianlu_ID());
			
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
	public boolean deleteguanxi(int ID) {
		//定义对象
		System.out.println("将要删除线路id:"+ID);
		Connection conn = null ;
		PreparedStatement ps = null;
		conn = DBCPUtil.getConn();
		boolean bool = false;
		//修改;
		String sqlDelete = "delete from xainlu_jingdain_guanxi_table where ID=?";
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
	public boolean updataguanxi( int ID ,Xainlu_jingdain_guanxi  guanxi) {
		//定义对象
				Connection conn = DBCPUtil.getConn();
				PreparedStatement ps = null;
				boolean bool = false;
				//修改
				String sqlUddate = "update xainlu_jingdain_guanxi_table set xianlu_ID=?,jingdian_ID=? where ID= ?";
				try {
					ps = conn.prepareStatement(sqlUddate);
					ps.setInt(1, guanxi.getXianlu_ID());
					ps.setInt(2, guanxi.getJingdian_ID());
					ps.setInt(3, ID);

					
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
	public List<Xainlu_jingdain_guanxi> queryguanxi() {
		//首先定义此方法将要用到的对象
		Connection conn = DBCPUtil.getConn();
		//sql语句预编译对象
		PreparedStatement ps = null;
		//结果集对象
		ResultSet rs = null;
		ArrayList<Xainlu_jingdain_guanxi> list = new ArrayList<Xainlu_jingdain_guanxi>();
		String sql = "select * from xainlu_jingdain_guanxi_table";
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
				Xainlu_jingdain_guanxi str = new Xainlu_jingdain_guanxi(
												rs.getInt("ID"),
												rs.getInt("xianlu_ID"), 														 
												rs.getInt("jingdian_ID"));
				list.add(str);
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
		XianluGuanxiIMPL dao=new XianluGuanxiIMPL();
//		boolean bool = dao.addguanxi(new Xainlu_jingdain_guanxi(3, 41));
//		System.out.println("添加"+bool);
		
//		boolean bool = dao.deleteguanxi(2);
//		System.out.println("删除"+bool);
		
//		boolean bool = dao.updataguanxi(3, new Xainlu_jingdain_guanxi(2, 4));
//		System.out.println("改"+bool);
		
//		List<Xainlu_jingdain_guanxi> list=dao.queryguanxi();
//		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//			Xainlu_jingdain_guanxi guanxi = (Xainlu_jingdain_guanxi) iterator.next();
//			System.out.println(guanxi.toString());
//		
//		
//	}

	

	}
}
