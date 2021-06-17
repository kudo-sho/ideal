package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AdminMaintenance {
	//フィールド
	private int adminId;
	private String adminName;
	private String password;
	private String adminExp;

	//getter
	public int getAdminId() {
		return adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public String getPassword() {
		return password;
	}
	public String getAdminExp() {
		return adminExp;
	}

	//setter
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setAdminExp(String adminExp) {
		this.adminExp = adminExp;
	}

	//メソッド

	//管理者リスト取得
	public static ArrayList<AdminMaintenance> getAdminList() throws IdealException {
		int cnt =0;
		ArrayList<AdminMaintenance> alam = new ArrayList<AdminMaintenance>();
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		AdminMaintenance am = null;

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();

			sql = "SELECT * FROM admin";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				cnt++;
				am = new AdminMaintenance();
				am.setAdminId(cnt);
				am.setAdminName(rs.getString("adm_Name"));
				am.setAdminExp(rs.getString("exp"));
				alam.add(am);
			}

			//	}catch(Exception e) {
		} catch (SQLException | NamingException e) {
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			//				System.out.println(msg);
			//		int i = IdealException.ERR_NO_DB_EXCEPTION;
			//		throw new IdealException(i);
			//		//↑他から取ってきた記述
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
		}
		return alam;

	}

	//管理者新規登録
	public static AdminMaintenance insert(AdminMaintenance am) throws NamingException, IdealException{

		InitialContext ic = null;
		DataSource ds =null;
		Connection con = null;
		String sql = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try{

			//DBに接続する
			ic = new InitialContext();
			ds = (DataSource)ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();

			//ユーザー情報を登録する
			sql = "INSERT INTO admin(adm_name, password, exp) VALUES(?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1,am.getAdminName());
			pst.setString(2,am.getPassword());
			pst.setString(3,am.getAdminExp());
			pst.executeUpdate();

//			//顧客IDを取得する
//			sql = "select last_insert_id() as userInfo";
//			pst = con.prepareStatement(sql);
//			rs = pst.executeQuery();
//			rs.next();
//
//			//顧客IDをセットする
//			user.setUsrId(rs.getInt("userInfo"));

		}catch(SQLException e){
			int i = IdealException.ERR_NO_DB_EXCEPTION;
			throw new IdealException(i);
		}finally{
			try {
				pst.close();
				con.close();
				ic.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return am;

	}


}