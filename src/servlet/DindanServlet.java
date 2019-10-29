package com.zhuji.servlet;
/**
 * 
 * @author jiejie
 *
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.zhuji.dao.impl.DindanImpl;
import com.zhuji.dao.impl.dingdanpingjiaImpl;
import com.zhuji.entity.Dindan;

public class DindanServlet extends HttpServlet {
	DindanImpl dao = new DindanImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

		doPost(request, response);
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		//编码格式设置
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//通过参数判断执行何方法
		String op = request.getParameter("op");
		if (("query").equals(op)) {
			//调用列表查询方法
			queryDindans(request, response);
		}else if (("add").equals(op)) {
			//调用修改添加方法
			add(request, response);
		}else if (("queryDindanByDindan_ID").equals(op)) {
			queryDindanByDindan_ID(request, response);
		}else if (("update").equals(op)) {
			update(request, response);
		}else if (("delete").equals(op)) {
			doDelete(request, response);
		}else if (("totalprice").equals(op)) {
			totalprice(request, response);
		}
		
		
	}
	private void totalprice(HttpServletRequest request, HttpServletResponse response) {
		//计算订单总价，传入页面;订单信息写入数据库，此时状态为未支付
		
		//接受参数
		//将当前系统的时间设置为订单号	
		long t = System.currentTimeMillis();		
		String dindan_NO = t+"";
//		int user_ID = Integer.parseInt(request.getParameter("user_ID"));
//		int xianlu_ID = Integer.parseInt(request.getParameter("xianlu_ID"));
		
		int user_ID = 345;
		int xianlu_ID = 35623;
		String zifu_time = request.getParameter("zifu_time");
		String zifi_NO = request.getParameter("zifi_NO");
		String dindanzongjia = request.getParameter("dindanzongjia");
		//状态设置为未支付
		String zhuangtai = "未支付";
		
		//将参数封装到Dindan对象中		
		Dindan dindanEnt = new Dindan(dindan_NO, user_ID, xianlu_ID, zifu_time, zifi_NO, dindanzongjia, zhuangtai);
		//调用dao层方法,将此订单信息写入数据库，状态为未支付；支付完成调用update方法或者单独写个方法更改状态
		boolean bool = dao.addDindan(dindanEnt);
		
		//将总价格这个值传到支付页面
		//创建session对象
		HttpSession session = request.getSession();
		//存放用户信息
		session.setAttribute("dindanzongjia", dindanzongjia);
		session.setAttribute("dindan_NO", dindan_NO);
		
		//response.sendRedirect("http://localhost:8080/zhuji/zhifu.jsp");
		
		RequestDispatcher dis =request.getRequestDispatcher("../zhifu.jsp");
		try {
			dis.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("转发");
		
		
//		String retMessage = "";
//		if (bool) {
//			retMessage = "success，成功！！";			
//		}else {
//			retMessage = "error错误！";
//		}
//		//向客户端输出内容
//		PrintWriter out;
//		try {
//			out = response.getWriter();
//			out.print(retMessage);
//			//刷新关闭
//			out.flush();
//			out.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
	}
	private void queryDindanByDindan_ID(HttpServletRequest request, HttpServletResponse response) {
		
		
	}
	private void update(HttpServletRequest request, HttpServletResponse response) {
		//获取参数
		String orderNo = request.getParameter("order_NO");
		int user_ID = Integer.parseInt(request.getParameter("user_ID"));
		int xianlu_ID = Integer.parseInt(request.getParameter("xianlu_ID"));
		String zifu_time = request.getParameter("zifu_time");
		String zifi_NO = request.getParameter("zifi_NO");
		String dindanzongjia = request.getParameter("dindanzongjia");
		String zhuangtai = request.getParameter("zhuangtai");
		
		//将参数封装到Dindan对象中		
		Dindan dindanEnt = new Dindan(orderNo, user_ID, xianlu_ID, zifu_time, zifi_NO, dindanzongjia, zhuangtai);
		//调用dao层方法
		boolean bool = dao.updateDindan(orderNo, dindanEnt);
		String retMessage = "";
		if (bool) {
			retMessage = "修改成功！！";			
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
	private void queryDindans(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	private void add(HttpServletRequest request, HttpServletResponse response) {
		//接受参数
		//将当前系统的时间设置为订单号	
		long t = System.currentTimeMillis();		
		String dindan_NO = t+"";
		int user_ID = Integer.parseInt(request.getParameter("user_ID"));
		int xianlu_ID = Integer.parseInt(request.getParameter("xianlu_ID"));
		String zifu_time = request.getParameter("zifu_time");
		String zifi_NO = request.getParameter("zifi_NO");
		String dindanzongjia = request.getParameter("dindanzongjia");
		String zhuangtai = request.getParameter("zhuangtai");
		
		//将参数封装到Dindan对象中		
		Dindan dindanEnt = new Dindan(dindan_NO, user_ID, xianlu_ID, zifu_time, zifi_NO, dindanzongjia, zhuangtai);
		//调用dao层方法
		boolean bool = dao.addDindan(dindanEnt);
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
		String dindan_NO = request.getParameter("dindan_NO");
		//调用dao层方法

		boolean bool = dao.deleteDindan(dindan_NO);
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
		//调用dao方法
		List<Dindan> retList = dao.queryDindans();
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
//								String jsonWebsites = gs.toJson(retList);
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
	
	

}
