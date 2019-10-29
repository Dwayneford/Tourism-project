package com.zhuji.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.zhuji.dao.IXianlu;
import com.zhuji.entity.Hezuoshangjia;
import com.zhuji.entity.Xianlu;
import com.zhuji.util.DBCPUtil;
/**
 *  @author lhq
 */
public class XianluIMPL implements IXianlu {

	@Override
	public boolean addXianlu(Xianlu xianlu) {
		//�������
				Connection conn = DBCPUtil.getConn() ;
				PreparedStatement ps = null;
				ResultSet rs = null;
				XianluIMPL dao = new XianluIMPL();
				boolean bool = false;
				String sqlAdd = "insert into xianlu_table(jingdian_ID,pingjia) values(?,?)";
				try {
					ps = conn.prepareStatement(sqlAdd);
					ps.setInt(1, xianlu.getJingdian_ID());
					ps.setString(2, xianlu.getPingjia());
					
				} catch (SQLException e) {
					System.out.println("Ԥ�������");
					e.printStackTrace();
				}
				try {
					int num = ps.executeUpdate();
					if (num>0) {
						bool = true ;
						System.out.println("��ӳɹ�,��ӵ�Ԫ��Ϊ:"+xianlu.toString());
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
	public boolean deleteXianlu(int xianlu_ID) {
		//�������
				System.out.println("��Ҫɾ����·id:"+xianlu_ID);
				Connection conn = null ;
				PreparedStatement ps = null;
				conn = DBCPUtil.getConn();
				boolean bool = false;
				//�޸�;
				String sqlDelete = "delete from xianlu_table where xianlu_ID=?";
				try {
					ps = conn.prepareStatement(sqlDelete);
					ps.setInt(1, xianlu_ID);
					
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
	public boolean updataXianlu(int xianlu_ID,Xianlu xianlu) {
		//�������
				Connection conn = DBCPUtil.getConn();
				PreparedStatement ps = null;
				boolean bool = false;
				//�޸�
				String sqlUddate = "update xianlu_table set jingdian_ID=?,pingjia=? where xianlu_ID=?";
				try {
					ps = conn.prepareStatement(sqlUddate);
					ps.setInt(1, xianlu.getJingdian_ID());
					ps.setString(2, xianlu.getPingjia());
					ps.setInt(3, xianlu_ID);
					
					
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
						System.out.println("�޸�ʧ�ܣ��þ�����ܲ�����");
					}
				} catch (SQLException e) {
					System.out.println("ִ��sql���ʧ��");
					e.printStackTrace();
				}
				
				
				
				return bool;
	}

	@Override
	public List<Xianlu> queryXianlu() {
		//���ȶ���˷�����Ҫ�õ��Ķ���
				Connection conn = DBCPUtil.getConn();
				//sql���Ԥ�������
				PreparedStatement ps = null;
				//���������
				ResultSet rs = null;
				ArrayList<Xianlu> list = new ArrayList<Xianlu>();
				String sql = "select * from xianlu_table";
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
						Xianlu xianlu = new Xianlu(
														rs.getInt("xianlu_ID"),
														rs.getInt("jingdian_ID"), 														 
														rs.getString("pingjia"));
						list.add(xianlu);
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

	@Override
	public List<Xianlu> queryXianluByName(int xianlu_ID) {
		//���ȶ���˷�����Ҫ�õ��Ķ���
				Connection conn = DBCPUtil.getConn();
				//sql���Ԥ�������
				PreparedStatement ps = null;
				//���������
				ResultSet rs = null;
				ArrayList<Xianlu> list = new ArrayList<Xianlu>();
				String sql = "select * from xianlu_table where xianlu_ID=?";
				try {
					ps= conn.prepareStatement(sql);
					ps.setInt(1, xianlu_ID);
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
						Xianlu xianlu = new Xianlu(
														rs.getInt("xianlu_ID"),
														rs.getInt("jingdian_ID"),  
														rs.getString("pingjia"));
						list.add(xianlu);
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
	public static void main(String[] args) {
		XianluIMPL dao=new XianluIMPL();
//		boolean bool = dao.addXianlu(new Xianlu(6, "1ag"));
//		System.out.println("���"+bool);
//		
//		boolean bool = dao.deleteXianlu(1);
//		System.out.println("ɾ"+bool);
		
//		boolean bool = dao.updataXianlu(2, new Xianlu(4, "dfa"));
//		System.out.println("��"+bool);	
		
//		List<Xianlu> list=dao.queryXianlu();
//		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//			Xianlu xianlu = (Xianlu) iterator.next();
//			System.out.println(xianlu.toString());
//		}
		
//		List<Xianlu> list =dao.queryXianluByName(3);
//		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//			Xianlu xianlu = (Xianlu) iterator.next();
//			System.out.println(xianlu.toString());
//		
//	}

   }
}