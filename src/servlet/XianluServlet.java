package com.zhuji.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zhuji.dao.impl.XianluIMPL;
import com.zhuji.entity.Jingdian;
import com.zhuji.entity.Xianlu;

public class XianluServlet extends HttpServlet {
	XianluIMPL dao =new XianluIMPL();

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
		}else if (("queryByName").equals(op)) {
			queryByName(request, response);
		}
	}
	private void add(HttpServletRequest request, HttpServletResponse response) {
		//接受参数		
		//ID需要String强转为int
		int jingdian_ID = Integer.parseInt(request.getParameter("jingdian_ID"));
		String pingjia = request.getParameter("pingjia");
		//将参数封装到对象中
		Xianlu xianlu= new Xianlu(jingdian_ID, pingjia);
		//调用dao方法
		boolean bool=dao.addXianlu(xianlu);
		String retMessage = "";
		if(bool){
			retMessage="添加成功";
		}else {
			retMessage="失败";
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
		int xianlu_ID =Integer.parseInt(request.getParameter("xianlu_ID"));
		//调用dao层方法
		boolean bool = dao.deleteXianlu(xianlu_ID);
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
		int xianlu_ID = Integer.parseInt(request.getParameter("xianlu_ID"));
		int jingdian_ID = Integer.parseInt(request.getParameter("jingdian_ID"));
		String pingjia = request.getParameter("pingjia");
		//将参数封装到对象中
		Xianlu xianlu=new Xianlu(jingdian_ID, pingjia);
		//调用dao方法
		boolean bool =  dao.updataXianlu(xianlu_ID, xianlu);
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
	private void query(HttpServletRequest request, HttpServletResponse response) {
		List<Xianlu> list=dao.queryXianlu();
		//拼接成 layUI需要的格式数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", 200);
		map.put("data", list);
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
	private void queryByName(HttpServletRequest request, HttpServletResponse response) {
		//获取参数
		int xianlu_ID = Integer.parseInt(request.getParameter("xianlu_ID"));
		//调用dao层方法
		List<Xianlu> list=dao.queryXianluByName(xianlu_ID);
		//拼接成 layUI需要的格式数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", 200);
		map.put("data", list);
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

}
