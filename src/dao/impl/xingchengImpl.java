package com.zhuji.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhuji.dao.Ixingcheng;
import com.zhuji.entity.Exingcheng;
import com.zhuji.util.DBCPUtil;

	/**
	 * @author xxy
	 *@time 2019-10-24*/
public class xingchengImpl implements Ixingcheng{
	
	//资源初始化
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	@Override
	public List<Exingcheng> showAllList() {
		List<Exingcheng> xingchengList = new ArrayList<Exingcheng>();
		// TODO Auto-generated method stub
		conn = DBCPUtil.getConn();
		
		String qurySql = "select (xianlu_ID,kaishi_time,jiesu_time,renshu,zhuangtai,jiage,shuoming,tixing) from xingcheng";
		
		try {
			ps = conn.prepareStatement(qurySql);
			
			//获取头部信息
			//ResultSetMetaData headData = ps.getMetaData();
			
			//获取内容
			rs = ps.executeQuery();
			while(rs.next()){
				Exingcheng XCEnt = new Exingcheng(
						rs.getInt("xianlu_ID"),
						rs.getString("kaishi_time"),
						rs.getString("jiesu_time"),
						rs.getString("renshu"),
						rs.getString("zhuangtai"),
						rs.getString("jiage"),
						rs.getString("shuoming"),
						rs.getString("tixing"));
				xingchengList.add(XCEnt);
			}
			DBCPUtil.closeRs(conn, rs, ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xingchengList;
	}

	@Override
	public boolean remove(int xingcheng_ID) {
		boolean bool = false;
		// TODO Auto-generated method stub
		conn = DBCPUtil.getConn();
		
		String detSql = "delete from xingcheng_table where xingcheng_ID="+xingcheng_ID;
		
		try {
			ps = conn.prepareStatement(detSql);
			int row = ps.executeUpdate();
			if(row > 0) bool = true;
			DBCPUtil.closeRs(conn, rs, ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}

	@Override
	public boolean addOrUp(Exingcheng XCEnt) {
		boolean bool = false;
		conn = DBCPUtil.getConn();
		// TODO Auto-generated method stub
		if(("").equals(XCEnt.getXingcheng_ID()) && XCEnt.getXingcheng_ID() <0){
		
			String AddSql = "insert into xingcheng_table("
					+ "xianlu_ID,"
					+ "kaishi_time,"
					+ "jiesu_time,"
					+ "renshu,"
					+ "zhuangtai,"
					+ "jiage,"
					+ "shuoming,"
					+ "tixing)"+"values(?,?,?,?,?,?,?,?)";
			
			try {
				ps = conn.prepareStatement(AddSql);
				
				//赋值
				ps.setInt(1,XCEnt.getXianlu_ID());
				ps.setString(2,XCEnt.getKaishi_time());
				ps.setString(3,XCEnt.getJiesu_time());
				ps.setString(4,XCEnt.getRenshu());
				ps.setString(5,XCEnt.getZhuangtai());
				ps.setString(6,XCEnt.getJiage());
				ps.setString(7,XCEnt.getShuoming());
				ps.setString(8,XCEnt.getTixing());
				
				int row = ps.executeUpdate();
				if(row >0 )  bool = true;
				DBCPUtil.closeRs(conn, rs, ps);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			String upSql = "updata xingcheng_table set "
					+ "xianlu_ID = ?"
					+ "kaishi_time = ?"
					+ "jiesu_time = ?"
					+ "renshu = ?"
					+ "zhuangtai = ?"
					+ "jiage = ?"
					+ "shuoming = ?"	
					+ " tixing = ?;";
		
		try {
			ps = conn.prepareStatement(upSql);
			
			//赋值
			ps.setInt(1,XCEnt.getXianlu_ID());
			ps.setString(2,XCEnt.getKaishi_time());
			ps.setString(3,XCEnt.getJiesu_time());
			ps.setString(4,XCEnt.getRenshu());
			ps.setString(5,XCEnt.getZhuangtai());
			ps.setString(6,XCEnt.getJiage());
			ps.setString(7,XCEnt.getShuoming());
			ps.setString(8,XCEnt.getTixing());
			
			int row = ps.executeUpdate();
			if(row > 0) bool = true;
			DBCPUtil.closeRs(conn, rs, ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		return bool;
	}
	
	public List<Exingcheng> qurtlist(String keyName ,Object Value){
		
		//资源初始化
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Exingcheng> xingchengList = new ArrayList<Exingcheng>();
		// TODO Auto-generated method stub
		conn = DBCPUtil.getConn();
		
		String qurySql = "select xianlu_ID,kaishi_time,jiesu_time,renshu,zhuangtai,jiage,shuoming,tixing from xingcheng_table where "+keyName+"="+Value;
		
		try {
			ps = conn.prepareStatement(qurySql);
			
			//获取头部信息
			//ResultSetMetaData headData = ps.getMetaData();
			
			//获取内容
			rs = ps.executeQuery();
			while(rs.next()){
				Exingcheng XCEnt = new Exingcheng(
						rs.getInt("xianlu_ID"),
						rs.getString("kaishi_time"),
						rs.getString("jiesu_time"),
						rs.getString("renshu"),
						rs.getString("zhuangtai"),
						rs.getString("jiage"),
						rs.getString("shuoming"),
						rs.getString("tixing"));
				xingchengList.add(XCEnt);
			}
			DBCPUtil.closeRs(conn, rs, ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xingchengList;

	}

	@Override
	public List<Exingcheng> qurtlistByxingcheng_ID(int xingcheng_ID) {
		// TODO Auto-generated method stub
		return qurtlist("xingcheng_ID",xingcheng_ID);
	}

	@Override
	public List<Exingcheng> qurtlistByxianlu_ID(int xianlu_ID) {
		// TODO Auto-generated method stub
		return qurtlist("xianlu_ID",xianlu_ID);
	}

	@Override
	public List<Exingcheng> qurtlistBykaishi_time(String kaishi_time) {
		// TODO Auto-generated method stub
		return qurtlist("kaishi_time",kaishi_time);
	}

	@Override
	public List<Exingcheng> qurtlistByjiesu_time(String jiesu_time) {
		// TODO Auto-generated method stub
		return qurtlist("jiesu_time",jiesu_time);
	}

	@Override
	public List<Exingcheng> qurtlistqurtlistByrenshu(String renshu) {
		// TODO Auto-generated method stub
		return qurtlist("jiesu_time",renshu);
	}

	@Override
	public List<Exingcheng> qurtlistByzhuangtai(String zhuangtai) {
		// TODO Auto-generated method stub
		return qurtlist("zhuangtai",zhuangtai);
	}

	@Override
	public List<Exingcheng> qurtlistByjiage(String jiage) {
		// TODO Auto-generated method stub
		return qurtlist("jiage",jiage);
	}

	@Override
	public List<Exingcheng> qurtlistByshuoming(String shuoming) {
		// TODO Auto-generated method stub
		return qurtlist("shuoming",shuoming);
	}

	@Override
	public List<Exingcheng> qurtlistBytixingD(String tixing) {
		// TODO Auto-generated method stub
		return qurtlist("tixing",tixing);
	}
}
