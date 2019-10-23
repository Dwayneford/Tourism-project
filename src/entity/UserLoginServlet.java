package com.journey.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.journey.dao.impl.UserImpl;
import com.journey.entity.User;
/**
 * 
 * @author DYB
 *	用户登录类
 *
 */
public class UserLoginServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码格式
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String phone = request.getParameter("user_login_phone");
		String psw = request.getParameter("user_login_password");
		User user = new User(psw, phone);
		UserImpl UserDao = new UserImpl();
		if (UserDao.userLogin(phone, psw)) {
			System.out.println(user.getUserName()+"登录成功");
			//创建session对象
			HttpSession session = request.getSession();
			//存放用户信息
			session.setAttribute("name", user.getUserName());
			session.setAttribute("pwd", psw);
			
			//判断用户是否 勾选了密码 yes
			String remerberPSW = request.getParameter("remerberPSW");
			if(("yes").equals(remerberPSW)){
				//记住密码 存入 cookie,获取 cookie对象，将对象 响应给客户端
				Cookie loginNmae = new Cookie("username", user.getUserName());
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
			response.sendRedirect("http://localhost:8080/My_Tourism_Project/html/personal.html");
		}
		
	}

}
