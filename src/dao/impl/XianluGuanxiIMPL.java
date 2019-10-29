package com.zhuji.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.zhuji.dao.IXainlu_jingdain_guanxi;
import com.zhuji.entity.Xainlu_jingdain_guanxi;
import com.zhuji.entity.Xianlu;
import com.zhuji.util.DBCPUtil;
/**
 *  @author lhq
 */
public class XianluGuanxiIMPL  implements IXainlu_jingdain_guanxi{

	@Override
	public boolean addguanxi(Xainlu_jingdain_guanxi  guanxi) {
		//�������
		Connection conn = DBCPUtil.getConn();
		PreparedStatement ps = null;
		boolean bool = false;
		//�޸�
		String sqlUddate = "insert into xainlu_jingdain_guanxi_table (xianlu_ID,jingdian_ID) values(?,?)";
		try {
			ps = conn.prepareStatement(sqlUddate);
			ps.setInt(1, guanxi.getJingdian_ID());
			ps.setInt(2, guanxi.getXianlu_ID());
			
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
	public boolean deleteguanxi(int ID) {
		//�������
		System.out.println("��Ҫɾ����·id:"+ID);
		Connection conn = null ;
		PreparedStatement ps = null;
		conn = DBCPUtil.getConn();
		boolean bool = false;
		//�޸�;
		String sqlDelete = "delete from xainlu_jingdain_guanxi_table where ID=?";
		try {
			ps = conn.prepareStatement(sqlDelete);
			ps.setInt(1, ID);
			
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
	public boolean updataguanxi( int ID ,Xainlu_jingdain_guanxi  guanxi) {
		//�������
				Connection conn = DBCPUtil.getConn();
				PreparedStatement ps = null;
				boolean bool = false;
				//�޸�
				String sqlUddate = "update xainlu_jingdain_guanxi_table set xianlu_ID=?,jingdian_ID=? where ID= ?";
				try {
					ps = conn.prepareStatement(sqlUddate);
					ps.setInt(1, guanxi.getXianlu_ID());
					ps.setInt(2, guanxi.getJingdian_ID());
					ps.setInt(3, ID);

					
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
	public List<Xainlu_jingdain_guanxi> queryguanxi() {
		//���ȶ���˷�����Ҫ�õ��Ķ���
		Connection conn = DBCPUtil.getConn();
		//sql���Ԥ�������
		PreparedStatement ps = null;
		//���������
		ResultSet rs = null;
		ArrayList<Xainlu_jingdain_guanxi> list = new ArrayList<Xainlu_jingdain_guanxi>();
		String sql = "select * from xainlu_jingdain_guanxi_table";
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
				Xainlu_jingdain_guanxi str = new Xainlu_jingdain_guanxi(
												rs.getInt("ID"),
												rs.getInt("xianlu_ID"), 														 
												rs.getInt("jingdian_ID"));
				list.add(str);
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
		XianluGuanxiIMPL dao=new XianluGuanxiIMPL();
//		boolean bool = dao.addguanxi(new Xainlu_jingdain_guanxi(3, 41));
//		System.out.println("���"+bool);
		
//		boolean bool = dao.deleteguanxi(2);
//		System.out.println("ɾ��"+bool);
		
//		boolean bool = dao.updataguanxi(3, new Xainlu_jingdain_guanxi(2, 4));
//		System.out.println("��"+bool);
		
//		List<Xainlu_jingdain_guanxi> list=dao.queryguanxi();
//		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//			Xainlu_jingdain_guanxi guanxi = (Xainlu_jingdain_guanxi) iterator.next();
//			System.out.println(guanxi.toString());
//		
//		
//	}

	

	}
}
