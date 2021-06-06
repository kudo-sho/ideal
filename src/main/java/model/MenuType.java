package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MenuType {

	private int typeId;
	private String typeName;

	public MenuType() {
		super();
	}


	//getter&setter
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public static ArrayList<MenuType> getAllType() throws IdealException{
		ArrayList<MenuType> almt = new ArrayList<MenuType>();
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		MenuType mt = null;

		try{
			ic = new InitialContext();
			ds = (DataSource)ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();

			sql = "SELECT * FROM menutype ORDER BY t_id";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			while(rs.next()){
				mt = new MenuType();
				mt.setTypeId(rs.getInt("t_id"));
				mt.setTypeName(rs.getString("t_Name"));
				almt.add(mt);
			}

//		}catch(Exception e) {
		}catch(SQLException | NamingException e) {
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			//				System.out.println(msg);
//			int i = IdealException.ERR_NO_DB_EXCEPTION;
//			throw new IdealException(i);
//			//↑他から取ってきた記述
		}finally{
			try{
				if(rs != null) rs.close();
				if(pst != null) pst.close();
				if(con != null) con.close();
			}
			catch(Exception e){
			}
		}
		return almt;
	}
}
