package com.journey.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.journey.dao.impl.UserImpl;
import com.journey.entity.User;
/**
 * 
 * @author DYB
 * 用户注册服务
 *
 */
public class UserRegisterServlet extends HttpServlet {

	
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
		String name = request.getParameter("user_login_name");
		String mail = request.getParameter("user_login_mail");
		User user = new User(name, mail, psw, phone);
		//调用dao的注册方法
		UserImpl dao = new UserImpl();
		boolean bool = dao.addUser(user);
		if (bool) {
			System.out.println("注册成功");
			response.sendRedirect("http://localhost:8080/My_Tourism_Project/index.html");
		}else {
			System.out.println("注册失败");
			response.sendRedirect("http://localhost:8080/My_Tourism_Project/html/zhuce.html");
		}
	}

}
