package com.zhuji.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zhuji.dao.impl.Hezuoshangjia_tupianIMPL;
import com.zhuji.entity.Hezuoshangjia_tupian;
/**
 * 
 * @author DYB
 *
 */
public class Hezuoshangjia_tupianServlset extends HttpServlet {
	Hezuoshangjia_tupianIMPL dao = new Hezuoshangjia_tupianIMPL();

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
		if (("add").equals(op)) {
			//调用列表查询方法
			add(request, response);
		}else if (("delete").equals(op)) {
			delete(request, response);
		}
		else if (("update").equals(op)) {
			update(request, response);
		}else if (("query").equals(op)) {
			query(request, response);
		}else if (("queryByID").equals(op)) {
			queryByID(request, response);
		}
		
	}





	private void add(HttpServletRequest request, HttpServletResponse response) {
		//接受参数
		
		//ID需要String强转为int
		int shangjia_ID = Integer.parseInt(request.getParameter("shangjia_ID"));
		String tupianURL = request.getParameter("tupianURL");
		//将参数封装到Hezuoshangjia_tupian对象中
		Hezuoshangjia_tupian hezuoshangjia_tupian = new Hezuoshangjia_tupian(shangjia_ID, tupianURL);
		//调用dao层方法
		boolean bool = dao.addHezuoshangjia_tupian(hezuoshangjia_tupian);
		String retMessage = "";
		if (bool) {
			retMessage = "添加成功！！";			
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
		int tupian_ID = Integer.parseInt(request.getParameter("tupian_ID"));
		//调用dao层方法

		boolean bool = dao.deleteHezuoshangjia_tupian(tupian_ID);
		String retMessage = "";
		if (bool) {
			retMessage = "删除成功！！";			
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
		int tupian_ID = Integer.parseInt(request.getParameter("tupian_ID"));
		int shangjia_ID = Integer.parseInt(request.getParameter("shangjia_ID"));
		String tupianURL = request.getParameter("tupianURL");
		//将参数封装到Hezuoshangjia_tupian对象中
		Hezuoshangjia_tupian hezuoshangjia_tupian = new Hezuoshangjia_tupian(shangjia_ID, tupianURL);
		//调用dao层方法
		boolean bool = dao.updataHezuoshangjia_tupian(tupian_ID, hezuoshangjia_tupian);
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

	//查询所有商家图片的servlet
	private void query(HttpServletRequest request, HttpServletResponse response) {
		//调用dao层方法，并接受参数
		List<Hezuoshangjia_tupian> retList =dao.queryHezuoshangjia_tupians();
		//拼接成 layUI需要的格式数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", 200);
		map.put("data", retList);
		//将数据返回到页面上：转换为json格式数据gson[{},{},{}]
		Gson gs =new Gson();
		String json = gs.toJson(map);
		System.out.println("Ent===>"+json);
		//向客户端输出
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(json);
			//刷新关闭
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
	}


	private void queryByID(HttpServletRequest request, HttpServletResponse response) {
		//获取参数
		int tupian_ID = Integer.parseInt(request.getParameter("tupian_ID"));
		//调用dao层方法
		Hezuoshangjia_tupian hezuoshangjia_tupian = dao.queryHezuoshangjia_tupianByID(tupian_ID);
		//存入作用域	
		request.setAttribute("EntHezuoshangjia_tupian", hezuoshangjia_tupian);
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
