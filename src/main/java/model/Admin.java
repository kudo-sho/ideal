package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Admin {
	//フィールド
	private int admId;
	private String admName;
	private String password;
	private String exp;

	//getter
	public int getAdmId() {
		return admId;
	}
	public String getAdmName() {
		return admName;
	}
	public String getPassword() {
		return password;
	}
	public String getExp() {
		return exp;
	}

	//setter
	public void setAdmId(int admId) {
		this.admId = admId;
	}
	public void setAdmName(String admName) {
		this.admName = admName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}



	//メソッド
	//ログイン処理
	public Admin login(String admName, String password) throws IdealException  {
//		System.out.println("beans起動");
		String name = admName;
		String pass = password;
		Admin a = null;
//		System.out.println("引数は"+ name +"と"+pass);

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			a = new Admin();
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			st = con.createStatement();
			String sql = "SELECT * FROM admin"
					+ " WHERE adm_name = '"+ name +"'"
							+ " AND password = '"+ pass +"'";
//			System.out.println(sql);
			rs = st.executeQuery(sql);

			//問い合わせた管理者情報をadminオブジェクトにセット
			while(rs.next()){
				a.setAdmId(rs.getInt("adm_id"));
				a.setAdmName(rs.getString("adm_name"));
				a.setPassword(rs.getString("password"));
				a.setExp(rs.getString("exp"));
			}
//			System.out.println("名前は"+a.getAdmName());//名前のセット確認(デバッグ用)
			//該当レコードがなかった場合はadminにnullをセット
			if(a.getAdmName() == null){
				a = null;
			}
		}catch(SQLException | ClassNotFoundException e) {
			int i = IdealException.ERR_NO_DB_EXCEPTION;
			throw new IdealException(i);

		}finally {
			try {
				if(con != null) con.close();
				if(st != null) st.close();
				if(rs != null) rs.close();
			}catch(Exception e) {
			}
		}
		return a;
	}

	//
	//管理者情報を取得（変更や削除に使用）
	public static Admin getAdmin(int admId)throws IdealException  {
//		System.out.println("beans起動");
		int id = admId;
		Admin a = null;

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			a = new Admin();
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			st = con.createStatement();
			String sql = "SELECT * FROM admin"
					+ " WHERE adm_id = "+ id ;
//			System.out.println(sql);
			rs = st.executeQuery(sql);

			//問い合わせた管理者情報をadminオブジェクトにセット
			while(rs.next()){
				a.setAdmId(rs.getInt("adm_id"));
				a.setAdmName(rs.getString("adm_name"));
				a.setPassword(rs.getString("password"));
				a.setExp(rs.getString("exp"));
			}
			System.out.println("Adminインスタンスに値セット完了");
//			System.out.println("名前は"+a.getadmId());
			//該当レコードがなかった場合はadminにnullをセット
			if(id == 0){
				a = null;
			}
		}catch(SQLException | ClassNotFoundException e) {
			int i = IdealException.ERR_NO_DB_EXCEPTION;
			throw new IdealException(i);
		}finally {
			try {
				if(con != null) con.close();
				if(st != null) st.close();
				if(rs != null) rs.close();
			}catch(Exception e) {
			}
		}
		return a;
	}

	//管理者リスト取得(管理者情報メンテナンス画面で使用)
	public static ArrayList<Admin> getAdminList() throws IdealException {
		ArrayList<Admin> alam = new ArrayList<Admin>(); //戻り値用のリスト
		Admin am = null; //リストに入れるオブジェクト
		//DB接続準備
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();

			String sql = "SELECT * FROM admin";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			//問い合わせた管理者情報をadminオブジェクトにセット
			while (rs.next()) {
				am = new Admin();
				am.setAdmId(rs.getInt("adm_id"));
				am.setAdmName(rs.getString("adm_Name"));
				am.setExp(rs.getString("exp"));
				alam.add(am); //取得したadminオブジェクトをarrayListに追加
			}

			//	}catch(Exception e) {
		} catch (SQLException | NamingException e) {
			int i = IdealException.ERR_NO_DB_EXCEPTION;
			throw new IdealException(i);
		} finally {
			try {
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				if (con != null) con.close();
			} catch (Exception e) {
			}
		}
		return alam;
	}

	//管理者新規登録
	public static Admin insert(Admin adm) throws  IdealException{
		//DBに接続準備
		InitialContext ic = null;
		DataSource ds =null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;

		try{
			//DBに接続する
			ic = new InitialContext();
			ds = (DataSource)ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();

			//管理者情報をadminテーブルにinsert
			sql = "INSERT INTO admin(adm_name, password, exp) VALUES(?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1,adm.getAdmName());
			pst.setString(2,adm.getPassword());
			pst.setString(3,adm.getExp());
			pst.executeUpdate();

			//新規登録した管理者IDを取得する
			sql = "select last_insert_id() as adminInfo";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			rs.next(); //最後の行を選択

			//管理者IDをセットする
			adm.setAdmId(rs.getInt("adminInfo"));

		}catch(SQLException | NamingException e){
			int i = IdealException.ERR_NO_DB_EXCEPTION;
			throw new IdealException(i);
		}finally{
			try {//close処理
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				if (con != null) con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return adm;
	}

}
