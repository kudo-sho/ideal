package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Debug {
	//フィールド
	private int adminId;
	private String adminName;
	private int userId;
	private String userName;


	//getter
	public int getAdminId() {
		return adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public int getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	//setter
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	//管理者リスト取得
	public static ArrayList<Debug> getAdminList() throws IdealException {
		int cnt =0;
		ArrayList<Debug> aldeb = new ArrayList<Debug>();
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		Debug deb = null;

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();

			sql = "SELECT * FROM admin";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				cnt++;
				deb = new Debug();
				deb.setAdminId(cnt);
				deb.setAdminName(rs.getString("adm_Name"));
				aldeb.add(deb);
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
		return aldeb;

	}

	//お客様リスト取得
	public static ArrayList<Debug> getUserList() throws IdealException {
		ArrayList<Debug> aldeb = new ArrayList<Debug>();
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		Debug deb = null;

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();

			sql = "SELECT * FROM user";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				deb = new Debug();
				deb.setUserId(rs.getInt("usr_id"));
				deb.setUserName(rs.getString("usr_name"));
				aldeb.add(deb);
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
		return aldeb;

	}
}