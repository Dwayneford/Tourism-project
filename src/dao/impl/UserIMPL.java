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


import com.zhuji.dao.IUser;
import com.zhuji.entity.user;
import com.zhuji.util.DBCPUtil;
import com.zhuji.util.MD5Encoder;
/**
 * 
 * @author DYB
 *
 */
public class UserIMPL implements IUser{

	@Override
	public boolean addUser(user userEnt) {
		//定义对象
		Connection conn =DBCPUtil.getConn();
		PreparedStatement ps = null;
		boolean bool = false;
		//判断该手机号是否存在
		UserIMPL dao = new UserIMPL();
		user user = dao.queryUserByPhone(userEnt.getuser_telphone());
		if (user == null) {
			String sqlAdd = "insert into user_table(user_name, user_password, user_telphone) values(?,?,?)";
			//注册只需要姓名密码电话
//			user user = new user(user_name, user_password, usre_telphone);
			try {
				ps= conn.prepareStatement(sqlAdd);
				ps.setString(1, userEnt.getUser_name());
				
				try {
					ps.setString(2, MD5Encoder.getEncryptedPwd(userEnt.getUser_password()));
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				ps.setString(3, userEnt.getuser_telphone());
				
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			
			//运行sql
			int rowNum;
			try {
				rowNum = ps.executeUpdate();
				if (rowNum>0) {
					bool=true;
					System.out.println("添加成功,添加的元素为:"+userEnt.toString());
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
	public boolean deleteUser(String user_phone) {
		//定义对象
		System.out.println("将要删除的用户的手机号为:"+user_phone);
		Connection conn = null ;
		PreparedStatement ps = null;
		conn = DBCPUtil.getConn();
		boolean bool = false;
		//修改;
		String sqlDelete = "delete from user_table where user_telphone=?";
		try {
			ps = conn.prepareStatement(sqlDelete);
			ps.setString(1, user_phone);
			
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
	public boolean updateUser(int userId, user userEnt) {
		//定义对象
		Connection conn = null ;
		PreparedStatement ps = null;
		conn = DBCPUtil.getConn();
		boolean bool = false;
		//修改
//	user user = new user(user_name, user_password, user_mail, usre_telphone, user_photo, user_qq, user_status, user_remark, user_createTime, user_sex, user_happy, user_adress)
		String sqlUddate = "update user_table set user_name=?, user_password=?, user_mail=?, usre_telphone=?, user_photo=?, user_qq=?, user_status=?, user_remark=?, user_createTime=?, user_sex=?, user_happy=?, user_adress=? where user_ID= ?";
		
		try {
			ps= conn.prepareStatement(sqlUddate);
			//赋值

			ps.setString(1, userEnt.getUser_name());
			try {
				ps.setString(2, MD5Encoder.getEncryptedPwd(userEnt.getUser_password()));
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			ps.setString(3, userEnt.getUser_mail());
			ps.setString(4, userEnt.getuser_telphone());
			ps.setString(5, userEnt.getUser_photo());
			ps.setString(6, userEnt.getUser_qq());
			ps.setString(7, userEnt.getUser_status());
			ps.setString(8, userEnt.getUser_remark());
			ps.setString(9, userEnt.getUser_createTime());
			ps.setString(10, userEnt.getUser_sex());
			ps.setString(11, userEnt.getUser_happy());
			ps.setString(12, userEnt.getUser_adress());
			ps.setInt(13, userId);
			
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
			
		}
		return bool;
	}

	@Override
	public List<user> queryUsers() {
		//首先定义此方法将要用到的对象
		Connection conn = null;
		//sql语句预编译对象
		PreparedStatement ps = null;
		//结果集对象
		ResultSet rs = null;
		List<user> list = new ArrayList<user>();	
		String sql = "select * from user_table"; 		
		
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
				user user = new user(
								rs.getInt("user_ID"),
								rs.getString("user_name"),
								rs.getString("user_password"),
								rs.getString("user_mail"), 
								rs.getString("user_telphone"),
								rs.getString("user_photo"),
								rs.getString("user_qq"), 
								rs.getString("user_status"),
								rs.getString("user_remark"),
								rs.getString("user_createTime"),
								rs.getString("user_sex"),
								rs.getString("user_happy"), 
								rs.getString("user_adress"));
				
				list.add(user);
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
	public user queryUserByMail(String user_mail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public user queryUserByPhone(String user_telphon) {
		//定义对象
		Connection conn = DBCPUtil.getConn() ;
		PreparedStatement ps = null;
		ResultSet rs = null;
		user user = null;
		String sql = "select * from user_table where user_telphone = ?";
		//连接数据库查询数据
		try {
			ps= conn.prepareStatement(sql);
			ps.setString(1, user_telphon);
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
				user = new user(
						rs.getInt("user_ID"),
						rs.getString("user_name"),
						rs.getString("user_password"),
						rs.getString("user_mail"), 
						rs.getString("user_telphone"),
						rs.getString("user_photo"),
						rs.getString("user_qq"), 
						rs.getString("user_status"),
						rs.getString("user_remark"),
						rs.getString("user_createTime"),
						rs.getString("user_sex"),
						rs.getString("user_happy"), 
						rs.getString("user_adress"));
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
		
		return user;
	}
	public boolean userLogin(String phone, String psw) {
		UserIMPL impl = new UserIMPL();
		//提取此用户在数据库中的信息
		user user = impl.queryUserByPhone(phone);
		boolean bool = false;
		try {
			//将输入的密码与数据库中的密码对比
			bool= MD5Encoder.validPassword(psw, user.getUser_password());
			if (bool) {
				System.out.println("登录成功,用户名为："+user.getUser_name());
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
	//测试方法
	public static void main(String[] args) {
		UserIMPL dao = new UserIMPL();
//		user user = dao.queryUserByPhone("123");
//		System.out.println(user);
		
//		boolean bool = dao.addUser(new user(user_name, user_password, usre_telphone)
//		boolean bool = dao.addUser(new user("uname", "upswd", "upswd"));
//		System.out.println("添加"+bool);
		
//		boolean bool = dao.deleteUser("sg");
//		System.out.println("删除"+bool);
		
//		List<user> list =dao.queryUsers();
//		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//			user user = (user) iterator.next();
//			System.out.println(user);
//		}
//		boolean bool = dao.userLogin("upswd", "upswd");
//		System.out.println("登录"+bool);
		
	}


	
}
