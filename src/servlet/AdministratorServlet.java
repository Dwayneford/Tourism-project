package com.zhuji.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.zhuji.dao.impl.AdministratorIMPL;
import com.zhuji.dao.impl.UserIMPL;
import com.zhuji.entity.Administrator;
import com.zhuji.entity.Jingdian;
import com.zhuji.entity.user;

public class AdministratorServlet extends HttpServlet {
	AdministratorIMPL dao=new AdministratorIMPL();

	
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
		if (("administratorLogin").equals(op)) {
			//登录
			administratorLogin(request, response);		
		}else if (("add").equals(op)) {
			add(request, response);
		}else if (("delete").equals(op)) {
			delete(request, response);
		}else if (("update").equals(op)) {
			update(request, response);
		}else if (("query").equals(op)) {
			query(request, response);
		}
	}
	private void administratorLogin(HttpServletRequest request, HttpServletResponse response) {
		//设置编码格式
		response.setContentType("text/html");
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		
		String admin_name = request.getParameter("admin_name");
		String admin_pwd = request.getParameter("admin_pwd");
		Administrator administrator = new Administrator(admin_name, admin_pwd);
		AdministratorIMPL AdmDao = new AdministratorIMPL();
		if (AdmDao.administratorLogin(admin_name, admin_pwd)) {
			System.out.println(administrator.getadmin_name()+"登录成功");
			//创建session对象
			HttpSession session = request.getSession();
			//存放用户信息
			session.setAttribute("admin_name", administrator.getadmin_name());
			session.setAttribute("admin_pwd", admin_pwd);
			
			//判断用户是否 勾选了密码 yes
			String remerberPSW = request.getParameter("remerberPSW");
			if(("yes").equals(remerberPSW)){
				//记住密码 存入 cookie,获取 cookie对象，将对象 响应给客户端
				Cookie loginNmae = new Cookie("username", administrator.getadmin_name());
				Cookie loginPwd = new Cookie("password", admin_pwd);
				//状态
				Cookie loginType = new Cookie("remerberPSW", remerberPSW);
				
				//设置 有效时间
				loginNmae.setMaxAge(60*60*24*12);
				loginPwd.setMaxAge(60*60*24*12);
				loginType.setMaxAge(60*60*24*12);
				
				//服务共享设置 通过后缀名称 确定哪些客户端可以 获取cookie值
				loginNmae.setPath("/");//JavaWeb_02
				loginPwd.setPath("/");
				loginType.setPath("/");
				//跨域共享 方法
				loginNmae.setDomain("localhost");
				loginPwd.setDomain("localhost");
				loginType.setDomain("localhost");
				
				//响应给 客户端
				response.addCookie(loginNmae);
				response.addCookie(loginPwd);
				response.addCookie(loginType);
				
			}else{
				//不记住密码 清空 cookie, 获取 客户端cookie
				Cookie[] cookies = request.getCookies();
				
				if(cookies!=null && cookies.length>0){
					//开始 遍历
					for (int i=0;i<cookies.length;i++) {
						if(("username").equals(cookies[i].getName())){
							cookies[i].setMaxAge(0);
							cookies[i].setPath("/");
							//响应给客户端
							response.addCookie(cookies[i]);
						}
						if(("password").equals(cookies[i].getName())){
							cookies[i].setMaxAge(0);
							cookies[i].setPath("/");
							//响应给客户端
							response.addCookie(cookies[i]);					
						}
						if(("islogin").equals(cookies[i].getName())){
							cookies[i].setMaxAge(0);
							cookies[i].setPath("/");
							//响应给客户端
							response.addCookie(cookies[i]);
						}
					}
				}
			}
					//页面跳转				
					try {
						response.sendRedirect("http://localhost:8080/zhuji/admin/index/index.jsp");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				
			
	}
	private void add(HttpServletRequest request, HttpServletResponse response) {
		//接受参数		
		//ID需要String强转为int
		//int admin_id = Integer.parseInt(request.getParameter("admin_id"));
		String admin_name = request.getParameter("admin_name");
		String admin_pwd = request.getParameter("admin_pwd");
		String admin_tel = request.getParameter("admin_tel");
		
		//将参数封装到对象中
		Administrator administrator=new Administrator(admin_name, admin_pwd, admin_tel);
		//调用dao方法
		boolean bool=dao.addAdministrator(administrator);
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
		//获取参数
		//int tupian_ID = Integer.parseInt(request.getParameter("tupian_ID"));
		int admin_id = Integer.parseInt(request.getParameter("admin_id"));
		//调用dao层方法

		boolean bool = dao.deleteAdministrator(admin_id);
		String retMessage = "";;
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
		//接受参数		
		//ID需要String强转为int
		int admin_id = Integer.parseInt(request.getParameter("admin_id"));
		String admin_name = request.getParameter("admin_name");
		String admin_pwd = request.getParameter("admin_pwd");
		String admin_role = request.getParameter("admin_role");
		String admin_tel = request.getParameter("admin_tel");
		String admin_email = request.getParameter("admin_email");
		String admin_photo_url = request.getParameter("admin_photo_url");
		String admin_time = request.getParameter("admin_time");		
		//将参数封装到Hezuoshangjia对象中
		Administrator administrator = new  Administrator(admin_name, admin_pwd, admin_role, admin_tel, admin_email, admin_photo_url, admin_time);
		//调用dao方法
		boolean bool =  dao.updataAdministrator(admin_id, administrator);
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
    private void query(HttpServletRequest request, HttpServletResponse response) {
    	//调用dao层方法，并接受参数
    	List<Administrator> List = dao.queryAdministrator();
    	//拼接成 layUI需要的格式数据
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("code", 0);
    	map.put("msg", "");
    	map.put("count", 200);
    	map.put("data", List);
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

