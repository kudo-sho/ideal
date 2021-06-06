package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Admin {
	//フィールド
	private String admName;
	private String password;
	private String exp;

	//getter
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
	public void setAdmName(String admName) {
		this.admName = admName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}


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
//			System.out.println(rs);

			while(rs.next()){
				a.setAdmName(rs.getString("adm_name"));
				a.setPassword(rs.getString("password"));
				a.setExp(rs.getString("exp"));
			}
//			System.out.println("名前は"+a.getAdmName());
			if(a.getAdmName() == null){
				a = null;


			}
//			else{
//				System.out.println("認証しました。");
//			}




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
