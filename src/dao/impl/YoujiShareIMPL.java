package com.zhuji.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhuji.dao.IYoujiShaer;
import com.zhuji.entity.Jingdian;
import com.zhuji.entity.YoujiShare;
import com.zhuji.util.DBCPUtil;

public class YoujiShareIMPL implements IYoujiShaer {

	@Override
	public boolean addYoujiShare(YoujiShare youjiShare) {
		//�������
		Connection conn = DBCPUtil.getConn() ;
		PreparedStatement ps = null;
		ResultSet rs = null;
		YoujiShareIMPL dao = new YoujiShareIMPL();
		boolean bool = false;
		String sqlAdd = "insert into youji_table(youji_Adress,youji_time,youji_Share) values(?,?,?)";
		try {
			ps = conn.prepareStatement(sqlAdd);
			
			ps.setString(1, youjiShare.getYouji_Adress());
			ps.setString(2, youjiShare.getYouji_time());
			ps.setString(3, youjiShare.getYouji_Share());
				
			
		} catch (SQLException e) {
			System.out.println("Ԥ�������");
			e.printStackTrace();
		}
		try {
			int num = ps.executeUpdate();
			if (num>0) {
				bool = true ;
				System.out.println("��ӳɹ�,��ӵ�Ԫ��Ϊ:"+youjiShare.toString());
			}
		} catch (SQLException e) {
			System.out.println("ִ��sql������");
			e.printStackTrace();
		}finally {
			try {
				DBCPUtil.closeRs(conn, rs, ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		return bool;
	}

	@Override
	public boolean deleteYoujiShare(int youji_ID) {
		
		System.out.println("��Ҫɾ����Ϊ:"+youji_ID);
		Connection conn = null ;
		PreparedStatement ps = null;
		conn = DBCPUtil.getConn();
		boolean bool = false;
		//�޸�;
		String sqlDelete = "delete from youji_table where youji_ID=?";
		try {
			ps = conn.prepareStatement(sqlDelete);
			ps.setInt(1, youji_ID);
			
			int rowcount =ps .executeUpdate();
			if (rowcount>0) {
				
				bool=true;
				System.out.println("ɾ���ɹ�");
			}
		} catch (SQLException e) {
			System.out.println("Ԥ�������");
			e.printStackTrace();
		}finally {
			try {
				DBCPUtil.closeRs(conn, null, ps);
			} catch (SQLException e) {
				System.out.println("��Դ�ر�ʧ��!");
				e.printStackTrace();
			}
		}					
		return bool;
	}

	@Override
	public boolean updataJindian(int youji_ID, YoujiShare youjiShare) {
		
		Connection conn = DBCPUtil.getConn();
		PreparedStatement ps = null;
		boolean bool = false;
		//�޸�
		String sqlUddate = "update youji_table set youji_Adress=?,youji_time=?,youji_Share=? where youji_ID=?";
		try {
			ps = conn.prepareStatement(sqlUddate);
			ps.setString(1, youjiShare.getYouji_Adress());
			ps.setString(2, youjiShare.getYouji_time());
			ps.setString(3, youjiShare.getYouji_Share());
			
			ps.setInt(4, youji_ID);
				
		} catch (SQLException e) {
			System.out.println("Ԥ����ʧ��");
			e.printStackTrace();
		}		
		try {
			int rowNum = ps.executeUpdate();
			if (rowNum>0) {
				bool=true;
				System.out.println("�޸ĳɹ�");
			}else {
				System.out.println("�޸�ʧ�ܣ����̼ҿ��ܲ�����");
			}
		} catch (SQLException e) {
			System.out.println("ִ��sql���ʧ��");
			e.printStackTrace();
		}

		
		return bool;
	}

	@Override
	public List<YoujiShare> queryYoujiShare() {
		
		Connection conn = DBCPUtil.getConn();
		//sql���Ԥ�������
		PreparedStatement ps = null;
		//���������
		ResultSet rs = null;
		ArrayList<YoujiShare> list = new ArrayList<YoujiShare>();
		String sql = "select * from youji_table";
		try {
			ps= conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs=ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//������װ�����
		try {
			while (rs.next()) {
				YoujiShare youjiShare = new YoujiShare(
												rs.getInt("youji_ID"),
												rs.getString("youji_Adress"), 
												rs.getString("youji_time"),
												rs.getString("youji_Share"));
												
				list.add(youjiShare);
			}
		} catch (SQLException e) {
			System.out.println("��������װ���������");
			e.printStackTrace();
		}finally {
			try {
				DBCPUtil.closeRs(conn, rs, ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

//	@Override
//	public List<YoujiShare> queryJindianByName(String youji_Adress) {
//		
//		return null;
//	}
//	public static void main(String[] args) {
//		YoujiShareIMPL dao=new YoujiShareIMPL();
//		boolean bool = dao.addYoujiShare(new YoujiShare("dads", "sas", "s���Ǵ�ǵ�����"));
//		System.out.println("���"+bool);}


}
