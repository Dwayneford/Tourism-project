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
import com.zhuji.dao.impl.dingdanpingjiaImpl;
import com.zhuji.entity.Edingdanpingjia;

public class dingdanpingjiaServlet extends HttpServlet {

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
		if (("add").equals(MethonName)) {
			String uporadd = add(request, response);
			out.print(uporadd);
		}else if (("delete").equals(MethonName)) {
			String removeString = delete(request, response);
			out.print(removeString);
		}else if (("query").equals(MethonName)) {
			dingdanpingjiaImpl ddpj = new dingdanpingjiaImpl();
			List<Edingdanpingjia> retList = ddpj.ShowAll();
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
			List<Edingdanpingjia> retList =queryByxxx(request, response);
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
	
	public String add(HttpServletRequest request, HttpServletResponse response){
	
		String jingdian_ID = request.getParameter("jingdian_ID");
		String xingcheng_ID = request.getParameter("xingcheng_ID");
		String user_ID = request.getParameter("user_ID");
		String pingjia = request.getParameter("pingjia");
		String pingjia_time = request.getParameter("pingjia_time");
		
		dingdanpingjiaImpl ddpj = new dingdanpingjiaImpl();
		Edingdanpingjia reList = new Edingdanpingjia(Integer.parseInt(jingdian_ID),Integer.parseInt(xingcheng_ID),Integer.parseInt(user_ID),pingjia,pingjia_time);
		String mssage = null;
		//调用列表查询方法
		boolean bool = ddpj.addOrUp(reList);
		if(("").equals(reList.getPinjia_ID())){
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
		dingdanpingjiaImpl ddpj = new dingdanpingjiaImpl();
		String pinjia_ID = request.getParameter("pinjia_ID");
		boolean bool = ddpj.remove(Integer.parseInt(pinjia_ID));
		String mssage = null;
		if(bool){
			mssage = "删除成功....";
		}
		else{
			mssage = "删除失败....";
		}
		return mssage;
	}
	
	public List<Edingdanpingjia> queryByxxx(HttpServletRequest request, HttpServletResponse response){
		
		String pinjia_ID = request.getParameter("pinjia_ID");
		String jingdian_ID = request.getParameter("jingdian_ID");
		String xingcheng_ID = request.getParameter("xingcheng_ID");
		String user_ID = request.getParameter("user_ID");
		String pingjia = request.getParameter("pingjia");
		String pingjia_time = request.getParameter("pingjia_time");
		
		List<Edingdanpingjia> quryByList = null;
		dingdanpingjiaImpl ddpj = new dingdanpingjiaImpl();
		String qurtlistby = request.getParameter("qurtlistby");
		if(("qurtlistbypinjia_ID").equals(qurtlistby)){
			quryByList = ddpj.qurtlistbypinjia_ID(Integer.parseInt(pinjia_ID));
		}
		else if(("qurtlistbyjingdian_ID").equals(qurtlistby)){
			quryByList = ddpj.qurtlistbyjingdian_ID(Integer.parseInt(jingdian_ID));
		}
		else if(("qurtlistbyxingcheng_ID").equals(qurtlistby)){
			quryByList = ddpj.qurtlistbyxingcheng_ID(Integer.parseInt(xingcheng_ID));
		}
		else if(("qurtlistbyuser_ID").equals(qurtlistby)){
			quryByList = ddpj.qurtlistbyuser_ID(Integer.parseInt(user_ID));
		}
		else if(("qurtlistbypingji").equals(qurtlistby)){
			quryByList = ddpj.qurtlistbypingjia(pingjia);
		}
		else if(("qurtlistbypingjia_time").equals(qurtlistby)){
			quryByList = ddpj.qurtlistbypingjia_time(pingjia_time);
		}

		return quryByList;
	}
	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	public void init() throws ServletException {
		// Put your code here
		String MethonName = null;
		String pinjia_ID = null;
		String jingdian_ID = null;
		String xingcheng_ID = null;
		String user_ID = null;
		String pingjia = null;
		String pingjia_time =null;
	}
}
