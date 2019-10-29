package com.zhuji.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuji.dao.Ixingcheng;
import com.zhuji.dao.impl.xingchengImpl;
import com.zhuji.entity.Exingcheng;

@SuppressWarnings("serial")
public class xingchengServlet extends HttpServlet {

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
			addorup(request, response);
			
		}else if (("delete").equals(MethonName)) {
			String delStr = delete(request, response);
			out.print(delStr);
		}
		else if (("query").equals(MethonName)) {
			
		}else if (("queryByxxx").equals(MethonName)) {
			queryByxxx(request, response);
		}
		out.flush();
		out.close();
	}
	
	public String addorup(HttpServletRequest request, HttpServletResponse response){
		
		String xianlu_ID = request.getParameter("xianlu_ID");
		String kaishi_time = request.getParameter("kaishi_time");
		String jiesu_time = request.getParameter("jiesu_time");
		String renshu = request.getParameter("renshu");
		String zhuangtai = request.getParameter("zhuangtai");
		String jiage = request.getParameter("jiage");
		String shuoming = request.getParameter("shuoming");
		String tixing = request.getParameter("tixing");
		
		Exingcheng reList = new Exingcheng(Integer.parseInt(xianlu_ID),kaishi_time,jiesu_time,renshu,zhuangtai,jiage,shuoming,tixing);
		Ixingcheng ddpj = new xingchengImpl();
 		String mssage = null;
		//调用列表查询方法
		boolean bool = ddpj.addOrUp(reList);
		if(("").equals(reList.getXianlu_ID())){
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
		
		Ixingcheng ddpj = new xingchengImpl();
		String xingcheng_ID = request.getParameter("xingcheng_ID");
		String mssage = null;
		boolean bool = ddpj.remove(Integer.parseInt(xingcheng_ID));
		if(bool){
			mssage = "删除成功....";
		}
		else{
			mssage = "删除失败....";
		}
		return mssage;
	}
	
	public List<Exingcheng> queryByxxx(HttpServletRequest request, HttpServletResponse response){
		
		String xianlu_ID = request.getParameter("xianlu_ID");
		String kaishi_time = request.getParameter("kaishi_time");
		
		Ixingcheng ddpj = new xingchengImpl();
		String qurtlistby = request.getParameter("qurtlistby");
		List<Exingcheng> relist = null;
		if(("qurtlistByxianlu_ID").equals(qurtlistby)){
			relist = ddpj.qurtlistByxianlu_ID(Integer.parseInt(xianlu_ID));
		}
		else if(("qurtlistBykaishi_time").equals(qurtlistby)){
			relist = ddpj.qurtlistBykaishi_time(kaishi_time);
		}
		return relist;
	}
	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	public void init() throws ServletException {
		// Put your code here
		String MethonName = null;
		String xingcheng_ID = null;
		String xianlu_ID = null;
		String kaishi_time = null;
		String jiesu_time = null;
		String renshu = null;
		String zhuangtai = null;
		String jiage = null;
		String shuoming = null;
		String tixing = null;
	}
}
