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
		}else if (("queryByName").equals(op)) {
			queryByName(request, response);
		}
	}
	private void add(HttpServletRequest request, HttpServletResponse response) {
		//���ܲ���		
		//ID��ҪStringǿתΪint
		int jingdian_ID = Integer.parseInt(request.getParameter("jingdian_ID"));
		String pingjia = request.getParameter("pingjia");
		//��������װ��������
		Xianlu xianlu= new Xianlu(jingdian_ID, pingjia);
		//����dao����
		boolean bool=dao.addXianlu(xianlu);
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
		int xianlu_ID =Integer.parseInt(request.getParameter("xianlu_ID"));
		//����dao�㷽��
		boolean bool = dao.deleteXianlu(xianlu_ID);
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
		//��ȡ����
		int xianlu_ID = Integer.parseInt(request.getParameter("xianlu_ID"));
		int jingdian_ID = Integer.parseInt(request.getParameter("jingdian_ID"));
		String pingjia = request.getParameter("pingjia");
		//��������װ��������
		Xianlu xianlu=new Xianlu(jingdian_ID, pingjia);
		//����dao����
		boolean bool =  dao.updataXianlu(xianlu_ID, xianlu);
		String retMessage = "";
		if (bool) {
			retMessage = "�޸ĳɹ�����";			
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
		List<Xianlu> list=dao.queryXianlu();
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
	private void queryByName(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ����
		int xianlu_ID = Integer.parseInt(request.getParameter("xianlu_ID"));
		//����dao�㷽��
		List<Xianlu> list=dao.queryXianluByName(xianlu_ID);
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
