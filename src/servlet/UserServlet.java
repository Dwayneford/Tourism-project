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
import com.zhuji.dao.impl.UserIMPL;

import com.zhuji.entity.user;

public class UserServlet extends HttpServlet {
	UserIMPL dao = new UserIMPL();

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
		if (("userLogin").equals(op)) {
			//调用列表查询方法
			userlogin(request, response);
		}else if (("userRegister").equals(op)) {
			userRegister(request, response);
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


	private void query(HttpServletRequest request, HttpServletResponse response) {
		//调用dao层方法，并接受参数
		List<user> retList = dao.queryUsers();
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
//		user user = new user(user_name, user_password, user_mail, usre_telphone, user_photo, user_qq, user_status, user_remark, user_createTime, user_sex, user_happy, user_adress)
		int userId = Integer.parseInt(request.getParameter("user_name"));
		String user_name = request.getParameter("user_name");
		String user_password = request.getParameter("user_password");
		String user_mail = request.getParameter("user_mail");
		String user_telphone = request.getParameter("usre_telphone");
		String user_photo = request.getParameter("user_photo");
		String user_qq = request.getParameter("user_qq");
		String user_status = request.getParameter("user_status");
		String user_remark = request.getParameter("user_remark");
		String user_createTime = request.getParameter("user_createTime");
		String user_sex = request.getParameter("user_sex");
		String user_happy = request.getParameter("user_happy");
		String user_adress = request.getParameter("user_adress");

		//将参数封装到Hezuoshangjia对象中
		user userEnt = new user(user_name, user_password, user_mail, user_telphone, user_photo, user_qq, user_status, user_remark, user_createTime, user_sex, user_happy, user_adress); 
		//调用dao方法
		boolean bool =  dao.updateUser(userId, userEnt);
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
		String user_phone = request.getParameter("user_phone");
		//调用dao层方法

		boolean bool = dao.deleteUser(user_phone);
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
		//用户暂时只能注册，不能新增
		
	}


	private void userRegister(HttpServletRequest request, HttpServletResponse response) {
		//设置编码格式
		response.setContentType("text/html");
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		String phone = request.getParameter("user_login_phone");
		String psw = request.getParameter("user_login_password");
		String name = request.getParameter("user_login_name");
		user user = new user(name,  psw, phone);
		//调用dao的注册方法
		UserIMPL dao = new UserIMPL();
		boolean bool = dao.addUser(user);
		if (bool) {
			System.out.println("注册成功");
			
			//创建session对象
			HttpSession session = request.getSession();
			//存放用户信息
			session.setAttribute("name", user.getUser_name());
			session.setAttribute("pwd", psw);
			
			
//			<%
//			String name =null;
//			if(session.getAttribute("name") != null){
//				name =session.getAttribute("name").toString();
//			}
//			 
//			%>
//			<%=name %>
			
			
			try {
				response.sendRedirect("http://localhost:8080/zhuji/index.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("注册失败");
			try {
				response.sendRedirect("http://localhost:8080/zhuji/html/zhuce.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}


	private void userlogin(HttpServletRequest request, HttpServletResponse response) {
		//设置编码格式
		response.setContentType("text/html");
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		
		String phone = request.getParameter("user_login_phone");
		String psw = request.getParameter("user_login_password");
		user user = new user(psw, phone);
		UserIMPL UserDao = new UserIMPL();
		if (UserDao.userLogin(phone, psw)) {
			System.out.println(user.getUser_name()+"登录成功");
			//创建session对象
			HttpSession session = request.getSession();
			//存放用户信息
			session.setAttribute("name", user.getUser_name());
			session.setAttribute("pwd", psw);
			
			//判断用户是否 勾选了密码 yes
			String remerberPSW = request.getParameter("remerberPSW");
			if(("yes").equals(remerberPSW)){
				
				//设置session
				
				
				
				
				//记住密码 存入 cookie,获取 cookie对象，将对象 响应给客户端
				Cookie loginNmae = new Cookie("username", user.getUser_name());
				Cookie loginPwd = new Cookie("password", psw);
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
				response.sendRedirect("http://localhost:8080/zhuji/html/personal.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("登录失败");
		}

		
	}

}
