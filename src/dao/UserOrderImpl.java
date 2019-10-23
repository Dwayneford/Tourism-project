package com.journey.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import com.journey.dao.IUserOrder;
import com.journey.entity.UserOrder;
import com.journey.util.DBCPUtil;

/**
 * 
 * @author dyb
 * 
 *	订单功能实现类，实现增addOrder(orderEnt)删deleteOrder(OrderNo)改updateOrder(OrderNo , orderEnt)查功能
 *	查询所有用户queryOrders()，返回一个list集合；按手机号查询queryUserByOrderPhone(orderPhone)，按订单号查询queryUserByOrderNO(orderNO) 返回一个userOrder对象
 *	最下面有测试方法供使用参考
 *
 */


public class UserOrderImpl implements IUserOrder{

	

	
	
	//新增订单
	@Override
	public boolean addOrder(UserOrder orderEnt) {
		//定义对象
		Connection conn = null ;
		PreparedStatement ps = null;
		conn= DBCPUtil.getConn();
		boolean bool = false;
				
		//添加 
		String sqlAdd = "insert into user_order_table(order_NO,user_id,name,phoneNo,mail,origin,destination,days,adult,child,budget,theme,description,status,type,time,route,begintime,price,insurance,traffic,travelmode,discount) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			ps= conn.prepareStatement(sqlAdd);
			
			//order_NO,user_id,name,phoneNo,mail,origin,destination,days,adult,child,budget,theme,description,status,type,time
			ps.setString(1, orderEnt.getOrderNo());
			ps.setString(2, orderEnt.getOrderId());
			ps.setString(3, orderEnt.getName());
			ps.setString(4, orderEnt.getPhoneNo());
			ps.setString(5, orderEnt.getMail());
			ps.setString(6, orderEnt.getOrigin());
			ps.setString(7, orderEnt.getDestination());
			ps.setString(8, orderEnt.getDays());
			ps.setString(9, orderEnt.getAdult());
			ps.setString(10, orderEnt.getChild());
			ps.setString(11, orderEnt.getBudget());
			ps.setString(12, orderEnt.getTheme());
			ps.setString(13, orderEnt.getDescription());
			ps.setString(14, orderEnt.getStatus());			//状态由管理员修改
			ps.setString(15, orderEnt.getType());
			ps.setString(16, orderEnt.getTime());
			ps.setString(17, orderEnt.getRoute());
			ps.setString(18, orderEnt.getBeginTime());
			ps.setString(19, orderEnt.getPrice());
			ps.setString(20, orderEnt.getInsurance());
			ps.setString(21, orderEnt.getTraffic());
			ps.setString(22, orderEnt.getTravelMode());
			ps.setString(23, orderEnt.getDiscont());
			//route,begintime,price,insurance,traffic,travelmode,discont
			//运行addsql
			int num = ps.executeUpdate();
			if (num>0) {
				bool= true ;
				System.out.println("添加成功,添加的订单信息为:"+orderEnt.toString());

			}				
		} catch (SQLException e) {
			System.out.println("预编译出错");
			e.printStackTrace();
		}finally {
			closeDB(null, ps, conn);
		}
		
			
		return bool;
	}

	//删除订单
	@Override
	public boolean deleteOrder(String OrderNo) {
		//定义对象
		System.out.println("将要删除的订单编号为:"+OrderNo);
				Connection conn = null ;
				PreparedStatement ps = null;
				conn= DBCPUtil.getConn();
				boolean bool = false;
				//修改;
				String sqlDelete = "delete from user_order_table where order_NO=?";
				try {
					ps = conn.prepareStatement(sqlDelete);
					ps.setString(1, OrderNo);
					
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
		return bool;
	}

	//更新订单,传入订单号，修改该订单的信息
	@Override
	public boolean updateOrder(String OrderNo , UserOrder orderEnt) {
		//定义对象
		Connection conn = null ;
		PreparedStatement ps = null;
		conn= DBCPUtil.getConn();
		boolean bool = false;
		//修改
		String sqlUddate = "update user_order_table set order_NO=?,user_id=?,name=?,phoneNo=?,mail=?,origin=?,destination=?,days=?,adult=?,child=?,budget=?,theme=?,description=?,status=?,type=?,time=? route=?,begintime=?,price=?,insurance=?,traffic=?,travelmode=?,discont=? where order_NO=?";
		
		try {
			ps= conn.prepareStatement(sqlUddate);
			//赋值

			ps.setString(1, orderEnt.getOrderNo());
			ps.setString(2, orderEnt.getOrderId());
			ps.setString(3, orderEnt.getName());
			ps.setString(4, orderEnt.getPhoneNo());
			ps.setString(5, orderEnt.getMail());
			ps.setString(6, orderEnt.getOrigin());
			ps.setString(7, orderEnt.getDestination());
			ps.setString(8, orderEnt.getDays());
			ps.setString(9, orderEnt.getAdult());
			ps.setString(10, orderEnt.getChild());
			ps.setString(11, orderEnt.getBudget());
			ps.setString(12, orderEnt.getTheme());
			ps.setString(13, orderEnt.getDescription());
			ps.setString(14, orderEnt.getStatus());
			ps.setString(15, orderEnt.getType());
			ps.setString(16, orderEnt.getTime());
			ps.setString(17, orderEnt.getRoute());
			ps.setString(18, orderEnt.getBeginTime());
			ps.setString(19, orderEnt.getPrice());
			ps.setString(20, orderEnt.getInsurance());
			ps.setString(21, orderEnt.getTraffic());
			ps.setString(22, orderEnt.getTravelMode());
			ps.setString(23, orderEnt.getDiscont());
			ps.setString(24, OrderNo);
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
	public List<UserOrder> queryOrders() {
		//首先定义此方法将要用到的对象
			Connection conn = null;
			//sql语句预编译对象
			PreparedStatement ps = null;
			//结果集对象
			ResultSet rs = null;
			ArrayList<UserOrder> list = new ArrayList<UserOrder>();	//存放返回的网站对象
			String sql = "select * from user_order_table"; 		//sql查询语句
			
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
				while (rs.next()) {
					//order_NO,user_id,name,phoneNo,mail,origin,destination,days,adult,child,budget,theme,description,status,type,time

					UserOrder userOrder = new UserOrder(rs.getString("order_NO"),
														rs.getString("user_id"), 
														rs.getString("name"),
														rs.getString("phoneNo"),
														rs.getString("mail"),
														rs.getString("origin"), 
														rs.getString("destination"),
														rs.getString("days"), 
														rs.getString("adult"),
														rs.getString("child"),
														rs.getString("budget"), 
														rs.getString("theme"), 
														rs.getString("description"), 
														rs.getString("status"), 
														rs.getString("type"),
														rs.getString("time"),
														rs.getString("route"),
														rs.getString("begintime"),
														rs.getString("price"), 
														rs.getString("insurance"),
														rs.getString("traffic"),
														rs.getString("travelmode"),
														rs.getString("discount"));
														
							
					
					list.add(userOrder);
				}
			} catch (SQLException e) {
				System.out.println("遍历并封装结果集错误");
				e.printStackTrace();
			}finally {
				closeDB(rs, ps, conn);
			}
		
		return list;
	}
	
	//通过订单编号查询，返回单个user对象
	@Override
	public UserOrder queryOrderByOrderNO(String OrderNO) {
		//定义对象
		Connection conn = DBCPUtil.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserOrder order=null;
		String sql = "select * from user_order_table where Order_NO = ?";
		//连接数据库查询数据

		try {
			ps = conn.prepareStatement(sql);
			//通过 编译 给sql 语句赋值
			ps.setString(1, OrderNO);
			
			
		} catch (SQLException e) {
			System.out.println("预编译出错！");
			e.printStackTrace();
		}
		
		try {
			//执行查询
			rs = ps.executeQuery();			
		} catch (SQLException e) {
			System.out.println("执行查询语句出错!");
			e.printStackTrace();
		} 
		try {
			if (rs.next()) {
				order= new UserOrder(
									rs.getString("order_NO"),
									rs.getString("user_id"), 
									rs.getString("name"),
									rs.getString("phoneNo"),
									rs.getString("mail"),
									rs.getString("origin"), 
									rs.getString("destination"),
									rs.getString("days"), 
									rs.getString("adult"),
									rs.getString("child"),
									rs.getString("budget"), 
									rs.getString("theme"), 
									rs.getString("description"), 
									rs.getString("status"), 
									rs.getString("type"),
									rs.getString("time"),
									rs.getString("rout"),
									rs.getString("begintime"),
									rs.getString("price"), 
									rs.getString("insurance"),
									rs.getString("traffic"),
									rs.getString("traclmode"),
									rs.getString("discount"));
			}
		} catch (SQLException e) {
			System.out.println("获取结果集出错");
			e.printStackTrace();
		}finally {
			closeDB(rs, ps, conn);
		}
		return order;
	}

	//通过手机号查询，返回单个user对象
	@Override
	public UserOrder queryOrderByOrderPhone(String Phone) {
			//定义对象
			Connection conn = null ;
			PreparedStatement ps = null;
			ResultSet rs = null;
			UserOrder order = null;
			String sql = "select * from user_order_table where phoneNo = ?";
			//连接数据库查询数据
			conn= DBCPUtil.getConn();
			try {
				ps = conn.prepareStatement(sql);
				//通过 编译 给sql 语句赋值
				ps.setString(1, Phone);
				
				
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
					order = new UserOrder(
							rs.getString("order_NO"),
							rs.getString("user_id"), 
							rs.getString("name"),
							rs.getString("phoneNo"),
							rs.getString("mail"),
							rs.getString("origin"), 
							rs.getString("destination"),
							rs.getString("days"), 
							rs.getString("adult"),
							rs.getString("child"),
							rs.getString("budget"), 
							rs.getString("theme"), 
							rs.getString("description"), 
							rs.getString("status"), 
							rs.getString("type"),
							rs.getString("time"),
							rs.getString("rout"),
							rs.getString("begintime"),
							rs.getString("price"), 
							rs.getString("insurance"),
							rs.getString("traffic"),
							rs.getString("traclmode"),
							rs.getString("discount"));
				}
			} catch (SQLException e) {
				System.out.println("获取结果集出错");
				e.printStackTrace();
			}finally {
				closeDB(rs, ps, conn);
			}
			return order;
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
		UserOrderImpl ui = new UserOrderImpl();
		//新增订单
//		ui.addOrder(new UserOrder(orderId, orderNo, name, phoneNo, mail, origin, destination, days, child, adult, budget, theme, description, status, type, time))
//		ui.addOrder(new UserOrder("orderNo", "name", "phoneNo", "mail", "origin", "destination", "days", "child", "adult", "budget", "theme", "description", "status", "type", "time"));
		boolean bool = ui.addOrder(new UserOrder("10000","1003", "order1", "orderphone", "ordermail", "orderorigin", "orderdestination", "orderdays", "orderchild", "orderadult", "orderbudget", "ordertheme", "orderdescription", "orderstatus", "ordertype", "ordertime"));
//		System.out.println(bool);
		//修改订单
//		ui.updateOrder("8", new UserOrder("change2", "sss2", "ccc2", "ooo2", "ddd2", "ccc2", "ooo2", "ddd2","change2", "sss2", "ccc2", "ooo2", "ddd2", "ccc2", "ooo2", "ddd2") );
		//删除订单
//		ui.deleteOrder("7");
		//按订单号查询订单
//		UserOrder order = ui.queryOrderByOrderNO("3");
//		System.out.println(order);
		//按手机号查询订单
//		UserOrder order = ui.queryOrderByOrderPhone("ooo2");
//		System.out.println(order);
		//查询所有订单
		System.out.println("查询所有订单");
		List list = ui.queryOrders();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			UserOrder order = (UserOrder) iterator.next();
			System.out.println(order);
		}
		
	}
	
	
	
	
}
