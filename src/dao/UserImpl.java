package com.journey.dao.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import com.journey.dao.IUser;
import com.journey.entity.User;
import com.journey.util.DBCPUtil;
import com.journey.util.MD5Encoder;
/**
 * 
 * @author dyb
 * 
 *	用户功能实现类，实现增addUser(userEnt)删deleteUser(userPhone)改updateUser(userPhone , userEnt)查功能
 *	查询所有用户queryUsers()，按手机号查询queryUserByPhone(userPhone)，按邮箱查询queryUserByMail(userMail) 
 *	最下面有测试方法供使用参考
 *
 */

public class UserImpl implements IUser{

	
	//用户登录方法
	public boolean userLogin(String phone , String password){
		UserImpl impl = new UserImpl();
		//提取此用户在数据库中的信息
		User user = impl.queryUserByPhone(phone);
		boolean bool = false;
		try {
			//将输入的密码与数据库中的密码对比
			bool= MD5Encoder.validPassword(password, user.getUserPassword());
			if (bool) {
				System.out.println("登录成功,用户名为："+user.getUserName());
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
	
	
	//注册用户,注册只需以下4个信息:user_name,user_telphon,user_mail,user_password
	@Override
	public boolean addUser(User userEnt) {
		//定义对象
		Connection conn = null ;
		PreparedStatement ps = null;
		conn = DBCPUtil.getConn();
		boolean bool = false;
		
		//判断该手机号是否存在
		UserImpl ui = new UserImpl();
		User user = ui.queryUserByPhone(userEnt.getUserTelphon());
		//查询结果，如果为空，则继续完成注册，否则提示
		if (user == null) {
			//添加
			String sqlAdd = "insert into user_table(user_name,user_telphon,user_mail,user_password) values(?,?,?,?)";
			
			try {
				ps= conn.prepareStatement(sqlAdd);
				ps.setString(1, userEnt.getUserName());
				ps.setString(2, userEnt.getUserTelphon());
				ps.setString(3, userEnt.getUserMail());
				try {
					//将密码加密转化存入数据库
					ps.setString(4, MD5Encoder.getEncryptedPwd(userEnt.getUserPassword()));
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				//运行sql
				int rowNum = ps.executeUpdate();
				if (rowNum>0) {
					bool=true;
					System.out.println("添加成功,添加的元素为:"+userEnt.toString());

				}				
			} catch (SQLException e) {
				System.out.println("预编译出错");
				e.printStackTrace();
			}finally {
				closeDB(null, ps, conn);
			}
		}else {
			System.out.println("该手机号已经存在!");
		}
			
		return bool;
	}

	//删除用户
	@Override
	public boolean deleteUser(String userPhone) {
		//定义对象
		System.out.println("将要删除的手机号为:"+userPhone);
				Connection conn = null ;
				PreparedStatement ps = null;
				conn = DBCPUtil.getConn();
				boolean bool = false;
				//修改;
				String sqlDelete = "delete from user_table where user_telphon=?";
				try {
					ps = conn.prepareStatement(sqlDelete);
					ps.setString(1, userPhone);
					
					int rowcount =ps .executeUpdate();
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
		return bool;
	}

	//更新用户,传入手机号，修改该用户的信息
	@Override
	public boolean updateUser(String userPhone , User userEnt) {
		//定义对象
		Connection conn = null ;
		PreparedStatement ps = null;
		conn = DBCPUtil.getConn();
		boolean bool = false;
		//修改
		String sqlUddate = "update user_table set user_name=?,user_password=?,user_mail=?,user_telphon=?,user_photo=?,user_qq_id=?,user_status=?,user_remark=?,user_createTime=?,user_sex=?,user_happy=?,user_adress=? where user_telphon= ?";
		
		try {
			ps= conn.prepareStatement(sqlUddate);
			//赋值

			ps.setString(1, userEnt.getUserName());
			ps.setString(2, userEnt.getUserPassword());
			ps.setString(3, userEnt.getUserMail());
			ps.setString(4, userEnt.getUserTelphon());
			ps.setString(5, userEnt.getUserPhoto());
			ps.setString(6, userEnt.getUserQqId());
			ps.setString(7, userEnt.getUserStatus());
			ps.setString(8, userEnt.getUserRemark());
			ps.setString(9, userEnt.getUserCreateTime());
			ps.setString(10, userEnt.getUserSex());
			ps.setString(11, userEnt.getUserHappy());
			ps.setString(12, userEnt.getUserAdress());
			ps.setString(13, userPhone);
			
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
		return bool;

	}

	//查询所有用户,返回一个结果实体集合
	@Override
	public List<User> queryUsers() {
		//首先定义此方法将要用到的对象
			Connection conn = null;
			//sql语句预编译对象
			PreparedStatement ps = null;
			//结果集对象
			ResultSet rs = null;
			ArrayList<User> list = new ArrayList<User>();	//存放返回的网站对象
			String sql = "select * from user_table"; 		//sql查询语句
			
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
					User user = new User(
										rs.getInt("userId") ,
										rs.getString("user_name"), 
										rs.getString("user_mail"), 
										rs.getString("user_password"), 
										rs.getString("user_telphon"), 
										rs.getString("user_photo"), 
										rs.getString("user_qq_id"), 
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
				closeDB(rs, ps, conn);
			}
		
		return list;
	}
	
	//通过邮箱查询用户，返回单个user对象
	@Override
	public User queryUserByMail(String userMail) {
		//定义对象
				Connection conn = null ;
				PreparedStatement ps = null;
				ResultSet rs = null;
				User user = null;
				String sql = "select * from user_table where user_mail = ?";
				//连接数据库查询数据
				conn= DBCPUtil.getConn();
				try {
					ps = conn.prepareStatement(sql);
					//通过 编译 给sql 语句赋值
					ps.setString(1, userMail);
					
					
				} catch (SQLException e) {
					System.out.println("预编译出错！");
					e.printStackTrace();
				}
				try {
					rs = ps.executeQuery();			
				} catch (SQLException e) {
					System.out.println("执行查询语句出错!");
					e.printStackTrace();
				} 
				try {
					if (rs.next()) {
						user = new User(
										rs.getInt("userId") ,
										rs.getString("user_name"), 
										rs.getString("user_mail"), 
										rs.getString("user_password"), 
										rs.getString("user_telphon"), 
										rs.getString("user_photo"), 
										rs.getString("user_qq_id"), 
										rs.getString("user_status"), 
										rs.getString("user_remark"), 
										rs.getString("user_createTime"), 
										rs.getString("user_sex"), 
										rs.getString("user_happy"), 
										rs.getString("user_adress"));
					}
				} catch (SQLException e) {
					System.out.println("获取结果集出错");
					e.printStackTrace();
				}finally {
					closeDB(rs, ps, conn);
				}
		return user;
	}

	//通过手机号查询，返回单个user对象
	@Override
	public User queryUserByPhone(String userPhone) {
			//定义对象
			Connection conn = null ;
			PreparedStatement ps = null;
			ResultSet rs = null;
			User user = null;
			String sql = "select * from user_table where user_telphon = ?";
			//连接数据库查询数据
			conn= DBCPUtil.getConn();
			try {
				ps = conn.prepareStatement(sql);
				//通过 编译 给sql 语句赋值
				ps.setString(1, userPhone);
				
				
			} catch (SQLException e) {
				System.out.println("预编译出错！");
				e.printStackTrace();
			}
			try {
				rs = ps.executeQuery();			
			} catch (SQLException e) {
				System.out.println("执行查询语句出错!");
				e.printStackTrace();
			} 
			try {
				if (rs.next()) {
					user = new User(
									rs.getInt("userId") ,
									rs.getString("user_name"), 
									rs.getString("user_mail"), 
									rs.getString("user_password"), 
									rs.getString("user_telphon"), 
									rs.getString("user_photo"), 
									rs.getString("user_qq_id"), 
									rs.getString("user_status"), 
									rs.getString("user_remark"), 
									rs.getString("user_createTime"), 
									rs.getString("user_sex"), 
									rs.getString("user_happy"), 
									rs.getString("user_adress"));
				}
			} catch (SQLException e) {
				System.out.println("获取结果集出错");
				e.printStackTrace();
			}finally {
				closeDB(rs, ps, conn);
			}
			return user;
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
	
	
	//测试方法
	public static void main(String[] args) {
		UserImpl ui = new UserImpl();
		//注册用户
//		ui.addUser(new User(userName, userMail, userPassword, userTelphon));
//		ui.addUser(new User("1002", "1002@qq.com", "test1002", "12345678911"));
		
		//修改用户
	//	ui.updateUser("321", new User("asd", "password", "test", "1234", "test", "test", "y", "test", "test", "m", "test", "test"));
		//删除用户
//		ui.deleteUser("test");
		//按手机号查询用户
//		User user = ui.queryUserByPhone("1234");
//		System.out.println(user);
		//按邮箱查询用户
//		User user = ui.queryUserByMail("da");
//		System.out.println(user);
		//用户登录
//		System.out.println(ui.userLogin("321", "test"));
//		System.out.println(ui.userLogin("321", "tes"));
		//查询所有用户
//		List<User> list =ui.queryUsers();
//		for (Iterator<User> iterator = list.iterator(); iterator.hasNext();) {
//			User user = (User) iterator.next();
//			System.out.println(user);
//		}
	}
	
	
	
	
}
