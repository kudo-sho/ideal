package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
					+ " WHERE usr_id = '"+ id +"'";
//			System.out.println(sql);
			rs = st.executeQuery(sql);
			System.out.println(rs);

			while(rs.next()){
				a.setAdmId(rs.getInt("adm_id"));
				a.setAdmName(rs.getString("adm_name"));
				a.setPassword(rs.getString("password"));
				a.setExp(rs.getString("exp"));
			}
			System.out.println("Userインスタンスに値セット完了");
//			System.out.println("名前は"+a.getadmId());
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
}
