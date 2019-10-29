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
import com.zhuji.dao.Ijingdiantupian;
import com.zhuji.dao.impl.dingdanpingjiaImpl;
import com.zhuji.dao.impl.jingdiantupianImpl;
import com.zhuji.entity.Edingdanpingjia;
import com.zhuji.entity.Ejingdiantupian;

public class jingdiantupianServlet extends HttpServlet {

	/**
		 * Destruction of the servlet. <br>
		 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String MethonName = request.getParameter("MethonName");
		
		/////////////////////////////////////////////////////////////////
		//||add() 添加方法
		//||delete() 添加方法
		//||update() 添加方法
		//||queryByxx() 添加方法
		///////////////////////////////////////////////////////////////
		//判断调用函数
		if (("addorup").equals(MethonName)) {
			//调用列表查询方法
			String addStr = addorup(request, response);
			out.print(addStr);
		}else if (("delete").equals(MethonName)) {
			String addStr = delete(request, response);
			out.print(addStr);
		}
		else if (("query").equals(MethonName)) {
			Ijingdiantupian ddpj = new jingdiantupianImpl();
			List<Ejingdiantupian> retList = ddpj.ShowAll();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", 0);
			map.put("msg", "");
			map.put("count", 200);
			map.put("data", retList);
			
			//将数据返回到页面上：转换为json格式数据gson[{},{},{}]
			Gson gs =new Gson();
			String json = gs.toJson(map);
			System.out.println("Ent===>"+json);
			out.print(json);
		}else if (("queryByxxx").equals(MethonName)) {
			List<Ejingdiantupian> retList = queryByxxx(request, response);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", 0);
			map.put("msg", "");
			map.put("count", 200);
			map.put("data", retList);
			
			//将数据返回到页面上：转换为json格式数据gson[{},{},{}]
			Gson gs =new Gson();
			String json = gs.toJson(map);
			System.out.println("Ent===>"+json);
			out.print(json);
		}
		out.flush();
		out.close();
	}
	
	public String addorup(HttpServletRequest request, HttpServletResponse response){
		
		String tupian_ID = request.getParameter("tupian_ID");
		String jingdian_ID = request.getParameter("jingdian_ID");
		String tupianURL = request.getParameter("tupianURL");
		
		Ijingdiantupian ddpj = new jingdiantupianImpl();
		Ejingdiantupian reList = new Ejingdiantupian(Integer.parseInt(tupian_ID),Integer.parseInt(jingdian_ID),tupianURL);
		String mssage = null;
		//调用列表查询方法
		boolean bool = ddpj.addOrUp(reList);
		if(("").equals(reList.getTupian_ID())){
			if(bool){
				mssage = "添加成功....";
			}
			else{
				mssage = "添加失败......";
			}
		}
		else{
			if(bool){
				mssage = "修改成功....";
			}
			else{
				mssage = "修改失败......";
			}
		}
		return mssage;
	}
	
	public String delete(HttpServletRequest request, HttpServletResponse response){
		
		String tupian_ID = request.getParameter("tupian_ID");
		
		Ijingdiantupian ddpj = new jingdiantupianImpl();
		String mssage = null;
		//调用列表查询方法
		boolean bool = ddpj.remove(Integer.parseInt(tupian_ID));
		if(bool){
			mssage = "删除成功....";
		}
		else{
			mssage = "删除失败....";
		}
		return mssage;
	}
	
	public List<Ejingdiantupian> queryByxxx(HttpServletRequest request, HttpServletResponse response){
		
		String tupian_ID = request.getParameter("tupian_ID");
		String jingdian_ID = request.getParameter("jingdian_ID");
		String tupianURL = request.getParameter("tupianURL");
		
		List<Ejingdiantupian> reList = null;
		String quryBy = request.getParameter("quryBy");
		Ijingdiantupian ddpj = new jingdiantupianImpl();
		if(("qurylistBytupian_ID").equals(quryBy)){
			reList = ddpj.qurylistBytupian_ID(Integer.parseInt(tupian_ID));
		}
		else if("qurylistByjingdian_ID".equals(quryBy)){
			reList = ddpj.qurylistByjingdian_ID(Integer.parseInt(jingdian_ID));
		}
		else if("qurylistBytupianURL".equals(quryBy)){
			reList = ddpj.qurylistBytupianURL(tupianURL);
		}
		return reList;
	}
	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	public void init() throws ServletException {
		// Put your code here
		String MethonName = null;
		String tupian_ID = null;
		String jingdian_ID = null;
		String tupianURL = null;
	}
}
