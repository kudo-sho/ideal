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
	private int admId;
	private String admName;
	private String admPassword;
	private String admExp;

	private int usrId;
	private String usrName;
	private String usrPassword;
	private String address;
	private String phone;
	private String mail;
	private String usrExp;

	//getter
	public int getAdmId() {
		return admId;
	}
	public String getAdmName() {
		return admName;
	}
	public String getAdmPassword() {
		return admPassword;
	}
	public String getAdmExp() {
		return admExp;
	}

	public int getUsrId() {
		return usrId;
	}
	public String getUsrName() {
		return usrName;
	}
	public String getUsrPassword() {
		return usrPassword;
	}
	public String getAddress() {
		return address;
	}
	public String getPhone() {
		return phone;
	}
	public String getMail() {
		return mail;
	}
	public String getUsrExp() {
		return usrExp;
	}

	//setter
	public void setAdmId(int admId) {
		this.admId = admId;
	}
	public void setAdmName(String admName) {
		this.admName = admName;
	}
	public void setAdmPassword(String admPassword) {
		this.admPassword = admPassword;
	}
	public void setAdmExp(String admExp) {
		this.admExp = admExp;
	}

	public void setUsrId(int usrId) {
		this.usrId = usrId;
	}
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}
	public void setUsrPassword(String usrPassword) {
		this.usrPassword = usrPassword;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public void setUsrExp(String usrExp) {
		this.usrExp = usrExp;
	}


	//管理者リスト取得
	public static ArrayList<Debug> getAdminList() throws IdealException {
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
				deb = new Debug();
				deb.setAdmId(rs.getInt("adm_id"));
				deb.setAdmName(rs.getString("adm_name"));
				deb.setAdmPassword("password");
				deb.setAdmExp("exp");
				aldeb.add(deb);
			}

		} catch (SQLException | NamingException e) {
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);

		} finally {
			try {
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				if (con != null) con.close();
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
				deb.setUsrId(rs.getInt("usr_id"));
				deb.setUsrName(rs.getString("usr_name"));
				deb.setUsrPassword("password");
				deb.setAddress("address");
				deb.setPhone("phone");
				deb.setMail("mail");
				deb.setUsrExp("exp");
				aldeb.add(deb);
			}

		} catch (SQLException | NamingException e) {
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);

		} finally {
			try {
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				if (con != null) con.close();
			} catch (Exception e) {
			}
		}
		return aldeb;
	}


}