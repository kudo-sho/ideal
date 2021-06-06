package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CourseCtl {

	//フィールド
	private int c_id;
	private int m_id;

	//getter
	public int getC_id() {
		return c_id;
	}
	public int getM_id() {
		return m_id;
	}

	//setter
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public void setM_id(int m_id) {
		this.m_id = m_id;
	}

	//メソッド

	public static void courseMenuChk(int m_id) throws IdealException{
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			st = con.createStatement();
			String sql = "SELECT * FROM coursectl"
					+ " WHERE m_id = "+m_id;
			//System.out.println(sql);
			rs = st.executeQuery(sql);

			if(rs.next() == true){
				//要素があった場合は例外を投げる
				int i = IdealException.ERR_NO_NOT_MENU_DELETE;
				throw new IdealException(i);
			}
		}catch(SQLException | ClassNotFoundException e) {
			//データベースでエラーが起きたときの例外
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
	}

}
