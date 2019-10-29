package com.zhuji.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import com.zhuji.dao.IHezuoshangjia;
import com.zhuji.entity.Hezuoshangjia;
import com.zhuji.util.DBCPUtil;
/**
 * 
 * @author dyb
 *合作商家dao功能方法
 */
public class HezuoshangjiaIMPL implements IHezuoshangjia{

	
	
	@Override
	public boolean addHezuoshangjia(Hezuoshangjia hezuoshangjia) {
		//定义对象
		Connection conn = DBCPUtil.getConn() ;
		PreparedStatement ps = null;
		ResultSet rs = null;
		HezuoshangjiaIMPL dao = new HezuoshangjiaIMPL();
		boolean bool = false;
//		Hezuoshangjia hezuoshangjia= new Hezuoshangjia(mingcheng, leixing, dianhua, zhuangtai, dizhi, jingdian_ID, tupianID, jieshao, hezuoshijian, junjia)
		String sqlAdd = "insert into hezuoshangjia_table(mingcheng,leixing,dianhua,zhuangtai,dizhi,jingdian_ID,tupianID,jieshao,hezuoshijian,junjia) values(?,?,?,?,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sqlAdd);
			ps.setString(1, hezuoshangjia.getMingcheng());
			ps.setString(2, hezuoshangjia.getLeixing());
			ps.setString(3, hezuoshangjia.getDianhua());
			ps.setString(4, hezuoshangjia.getZhuangtai());
			ps.setString(5, hezuoshangjia.getDizhi());
			ps.setInt(6, hezuoshangjia.getJingdian_ID());
			ps.setInt(7, hezuoshangjia.getTupianID());
			ps.setString(8, hezuoshangjia.getJieshao());
			ps.setString(9, hezuoshangjia.getHezuoshijian());
			ps.setString(10, hezuoshangjia.getJunjia());
			
		} catch (SQLException e) {
			System.out.println("预编译出错");
			e.printStackTrace();
		}
		try {
			int num = ps.executeUpdate();
			if (num>0) {
				bool = true ;
				System.out.println("添加成功,添加的元素为:"+hezuoshangjia.toString());
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
	public boolean deleteHezuoshangjia(String dianhua) {
		//定义对象
		System.out.println("将要删除的商家的手机号为:"+dianhua);
		Connection conn = null ;
		PreparedStatement ps = null;
		conn = DBCPUtil.getConn();
		boolean bool = false;
		//修改;
		String sqlDelete = "delete from hezuoshangjia_table where dianhua=?";
		try {
			ps = conn.prepareStatement(sqlDelete);
			ps.setString(1, dianhua);
			
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
	public boolean updataHezuoshangjia(String dianhua ,Hezuoshangjia hezuoshangjia) {
		//定义对象
		Connection conn = DBCPUtil.getConn();
		PreparedStatement ps = null;
		boolean bool = false;
		//修改
		String sqlUddate = "update hezuoshangjia_table set mingcheng=?,leixing=?,dianhua=?,zhuangtai=?,dizhi=?,jingdian_ID=?,tupianID=?,jieshao=?,hezuoshijian=?,junjia=? where dianhua= ?";
		try {
			ps = conn.prepareStatement(sqlUddate);
			ps.setString(1, hezuoshangjia.getMingcheng());
			ps.setString(2, hezuoshangjia.getLeixing());
			ps.setString(3, hezuoshangjia.getDianhua());
			ps.setString(4, hezuoshangjia.getZhuangtai());
			ps.setString(5, hezuoshangjia.getDizhi());
			ps.setInt(6, hezuoshangjia.getJingdian_ID());
			ps.setInt(7, hezuoshangjia.getTupianID());
			ps.setString(8, hezuoshangjia.getJieshao());
			ps.setString(9, hezuoshangjia.getHezuoshijian());
			ps.setString(10, hezuoshangjia.getJunjia());
			ps.setString(11, dianhua);
			
			
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
	public List<Hezuoshangjia> queryHezuoshangjias() {
		//首先定义此方法将要用到的对象
		Connection conn = DBCPUtil.getConn();
		//sql语句预编译对象
		PreparedStatement ps = null;
		//结果集对象
		ResultSet rs = null;
		ArrayList<Hezuoshangjia> list = new ArrayList<Hezuoshangjia>();
		String sql = "select * from hezuoshangjia_table";
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
				Hezuoshangjia hezuoshangjia = new Hezuoshangjia(
												rs.getInt("shangjia_ID"),
												rs.getString("mingcheng"), 
												rs.getString("leixing"), 
												rs.getString("dianhua"),
												rs.getString("zhuangtai"), 
												rs.getString("dizhi"), 
												rs.getInt("jingdian_ID"),
												rs.getInt("tupianID"),
												rs.getString("jieshao"), 
												rs.getString("hezuoshijian"), 
												rs.getString("junjia"));
				list.add(hezuoshangjia);
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
	public List<Hezuoshangjia> queryHezuoshangjiaByName(String mingcheng) {
		//首先定义此方法将要用到的对象
		Connection conn = DBCPUtil.getConn();
		//sql语句预编译对象
		PreparedStatement ps = null;
		//结果集对象
		ResultSet rs = null;
		ArrayList<Hezuoshangjia> list = new ArrayList<Hezuoshangjia>();
		String sql = "select * from hezuoshangjia_table where mingcheng=?";
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
				Hezuoshangjia hezuoshangjia = new Hezuoshangjia(
												rs.getInt("shangjia_ID"),
												rs.getString("mingcheng"), 
												rs.getString("leixing"), 
												rs.getString("dianhua"),
												rs.getString("zhuangtai"), 
												rs.getString("dizhi"), 
												rs.getInt("jingdian_ID"),
												rs.getInt("tupianID"),
												rs.getString("jieshao"), 
												rs.getString("hezuoshijian"), 
												rs.getString("junjia"));
				list.add(hezuoshangjia);
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
	public List<Hezuoshangjia> queryHezuoshangjiaByType(String leixing) {
		//首先定义此方法将要用到的对象
				Connection conn = DBCPUtil.getConn();
				//sql语句预编译对象
				PreparedStatement ps = null;
				//结果集对象
				ResultSet rs = null;
				ArrayList<Hezuoshangjia> list = new ArrayList<Hezuoshangjia>();
				String sql = "select * from hezuoshangjia_table where leixing=?";
				try {
					ps= conn.prepareStatement(sql);
					ps.setString(1, leixing);
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
						Hezuoshangjia hezuoshangjia = new Hezuoshangjia(
														rs.getInt("shangjia_ID"),
														rs.getString("mingcheng"), 
														rs.getString("leixing"), 
														rs.getString("dianhua"),
														rs.getString("zhuangtai"), 
														rs.getString("dizhi"), 
														rs.getInt("jingdian_ID"),
														rs.getInt("tupianID"),
														rs.getString("jieshao"), 
														rs.getString("hezuoshijian"), 
														rs.getString("junjia"));
						list.add(hezuoshangjia);
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
	
	//测试方法
	public static void main(String[] args) {
		HezuoshangjiaIMPL dao = new HezuoshangjiaIMPL();
//		Hezuoshangjia hezuoshangjia = new Hezuoshangjia(mingcheng, leixing, dianhua, zhuangtai, dizhi, jingdian_ID, tupianID, jieshao, hezuoshijian, junjia);
		
//		boolean bool = dao.addHezuoshangjia(new Hezuoshangjia("mingcheng", "leixing"," dianhua", "zhuangtai", "dizhi", 10001, 10001, "jieshao", "hezuoshijian", "junjia"));
//		System.out.println("添加"+bool);
		
//		boolean bool = dao.deleteHezuoshangjia("asd");
//		System.out.println("删除"+bool);
		
//		boolean bool = dao.updataHezuoshangjia("523", new Hezuoshangjia("mingcheng2", "leixing2"," dianhua2", "zhuangtai2", "dizhi", 10002, 10002, "jieshao2", "hezuoshijian2", "junjia2"));
//		System.out.println("修改"+bool);
		
//		List<Hezuoshangjia> list = dao.queryHezuoshangjias();
//		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//			Hezuoshangjia hezuoshangjia = (Hezuoshangjia) iterator.next();
//			System.out.println(hezuoshangjia.toString());
//		}
		
//		List<Hezuoshangjia> list = dao.queryHezuoshangjiaByName("asd");
//		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//			Hezuoshangjia hezuoshangjia = (Hezuoshangjia) iterator.next();
//			System.out.println(hezuoshangjia.toString());
//		}
		
		List<Hezuoshangjia> list = dao.queryHezuoshangjiaByType("leixing");
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Hezuoshangjia hezuoshangjia = (Hezuoshangjia) iterator.next();
			System.out.println(hezuoshangjia.toString());
		}
		
	}
}
