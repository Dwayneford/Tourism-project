package com.zhuji.dao.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.zhuji.dao.IAdministrator;
import com.zhuji.entity.Administrator;

import com.zhuji.util.DBCPUtil;
import com.zhuji.util.MD5Encoder;

public class AdministratorIMPL implements IAdministrator{

	@Override
	public boolean addAdministrator(Administrator administrator) {
		//�������
		Connection conn =DBCPUtil.getConn();
		PreparedStatement ps = null;
		boolean bool = false;
		//�жϸ��ֻ����Ƿ����
		AdministratorIMPL dao=new AdministratorIMPL();
		Administrator list=dao.queryAdministratorByTel(administrator.getadmin_tel());
		if (list == null) {
			String sqlAdd = "insert into guanliyue_table(admin_name, admin_pwd, admin_tel) values(?,?,?)";
			
		
			try {
				ps= conn.prepareStatement(sqlAdd);
				ps.setString(1, administrator.getadmin_name());
				
				try {
					ps.setString(2, MD5Encoder.getEncryptedPwd(administrator.getadmin_pwd()));
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				ps.setString(3, administrator.getadmin_tel());
				
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			
			//����sql
			int rowNum;
			try {
				rowNum = ps.executeUpdate();
				if (rowNum>0) {
					bool=true;
					System.out.println("��ӳɹ�,��ӵ�Ԫ��Ϊ:"+administrator.toString());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					DBCPUtil.closeRs(conn, null, ps);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		
		return bool;
	}

	@Override
	public boolean deleteAdministrator(int admin_id) {
		//�������
		System.out.println("��Ҫɾ�����û�Ϊ:"+admin_id);
		Connection conn = null ;
		PreparedStatement ps = null;
		conn = DBCPUtil.getConn();
		boolean bool = false;
		//�޸�;
		String sqlDelete = "delete from guanliyue_table where admin_id=?";
		try {
			ps = conn.prepareStatement(sqlDelete);
			ps.setInt(1, admin_id);
			
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
				e.printStackTrace();
			}
		}				
		return bool;
	}

	@Override
	public boolean updataAdministrator(int admin_id, Administrator administrator) {
		Connection conn = null ;
		PreparedStatement ps = null;
		conn = DBCPUtil.getConn();
		boolean bool = false;
		
		String sqlDelete = "delete from guanliyue_table where admin_id=?";
		try {
			ps =conn.prepareStatement(sqlDelete);
			ps.setInt(1, admin_id);
			int rowcount =ps .executeUpdate();
			if (rowcount>0) {				
				bool=true;
				System.out.println("ɾ���ɹ�");
		} 
		}
			catch (SQLException e) {
			System.out.println("Ԥ�������");
			e.printStackTrace();
		}
		finally {
			try {
				DBCPUtil.closeRs(conn, null, ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}				
		return bool;
	}

	@Override
	public List<Administrator> queryAdministrator() {
		//���ȶ���˷�����Ҫ�õ��Ķ���
		Connection conn = null;
		//sql���Ԥ�������
		PreparedStatement ps = null;
		//���������
		ResultSet rs = null;
		
		List<Administrator> list = new ArrayList<Administrator>();	
		String sql = "select * from guanliyue_table";
		
		//�������ݿ⣬��ѯ����
		//����һ��JDBCUtil��Ķ���,��ʹ�������ӷ���
		conn = DBCPUtil.getConn();
		//�ô����Ӷ���ķ�����sql���Ԥ����,�õ�һ��Ԥ�������
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.println("Ԥ����sql���ʧ�ܣ�");
			e.printStackTrace();
		}
		//Ԥ������ɺ�ִ��sql���,�õ������
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("��ѯʧ��!");
			e.printStackTrace();
		}
		//��������װ�����
		try {
			while (rs.next()) {
				Administrator str = new Administrator(
								rs.getInt("admin_id"),
								rs.getString("admin_name"),
								rs.getString("admin_pwd"),
								rs.getString("admin_role"), 
								rs.getString("admin_tel"),
								rs.getString("admin_email"),
								rs.getString("admin_photo_url"), 
								rs.getString("admin_time"));
				
				list.add(str);
			}
		} catch (SQLException e) {
			System.out.println("��������װ���������");
			e.printStackTrace();
		}finally {
			try {
				DBCPUtil.closeRs(conn, rs, ps);
			} catch (SQLException e) {
				// 
				e.printStackTrace();
			}
		}
		return list;
	
	}
	@Override
	public Administrator queryAdministratorByName(String admin_name) {
		//�������
				Connection conn = DBCPUtil.getConn() ;
				PreparedStatement ps = null;
				ResultSet rs = null;
				Administrator list = null;
				String sql = "select * from guanliyue_table where admin_name = ?";
				//�������ݿ��ѯ����
				try {
					ps= conn.prepareStatement(sql);
					ps.setString(1, admin_name);
				} catch (SQLException e) {
					// 
					e.printStackTrace();
				}
				try {
					rs = ps.executeQuery();
				} catch (SQLException e) {
					// 
					e.printStackTrace();
				}
				try {
					while (rs.next()) {
						list = new Administrator(
								rs.getInt("admin_id"),
								rs.getString("admin_name"),
								rs.getString("admin_pwd"),
								rs.getString("admin_role"), 
								rs.getString("admin_tel"),
								rs.getString("admin_email"),
								rs.getString("admin_photo_url"), 
								rs.getString("admin_time"));						
					}
				} catch (SQLException e) {
					// 
					e.printStackTrace();
				}finally {
					try {
						DBCPUtil.closeRs(conn, rs, ps);
					} catch (SQLException e) {
						// 
						e.printStackTrace();
					}
				}
				
				return list;
	}
	@Override
	public Administrator queryAdministratorByTel(String admin_tel) {
		
		//�������
		Connection conn = DBCPUtil.getConn() ;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Administrator list = null;
		String sql = "select * from guanliyue_table where admin_tel = ?";
		//�������ݿ��ѯ����
		try {
			ps= conn.prepareStatement(sql);
			ps.setString(1, admin_tel);
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				list = new Administrator(
						rs.getInt("admin_id"),
						rs.getString("admin_name"),
						rs.getString("admin_pwd"),
						rs.getString("admin_role"), 
						rs.getString("admin_tel"),
						rs.getString("admin_email"),
						rs.getString("admin_photo_url"), 
						rs.getString("admin_time"));						
			}
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		}finally {
			try {
				DBCPUtil.closeRs(conn, rs, ps);
			} catch (SQLException e) {
				// 
				e.printStackTrace();
			}
		}
		
		return list;
	}
	public boolean administratorLogin(String name, String psw) {
		AdministratorIMPL dao = new AdministratorIMPL();
		//��ȡ���û������ݿ��е���Ϣ
		Administrator administrator=dao.queryAdministratorByName(name);
		boolean bool = false;
		try {
			//����������������ݿ��е�����Ա�
			bool= MD5Encoder.validPassword(psw, administrator.getadmin_pwd());
			if (bool) {
				System.out.println("��¼�ɹ�,�û���Ϊ��"+administrator.getadmin_name());
			}else {
				System.out.println("��¼ʧ��!");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return bool;

	}
//	public static void main(String[] args) {
//		AdministratorIMPL dao = new AdministratorIMPL();
//		Administrator administrator = dao.queryAdministratorByTel("15223532333");
//		System.out.println(administrator.getadmin_name());
		
//		boolean bool = dao.addAdministrator(new Administrator("sdas", "13431","13431"));
//		
//		System.out.println("���"+bool);
		
//		boolean bool = dao.deleteAdministrator(2);
//		System.out.println("ɾ��"+bool);
		
//		List<Administrator> list =dao.queryAdministrator();
//		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//			Administrator str = (Administrator) iterator.next();
//			System.out.println(str.getadmin_name());
//		}
//		boolean bool = dao.administratorLogin("sdas", "13431");
//		System.out.println("��¼"+bool);
//		
//		
//	}

}
