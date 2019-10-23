package com.journey.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;
import com.journey.dao.impl.UserOrderImpl;
import com.journey.entity.UserOrder;

public class OrderServlet extends HttpServlet {

	UserOrderImpl dao = new UserOrderImpl();
		 
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		//编码格式设置
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//通过参数判断执行何方法
		String op = request.getParameter("op");
		if (("queryOrders").equals(op)) {
			//调用列表查询方法
			queryOrders(request, response);
		}else if (("add").equals(op)) {
			//调用修改添加方法
			add(request, response);
		}else if (("queryOrderByOrderNO").equals(op)) {
			queryOrderByOrderNO(request, response);
		}else if (("update").equals(op)) {
			update(request, response);
		}else if (("delete").equals(op)) {
			delete(request, response);
		}else if (("queryOrderByPhone").equals(op)) {
			queryOrderByPhone(request, response);
		}
		
		
	}



	private void add(HttpServletRequest request, HttpServletResponse response) {
		//接受参数
		//将当前系统的时间设置为订单号		
		long t = System.currentTimeMillis();
//		String orderNo = request.getParameter("order_NO");
		String orderNo = t+"";
		String user_id = request.getParameter("user_ID");
		String name = request.getParameter("name");
		String phoneNo = request.getParameter("phoneNO");
		String mail = request.getParameter("origin");
		String destination = request.getParameter("destination");
		String origin = request.getParameter("origin");
		String days = request.getParameter("days");
		String adult = request.getParameter("adult");
		String child = request.getParameter("child");
		String budget = request.getParameter("budget");
		String theme = request.getParameter("theme");
		String description = request.getParameter("description");
		String status = request.getParameter("status");
		String type = request.getParameter("type");
		String time = request.getParameter("time");
		
		String route = request.getParameter("route");
		String begintime = request.getParameter("begintime");
		String price = request.getParameter("price");
		String insurance = request.getParameter("insurance");
		String traffic = request.getParameter("traffic");
		String travelmode = request.getParameter("travelmode");
		String discont = request.getParameter("discont");
		//route,begintime,price,insurance,traffic,travelmode,discont
		//将参数封装到order对象中		
		UserOrder orderEnt = new UserOrder(orderNo, name, phoneNo, mail, origin, destination, days, child, adult, budget, theme, description, status, type, time,route,begintime,price,insurance,traffic,travelmode,discont);
		//调用dao层方法

		boolean bool = dao.addOrder(orderEnt);
		String retMessage = "";
		if (bool) {
			retMessage = "success，成功！！";			
		}else {
			retMessage = "error错误！";
		}
		//向客户端输出内容
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(retMessage);
			//刷新关闭
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		//获取参数
		String orderNO = request.getParameter("order_NO");
		//调用dao层方法

		boolean bool = dao.deleteOrder(orderNO);
		String retMessage = "";
		if (bool) {
			retMessage = "success，成功！！";			
		}else {
			retMessage = "error错误！";
		}
		//向客户端输出内容
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(retMessage);
			//刷新关闭
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}	

	private void update(HttpServletRequest request, HttpServletResponse response) {
		//获取参数
		String orderNo = request.getParameter("order_NO");
		String user_id = request.getParameter("user_ID");
		String name = request.getParameter("name");
		String phoneNo = request.getParameter("phoneNO");
		String mail = request.getParameter("origin");
		String destination = request.getParameter("destination");
		String origin = request.getParameter("origin");
		String days = request.getParameter("days");
		String adult = request.getParameter("adult");
		String child = request.getParameter("child");
		String budget = request.getParameter("budget");
		String theme = request.getParameter("theme");
		String description = request.getParameter("description");
		String status = request.getParameter("status");
		String type = request.getParameter("type");
		String time = request.getParameter("time");
		String route = request.getParameter("route");
		String begintime = request.getParameter("begintime");
		String price = request.getParameter("price");
		String insurance = request.getParameter("insurance");
		String traffic = request.getParameter("traffic");
		String travelmode = request.getParameter("travelmode");
		String discont = request.getParameter("discont");
		//将参数封装到order对象中		
		UserOrder orderEnt = new UserOrder(orderNo, name, phoneNo, mail, origin, destination, days, child, adult, budget, theme, description, status, type, time,route,begintime,price,insurance,traffic,travelmode,discont);
		//调用dao层方法

		boolean bool = dao.updateOrder(orderNo, orderEnt);
		String retMessage = "";
		if (bool) {
			retMessage = "success，成功！！";			
		}else {
			retMessage = "error错误！";
		}
		//向客户端输出内容
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(retMessage);
			//刷新关闭
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void queryOrders(HttpServletRequest request, HttpServletResponse response) {
		//调用dao层方法获取列表数据
		
		//返回的数据
		List<UserOrder> retList = dao.queryOrders();
		
		//拼接成 layUI需要的格式数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", 200);
		map.put("data", retList);
		
		//将数据返回到页面上：转换为json格式数据gson fastjson
		//[{},{},{}]
				Gson gs =new Gson();
				String jsonorders = gs.toJson(map);
//						String jsonWebsites = gs.toJson(retList);
				System.out.println("orderEnt===>"+jsonorders);
				//向客户端输出
				PrintWriter out;
				try {
					out = response.getWriter();
					out.print(jsonorders);
					//刷新关闭
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
		
	}

	private void queryOrderByPhone(HttpServletRequest request, HttpServletResponse response) {
		//获取参数
		String phoneNO = request.getParameter("phoneNO");
		//调用dao层方法
		UserOrder EntOrder = dao.queryOrderByOrderPhone(phoneNO);
		//存入作用域	
		request.setAttribute("EntOrder", EntOrder);
		//响应到页面
		try {
			request.getRequestDispatcher("待编写的页面").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	private void queryOrderByOrderNO(HttpServletRequest request, HttpServletResponse response) {
		//获取参数
		String OrderNO = request.getParameter("OrderNO");
		//调用dao层方法
		UserOrder EntOrder = dao.queryOrderByOrderNO(OrderNO);
		//存入作用域	
		request.setAttribute("EntOrder", EntOrder);
		//响应到页面
		try {
			request.getRequestDispatcher("待编写的页面").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


}
