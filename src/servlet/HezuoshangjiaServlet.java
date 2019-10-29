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
import com.zhuji.dao.impl.HezuoshangjiaIMPL;

import com.zhuji.entity.Hezuoshangjia;
/**
 * 
 * @author DYB
 *
 */
public class HezuoshangjiaServlet extends HttpServlet {
	HezuoshangjiaIMPL dao = new HezuoshangjiaIMPL();

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


	private void queryByName(HttpServletRequest request, HttpServletResponse response) {
		//获取参数
		String  mingcheng = request.getParameter("mingcheng");
		//调用dao层方法
		List<Hezuoshangjia> retList = dao.queryHezuoshangjiaByName(mingcheng);
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


	private void query(HttpServletRequest request, HttpServletResponse response) {
		//调用dao层方法，并接受参数
		List<Hezuoshangjia> retList = dao.queryHezuoshangjias();
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


	private void update(HttpServletRequest request, HttpServletResponse response) {
		//接受参数		
		//ID需要String强转为int
		String mingcheng = request.getParameter("mingcheng");
		String leixing = request.getParameter("leixing");
		String dianhua = request.getParameter("dianhua");
		String zhuangtai = request.getParameter("zhuangtai");
		String dizhi = request.getParameter("dizhi");
		int jingdian_ID = Integer.parseInt(request.getParameter("jingdian_ID"));
		int tupianID = Integer.parseInt(request.getParameter("tupianID"));
		String jieshao = request.getParameter("jieshao");
		String hezuoshijian = request.getParameter("hezuoshijian");
		String junjia = request.getParameter("junjia");
		//将参数封装到Hezuoshangjia对象中
		Hezuoshangjia hezuoshangjia = new Hezuoshangjia(mingcheng, leixing, dianhua, zhuangtai, dizhi, jingdian_ID, tupianID, jieshao, hezuoshijian, junjia); 
		//调用dao方法
		boolean bool =  dao.updataHezuoshangjia(dianhua, hezuoshangjia);
		String retMessage = "";
		if (bool) {
			retMessage = "更新成功！！";			
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
		String dianhua = request.getParameter("dianhua");
		//调用dao层方法

		boolean bool = dao.deleteHezuoshangjia(dianhua);
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


	private void add(HttpServletRequest request, HttpServletResponse response) {
		//接受参数		
		//ID需要String强转为int
		String mingcheng = request.getParameter("mingcheng");
		String leixing = request.getParameter("leixing");
		String dianhua = request.getParameter("dianhua");
		String zhuangtai = request.getParameter("zhuangtai");
		String dizhi = request.getParameter("dizhi");
		int jingdian_ID = Integer.parseInt(request.getParameter("jingdian_ID"));
		int tupianID = Integer.parseInt(request.getParameter("tupianID"));
		String jieshao = request.getParameter("jieshao");
		String hezuoshijian = request.getParameter("hezuoshijian");
		String junjia = request.getParameter("junjia");
		//将参数封装到Hezuoshangjia对象中
		Hezuoshangjia hezuoshangjia = new Hezuoshangjia(mingcheng, leixing, dianhua, zhuangtai, dizhi, jingdian_ID, tupianID, jieshao, hezuoshijian, junjia); 
		//调用dao方法
		boolean bool =  dao.addHezuoshangjia(hezuoshangjia);
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

}
