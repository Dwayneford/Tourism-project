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
import com.zhuji.dao.impl.YoujiShareIMPL;
import com.zhuji.entity.Jingdian;
import com.zhuji.entity.YoujiShare;

public class YoujiShareServlet extends HttpServlet {

	

	YoujiShareIMPL dao=new YoujiShareIMPL();
	

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		//�����ʽ����
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//ͨ�������ж�ִ�кη���
		String op = request.getParameter("op");
		if (("add").equals(op)) {
			//�����б��ѯ����
			add(request, response);
		}else if (("delete").equals(op)) {
			delete(request, response);
		}
		else if (("update").equals(op)) {
			update(request, response);
		}else if (("query").equals(op)) {
			query(request, response);
		}
	}
	private void add(HttpServletRequest request, HttpServletResponse response) {
		String youji_Adress = request.getParameter("youji_Adress");
		String youji_time = request.getParameter("youji_time");
		String youji_Share = request.getParameter("youji_Share");
		
		//��������װ��������
		YoujiShare youjiShare=new YoujiShare(youji_Adress, youji_time, youji_Share);
		//����dao����
		boolean bool=dao.addYoujiShare(youjiShare);
		String retMessage = "";
		if(bool){
			retMessage="��ӳɹ�";
		}else {
			retMessage="ʧ��";
		}
		//��ͻ����������
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(retMessage);
			//ˢ�¹ر�
			out.flush();
			out.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) {
		int youji_ID =Integer.parseInt(request.getParameter("youji_ID"));
		//����dao�㷽��

		boolean bool = dao.deleteYoujiShare(youji_ID);
		String retMessage = "";
		if (bool) {
			retMessage = "ɾ���ɹ�����";			
		}else {
			retMessage = "error����";
		}
		//��ͻ����������
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(retMessage);
			//ˢ�¹ر�
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void update(HttpServletRequest request, HttpServletResponse response) {
		
		int youji_ID = Integer.parseInt(request.getParameter("youji_ID"));
	
		String youji_Adress = request.getParameter("youji_Adress");
		String youji_time = request.getParameter("youji_time");
		String youji_Share = request.getParameter("youji_Share");
		//��������װ��������
		YoujiShare youjiShare=new YoujiShare(youji_ID, youji_time, youji_Share);
		//����dao����
		boolean bool =  dao.updataJindian(youji_ID, youjiShare);
		String retMessage = "";
		if (bool) {
			retMessage = "��ӳɹ�����";			
		}else {
			retMessage = "error����";
		}
		//��ͻ����������
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(retMessage);
			//ˢ�¹ر�
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void query(HttpServletRequest request, HttpServletResponse response) {
		List<YoujiShare> list=dao.queryYoujiShare();
		//ƴ�ӳ� layUI��Ҫ�ĸ�ʽ����
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", 200);
		map.put("data", list);
		//�����ݷ��ص�ҳ���ϣ�ת��Ϊjson��ʽ����gson[{},{},{}]
		Gson gs =new Gson();
		String json = gs.toJson(map);
		System.out.println("Ent===>"+json);
		//��ͻ������
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(json);
			//ˢ�¹ر�
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

}
