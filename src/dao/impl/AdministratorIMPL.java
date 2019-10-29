package com.zhuji.dao.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.zhuji.dao.IAdministrator;
import com.zhuji.entity.Administrator;

import com.zhuji.util.DBCPUtil;
import com.zhuji.util.MD5Encoder;

public class AdministratorIMPL implements IAdministrator{

	@Override
	public boolean addAdministrator(Administrator administrator) {
		//定义对象
		Connection conn =DBCPUtil.getConn();
		PreparedStatement ps = null;
		boolean bool = false;
		//判断该手机号是否存在
		AdministratorIMPL dao=new AdministratorIMPL();
		Administrator list=dao.queryAdministratorByTel(administrator.getadmin_tel());
		if (list == null) {
			String sqlAdd = "insert into guanliyue_table(admin_name, admin_pwd, admin_tel) values(?,?,?)";
			
		
			try {
				ps= conn.prepareStatement(sqlAdd);
				ps.setString(1, administrator.getadmin_name());
				
				try {
					ps.setString(2, MD5Encoder.getEncryptedPwd(administrator.getadmin_pwd()));
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				ps.setString(3, administrator.getadmin_tel());
				
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			
			//运行sql
			int rowNum;
			try {
				rowNum = ps.executeUpdate();
				if (rowNum>0) {
					bool=true;
					System.out.println("添加成功,添加的元素为:"+administrator.toString());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					DBCPUtil.closeRs(conn, null, ps);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		
		return bool;
	}

	@Override
	public boolean deleteAdministrator(int admin_id) {
		//定义对象
		System.out.println("将要删除的用户为:"+admin_id);
		Connection conn = null ;
		PreparedStatement ps = null;
		conn = DBCPUtil.getConn();
		boolean bool = false;
		//修改;
		String sqlDelete = "delete from guanliyue_table where admin_id=?";
		try {
			ps = conn.prepareStatement(sqlDelete);
			ps.setInt(1, admin_id);
			
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
				e.printStackTrace();
			}
		}				
		return bool;
	}

	@Override
	public boolean updataAdministrator(int admin_id, Administrator administrator) {
		Connection conn = null ;
		PreparedStatement ps = null;
		conn = DBCPUtil.getConn();
		boolean bool = false;
		
		String sqlDelete = "delete from guanliyue_table where admin_id=?";
		try {
			ps =conn.prepareStatement(sqlDelete);
			ps.setInt(1, admin_id);
			int rowcount =ps .executeUpdate();
			if (rowcount>0) {				
				bool=true;
				System.out.println("删除成功");
		} 
		}
			catch (SQLException e) {
			System.out.println("预编译出错");
			e.printStackTrace();
		}
		finally {
			try {
				DBCPUtil.closeRs(conn, null, ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}				
		return bool;
	}

	@Override
	public List<Administrator> queryAdministrator() {
		//首先定义此方法将要用到的对象
		Connection conn = null;
		//sql语句预编译对象
		PreparedStatement ps = null;
		//结果集对象
		ResultSet rs = null;
		
		List<Administrator> list = new ArrayList<Administrator>();	
		String sql = "select * from guanliyue_table";
		
		//连接数据库，查询数据
		//创建一个JDBCUtil类的对象,并使用其连接方法
		conn = DBCPUtil.getConn();
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
			while (rs.next()) {
				Administrator str = new Administrator(
								rs.getInt("admin_id"),
								rs.getString("admin_name"),
								rs.getString("admin_pwd"),
								rs.getString("admin_role"), 
								rs.getString("admin_tel"),
								rs.getString("admin_email"),
								rs.getString("admin_photo_url"), 
								rs.getString("admin_time"));
				
				list.add(str);
			}
		} catch (SQLException e) {
			System.out.println("遍历并封装结果集错误");
			e.printStackTrace();
		}finally {
			try {
				DBCPUtil.closeRs(conn, rs, ps);
			} catch (SQLException e) {
				// 
				e.printStackTrace();
			}
		}
		return list;
	
	}
	@Override
	public Administrator queryAdministratorByName(String admin_name) {
		//定义对象
				Connection conn = DBCPUtil.getConn() ;
				PreparedStatement ps = null;
				ResultSet rs = null;
				Administrator list = null;
				String sql = "select * from guanliyue_table where admin_name = ?";
				//连接数据库查询数据
				try {
					ps= conn.prepareStatement(sql);
					ps.setString(1, admin_name);
				} catch (SQLException e) {
					// 
					e.printStackTrace();
				}
				try {
					rs = ps.executeQuery();
				} catch (SQLException e) {
					// 
					e.printStackTrace();
				}
				try {
					while (rs.next()) {
						list = new Administrator(
								rs.getInt("admin_id"),
								rs.getString("admin_name"),
								rs.getString("admin_pwd"),
								rs.getString("admin_role"), 
								rs.getString("admin_tel"),
								rs.getString("admin_email"),
								rs.getString("admin_photo_url"), 
								rs.getString("admin_time"));						
					}
				} catch (SQLException e) {
					// 
					e.printStackTrace();
				}finally {
					try {
						DBCPUtil.closeRs(conn, rs, ps);
					} catch (SQLException e) {
						// 
						e.printStackTrace();
					}
				}
				
				return list;
	}
	@Override
	public Administrator queryAdministratorByTel(String admin_tel) {
		
		//定义对象
		Connection conn = DBCPUtil.getConn() ;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Administrator list = null;
		String sql = "select * from guanliyue_table where admin_tel = ?";
		//连接数据库查询数据
		try {
			ps= conn.prepareStatement(sql);
			ps.setString(1, admin_tel);
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				list = new Administrator(
						rs.getInt("admin_id"),
						rs.getString("admin_name"),
						rs.getString("admin_pwd"),
						rs.getString("admin_role"), 
						rs.getString("admin_tel"),
						rs.getString("admin_email"),
						rs.getString("admin_photo_url"), 
						rs.getString("admin_time"));						
			}
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		}finally {
			try {
				DBCPUtil.closeRs(conn, rs, ps);
			} catch (SQLException e) {
				// 
				e.printStackTrace();
			}
		}
		
		return list;
	}
	public boolean administratorLogin(String name, String psw) {
		AdministratorIMPL dao = new AdministratorIMPL();
		//提取此用户在数据库中的信息
		Administrator administrator=dao.queryAdministratorByName(name);
		boolean bool = false;
		try {
			//将输入的密码与数据库中的密码对比
			bool= MD5Encoder.validPassword(psw, administrator.getadmin_pwd());
			if (bool) {
				System.out.println("登录成功,用户名为："+administrator.getadmin_name());
			}else {
				System.out.println("登录失败!");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return bool;

	}
//	public static void main(String[] args) {
//		AdministratorIMPL dao = new AdministratorIMPL();
//		Administrator administrator = dao.queryAdministratorByTel("15223532333");
//		System.out.println(administrator.getadmin_name());
		
//		boolean bool = dao.addAdministrator(new Administrator("sdas", "13431","13431"));
//		
//		System.out.println("添加"+bool);
		
//		boolean bool = dao.deleteAdministrator(2);
//		System.out.println("删除"+bool);
		
//		List<Administrator> list =dao.queryAdministrator();
//		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//			Administrator str = (Administrator) iterator.next();
//			System.out.println(str.getadmin_name());
//		}
//		boolean bool = dao.administratorLogin("sdas", "13431");
//		System.out.println("登录"+bool);
//		
//		
//	}

}
