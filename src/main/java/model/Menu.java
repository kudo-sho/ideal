package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import controller.MenuOperationSvl;

public class Menu {

	private int menuId;
	private String menuName;
	private String detail;
	private int orderFlg;
	private int price;
	private int typeId;
	private String typeName;

	//コンストラクター
	public Menu() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	//setter & getter
	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getOrderFlg() {
		return orderFlg;
	}

	public void setOrderFlg(int orderFlg) {
		this.orderFlg = orderFlg;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}



	//未完成
	//メソッド
	//あとでスローしてるやつを変える

	//	//メニュー情報取得処理
	public static Menu getOneMenu(int menuID,int typeID) throws IdealException {
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		Menu menu = null;

		try{
			ic = new InitialContext();
			ds = (DataSource)ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();

			if(typeID == 100){ //コースの場合
				sql = "SELECT * FROM course NATURAL JOIN menutype WHERE c_id = ?";
			}else{ //メニューの場合
				sql = "SELECT * FROM menu NATURAL JOIN menutype WHERE m_id = ?";
			}
			pst = con.prepareStatement(sql);
			pst.setInt(1, menuID);
			rs = pst.executeQuery();

			while(rs.next()){
				menu = new Menu();
				if(typeID == 100){ //コースの場合
					menu.setMenuId(rs.getInt("c_id"));
					menu.setMenuName(rs.getString("c_Name"));
				}else{ //メニューの場合
					menu.setMenuId(rs.getInt("m_id"));
					menu.setMenuName(rs.getString("m_Name"));
				}
				menu.setDetail(rs.getString("detail"));
				if(menu.getDetail() == null){
					menu.setDetail("");
				}
				menu.setOrderFlg(rs.getInt("orderFlg"));
				menu.setPrice(rs.getInt("price"));
				menu.setTypeId(rs.getInt("t_id"));
				menu.setTypeName(rs.getString("t_name"));
			}
		}catch(SQLException | NamingException e) {
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			//				System.out.println(msg);
		}finally{
			try{
				if(rs != null) rs.close();
				if(pst != null) pst.close();
				if(con != null) con.close();
			}
			catch(Exception e){
//				e.printStackTrace();
			}
		}
		return menu;
	}



	//メニュー情報一覧取得処理
	public static ArrayList<Menu> getMenuList() throws  IdealException{
		ArrayList<Menu> alm = new ArrayList<Menu>();
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		Menu menu = null;

		try{
			ic = new InitialContext();
			ds = (DataSource)ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();

			sql = "SELECT * FROM menu NATURAL JOIN menutype WHERE orderFlg = '1' ORDER BY t_id,m_id";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			while(rs.next()){
				menu = new Menu();
				menu.setMenuId(rs.getInt("m_id"));
				menu.setMenuName(rs.getString("m_Name"));
				menu.setDetail(rs.getString("detail"));
				if(menu.getDetail() == null){
					menu.setDetail("");
				}
				menu.setOrderFlg(rs.getInt("orderFlg"));
				menu.setPrice(rs.getInt("price"));
				menu.setTypeId(rs.getInt("t_id"));
				menu.setTypeName(rs.getString("t_name"));
				alm.add(menu);
			}
//			Menu menu1 = alm.get(0);
//			System.out.println(menu1.getDetail());
		}catch(SQLException | NamingException e) {
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			//				System.out.println(msg);
		}finally{
			try{
				if(rs != null) rs.close();
				if(pst != null) pst.close();
				if(con != null) con.close();
			}
			catch(Exception e){
			}
		}
		return alm;
	}


	//分類別メニュー情報一覧取得処理
	public static ArrayList<Menu> getMenu(int typeID) throws  IdealException{

		ArrayList<Menu> alm = new ArrayList<Menu>();
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		Menu menu = null;

		try{
			ic = new InitialContext();
			ds = (DataSource)ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();

			if(typeID == 100){ //コースの場合
				sql = "SELECT * FROM course NATURAL JOIN menutype WHERE t_id = " + typeID
						+ " ORDER BY c_id";
			}else{ //メニューの場合
				sql = "SELECT * FROM menu NATURAL JOIN menutype WHERE t_id = " + typeID
						+ " ORDER BY m_id";
			}
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();


			while(rs.next()){
				menu = new Menu();

				if(typeID == 100){ //コースの場合
					menu.setMenuId(rs.getInt("c_id"));
					menu.setMenuName(rs.getString("c_Name"));
				}else{ //メニューの場合
					menu.setMenuId(rs.getInt("m_id"));
					menu.setMenuName(rs.getString("m_Name"));
				}
				menu.setDetail(rs.getString("detail"));
				if(menu.getDetail() == null){
					menu.setDetail("");
				}
				menu.setOrderFlg(rs.getInt("orderFlg"));
				menu.setPrice(rs.getInt("price"));
				menu.setTypeId(rs.getInt("t_id"));
				menu.setTypeName(rs.getString("t_name"));
				alm.add(menu);
			}
		}catch(SQLException | NamingException e) {
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			//				System.out.println(msg);
		}finally{
			try{
				if(rs != null) rs.close();
				if(pst != null) pst.close();
				if(con != null) con.close();
			}
			catch(Exception e){
			}
		}
		return alm;
	}


	//
	//		//メニュー情報更新処理
		public static int updateMenu(Menu m,int mode) throws  IdealException{

			InitialContext ic = null;
			DataSource ds = null;
			Connection con = null;
			PreparedStatement pst = null;
			String sql = null;
			String table = "menu";
			String id = "m_id";
			String name = "m_name";

			try{
				ic = new InitialContext();
				ds = (DataSource)ic.lookup("java:comp/env/mysql");
				con = ds.getConnection();

				//コースの場合、↓でテーブル名とかを変える
				//・・・んだけど、仕様的にコースだったらこのメソッド使わないから不要のはず
				if(m.getTypeId() == 100){
					table = "course";
					id = "c_id";
					name = "c_name";
				}

				switch(mode) {

				//登録処理
				case MenuOperationSvl.INSERT:
					sql = "INSERT INTO " + table + " VALUES(DEFAULT,?,?,?,?,?)";
					pst = con.prepareStatement(sql);
					pst.setString(1, m.getMenuName());
					pst.setString(2, m.getDetail());
					pst.setInt(3, m.getOrderFlg());
					pst.setInt(4, m.getPrice());
					pst.setInt(5, m.getTypeId());
					break;

					//変更処理
				case MenuOperationSvl.UPDATE:
					sql ="UPDATE " + table + " SET " + name + " = ?, detail = ?, orderFlg = ?, price = ?, t_id = ? "
							+ "WHERE " + id + " = ?";
					pst = con.prepareStatement(sql);
					pst.setString(1, m.getMenuName());
					pst.setString(2, m.getDetail());
					pst.setInt(3, m.getOrderFlg());
					pst.setInt(4, m.getPrice());
					pst.setInt(5, m.getTypeId());
					pst.setInt(6, m.getMenuId());
					break;

					//抹消処理
				case MenuOperationSvl.DELETE:
					sql = "DELETE FROM " + table + " WHERE " + id + " = ?";
					pst = con.prepareStatement(sql);
					pst.setInt(1, m.getMenuId());
					break;
				}
				pst.executeUpdate();

}catch(SQLException | NamingException e) {
	throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
	//				System.out.println(msg);
}finally{
				try{
					if(pst != null) pst.close();
					if(con != null) con.close();
				}
				catch(Exception e){
				}
			}

			//おそらくTypeIDを返す？

			return m.getTypeId();
		}

}
