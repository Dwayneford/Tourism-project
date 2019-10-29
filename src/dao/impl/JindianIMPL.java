package com.zhuji.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.zhuji.util.DBCPUtil;

import com.zhuji.dao.IJindian;
import com.zhuji.entity.Hezuoshangjia;
import com.zhuji.entity.Jingdian;
/**
 *  @author lhq
 */
public class JindianIMPL implements IJindian {
	
//	Connection conn = null;
//	PreparedStatement ps = null;
//	ResultSet rs = null;
	//��Ӻ�������
	@Override
	public boolean addJindian(Jingdian jingdian) {
		//�������
				Connection conn = DBCPUtil.getConn() ;
				PreparedStatement ps = null;
				ResultSet rs = null;
				JindianIMPL dao = new JindianIMPL();
				boolean bool = false;
				String sqlAdd = "insert into jingdian_table(tupian_ID,mingcheng,dizhi,miaosu) values(?,?,?,?)";
				try {
					ps = conn.prepareStatement(sqlAdd);
					
					ps.setInt(1, jingdian.getTupian_ID());
					ps.setString(2, jingdian.getMingcheng());
					ps.setString(3, jingdian.getDizhi());
					ps.setString(4, jingdian.getMiaosu());	
					
				} catch (SQLException e) {
					System.out.println("Ԥ�������");
					e.printStackTrace();
				}
				try {
					int num = ps.executeUpdate();
					if (num>0) {
						bool = true ;
						System.out.println("��ӳɹ�,��ӵ�Ԫ��Ϊ:"+jingdian.toString());
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
	//ɾ������
	@Override
	public boolean deleteJindian(int jingdian_ID) {
		//�������
				System.out.println("��Ҫɾ����Ϊ:"+jingdian_ID);
				Connection conn = null ;
				PreparedStatement ps = null;
				conn = DBCPUtil.getConn();
				boolean bool = false;
				//�޸�;
				String sqlDelete = "delete from jingdian_table where jingdian_ID=?";
				try {
					ps = conn.prepareStatement(sqlDelete);
					ps.setInt(1, jingdian_ID);
					
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
	//�޸ľ���
	@Override
	public boolean updataJindian(int jingdian_ID, Jingdian jingdian) {
		//�������
				Connection conn = DBCPUtil.getConn();
				PreparedStatement ps = null;
				boolean bool = false;
				//�޸�
				String sqlUddate = "update jingdian_table set tupian_ID=?,mingcheng=?,dizhi=?,miaosu=? where jingdian_ID=?";
				try {
					ps = conn.prepareStatement(sqlUddate);
					ps.setInt(1, jingdian.getTupian_ID());
					ps.setString(2, jingdian.getMingcheng());
					ps.setString(3, jingdian.getDizhi());
					ps.setString(4, jingdian.getMiaosu());
					ps.setInt(5, jingdian_ID);
						
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
	//��ѯ���о���
	@Override
	public List<Jingdian> queryJindian() {
		//���ȶ���˷�����Ҫ�õ��Ķ���
				Connection conn = DBCPUtil.getConn();
				//sql���Ԥ�������
				PreparedStatement ps = null;
				//���������
				ResultSet rs = null;
				ArrayList<Jingdian> list = new ArrayList<Jingdian>();
				String sql = "select * from jingdian_table";
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
						Jingdian jingdian = new Jingdian(
														rs.getInt("jingdian_ID"),
														rs.getInt("tupian_ID"), 
														rs.getString("mingcheng"), 
														rs.getString("dizhi"),
														rs.getString("miaosu"));
														
						list.add(jingdian);
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
	//�����Ʋ�ѯ����
	@Override
	public List<Jingdian> queryJindianByName(String mingcheng) {
		//���ȶ���˷�����Ҫ�õ��Ķ���
				Connection conn = DBCPUtil.getConn();
				//sql���Ԥ�������
				PreparedStatement ps = null;
				//���������
				ResultSet rs = null;
				ArrayList<Jingdian> list = new ArrayList<Jingdian>();
				String sql = "select * from jingdian_table where mingcheng=?";
				try {
					ps= conn.prepareStatement(sql);
					ps.setString(1, mingcheng);
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
						Jingdian jingdian = new Jingdian(
								rs.getInt("jingdian_ID"),
								rs.getInt("tupian_ID"), 
								rs.getString("mingcheng"), 
								rs.getString("dizhi"),
								rs.getString("miaosu"));
						list.add(jingdian);
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
		JindianIMPL dao=new JindianIMPL();
		//boolean bool = dao.addJindian(new Jingdian(10000, "mingcheng1", "dizhi1","miaosu1"));
		//System.out.println("���"+bool);
		
		//boolean bool = dao.deleteJindian(2);
		//System.out.println("ɾ��"+bool);
		
//		boolean bool = dao.updataJindian(3, new Jingdian(1999, "asd11", "das11", "fgg"));
//		System.out.println("�޸�"+bool);
		
//		List<Jingdian> list=dao.queryJindian();
//		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//			Jingdian jingdian = (Jingdian) iterator.next();
//			System.out.println(jingdian.toString());
//		}
		
//		List<Jingdian> list=dao.queryJindianByName("d");
//		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//			Jingdian jingdian = (Jingdian) iterator.next();
//			System.out.println(jingdian.toString());		
//	}
	
}
	/**
	 * ͨ��mingcheng��ѯ����
	 * ��������quryJindian
	 * @return ģ����ѯ
	 * @throws Exception */
//	@Override
//	public List<Jingdian> queryJindian(String mingcheng) {
//		List<Jingdian> list= new ArrayList<Jingdian>();
//		String qurySql = "SELECT * FROM jingdian_table  WHERE book_name LIKE '%"+mingcheng+"%'";;		
//		try {
//			ps = conn.prepareStatement(qurySql);
//			//��ȡͷ����Ϣ
//			//ResultSetMetaData header = ps.getMetaData();			
//			//��ȡ����
//			rs = ps.executeQuery();			
//			while(rs.next()){
//				Jingdian jingdianlist = new Jingdian(
//						rs.getInt("jingdian_ID"),
//						rs.getInt("tupian_ID"),
//						rs.getString("mingcheng"),
//						rs.getString("dizhi"),
//						rs.getString("miaosu"));				
//				list.add(jingdianlist);
//			}
//		} catch (SQLException e) {			
//			e.printStackTrace();
//		}
//		try {
//			DBCPUtil.closeRs(conn, rs, ps);
//		} catch (SQLException e) {			
//			e.printStackTrace();
//		}		
//		return list;		
//	}
	

}
	

