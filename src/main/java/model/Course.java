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

import controller.CourseOperationSvl;

public class Course {
	//フィールド
	private int courseId;
	private String courseName;
	private String detail;
	private int orderFlg;
	private int price;
	private int typeId;
	private String typeName;
	private int menuId;
	private String menuName;

	public static int[] COURSE_MENU_TYPE_ID ={
		200,210,220,
		300,310,
		400
	};

	//getter
	public int getCourseId() {
		return courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getDetail() {
		return detail;
	}

	public int getOrderFlg() {
		return orderFlg;
	}

	public int getPrice() {
		return price;
	}

	public int getTypeId() {
		return typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public int getMenuId() {
		return menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public static int[] getCOURSE_MENU_TYPE_ID() {
		return COURSE_MENU_TYPE_ID;
	}

	//setter
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public void setOrderFlg(int orderFlg) {
		this.orderFlg = orderFlg;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public static void setCOURSE_MENU_TYPE_ID(int[] cOURSE_MENU_TYPE_ID) {
		COURSE_MENU_TYPE_ID = cOURSE_MENU_TYPE_ID;
	}

	//メソッド
	public static Course getCourse(int c_id) throws IdealException{
		//コース情報取得処理（構成メニューを含まない）
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Course cou = new Course();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			st = con.createStatement();
			String sql = "SELECT * FROM course"
					+ " WHERE c_id = "+c_id;
//			System.out.println(sql);
			rs = st.executeQuery(sql);

			while(rs.next()){
				cou.setCourseId(rs.getInt("c_id"));
				cou.setCourseName(rs.getString("c_name"));
				cou.setDetail(rs.getString("detail"));
				cou.setOrderFlg(rs.getInt("orderFlg"));
				cou.setPrice(rs.getInt("price"));
				cou.setTypeId(rs.getInt("t_id"));
			}
//			System.out.println(cou.getCourseId());
//			System.out.println(cou.getCourseName());
//			System.out.println(cou.getDetail());
//			System.out.println(cou.getOrderFlg());
//			System.out.println(cou.getPrice());
//			System.out.println(cou.getTypeId());
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
		return cou;
	}

	public static ArrayList<Course> getOneCourse(int c_id) throws IdealException{
	//コース情報取得処理（構成メニューを含む）
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;

	ArrayList<Course> al = new ArrayList<Course>();
	try{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		st = con.createStatement();
		String sql ="SELECT * FROM course"
				+ " inner join coursectl using(c_id)"
				+ " inner join menu using(m_id)"
				+ " where c_id = "+c_id;
//		System.out.println(sql);
		rs = st.executeQuery(sql)  ;

		while(rs.next()){
			Course cou = new Course();
			cou.setCourseId(rs.getInt("c_id"));
			cou.setCourseName(rs.getString("c_name"));
			cou.setDetail(rs.getString("detail"));
			cou.setOrderFlg(rs.getInt("orderFlg"));
			cou.setPrice(rs.getInt("price"));
			cou.setTypeId(rs.getInt("menu.t_id"));
			cou.setMenuId(rs.getInt("m_id"));
			cou.setMenuName(rs.getString("m_name"));

			al.add(cou);
		}



//		System.out.println(cou.getCourseId());
//		System.out.println(cou.getCourseName());
//		System.out.println(cou.getDetail());
//		System.out.println(cou.getOrderFlg());
//		System.out.println(cou.getPrice());
//		System.out.println(cou.getTypeId());
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
	return al;

	}
	public static ArrayList<Course> getCourseList() throws IdealException{
		//コース情報とコースの構成メニューをArryList<Course>で渡す
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		ArrayList<Course> al = new ArrayList<Course>();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			st = con.createStatement();
			String sql ="SELECT * FROM course"
					+ " inner join coursectl using(c_id)"
					+ " inner join menu using(m_id)";

//			System.out.println(sql);
			rs = st.executeQuery(sql)  ;

			while(rs.next()){
				Course cou = new Course();
				cou.setCourseId(rs.getInt("c_id"));
				cou.setCourseName(rs.getString("c_name"));
				cou.setDetail(rs.getString("detail"));
				cou.setOrderFlg(rs.getInt("orderFlg"));
				cou.setPrice(rs.getInt("price"));
				cou.setTypeId(rs.getInt("t_id"));
				cou.setMenuId(rs.getInt("m_id"));
				cou.setMenuName(rs.getString("m_name"));

				al.add(cou);
			}



//			System.out.println(cou.getCourseId());
//			System.out.println(cou.getCourseName());
//			System.out.println(cou.getDetail());
//			System.out.println(cou.getOrderFlg());
//			System.out.println(cou.getPrice());
//			System.out.println(cou.getTypeId());
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
		return al;



	}
	public static ArrayList<Course> getOneCourseList() throws IdealException{
		//オーダー可能なコース情報を取得
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Course> al = new ArrayList<Course>();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			st = con.createStatement();
			String sql = "SELECT * FROM course"
					+ " WHERE orderFlg = 1";
//			System.out.println(sql);
			rs = st.executeQuery(sql)  ;

			while(rs.next()){
				Course cou = new Course();
				cou.setCourseId(rs.getInt("c_id"));
				cou.setCourseName(rs.getString("c_name"));
				cou.setDetail(rs.getString("detail"));
				cou.setOrderFlg(rs.getInt("orderFlg"));
				cou.setPrice(rs.getInt("price"));
				cou.setTypeId(rs.getInt("t_id"));
				al.add(cou);
			}
//			System.out.println(cou.getCourseId());
//			System.out.println(cou.getCourseName());
//			System.out.println(cou.getDetail());
//			System.out.println(cou.getOrderFlg());
//			System.out.println(cou.getPrice());
//			System.out.println(cou.getTypeId());
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
		return al;
	}


	/*    完成できず　　　　*/
//	public static ArrayList<Course> getTypeCourseList(int t_id) throws IdealException{
//		//分類別コース情報一覧取得処理
//
//		Connection con = null;
//		Statement st = null;
//		ResultSet rs = null;
//		ArrayList<Course> al = new ArrayList<Course>();
//		try{
//			Class.forName("com.mysql.jdbc.Driver");
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
//			st = con.createStatement();
//			String sql = "SELECT * FROM course"
//					+ " WHERE orderFlg = 1";
//			System.out.println(sql);
//			rs = st.executeQuery(sql)  ;
//
//			while(rs.next()){
//				Course cou = new Course();
//				cou.setCourseId(rs.getInt("c_id"));
//				cou.setCourseName(rs.getString("c_name"));
//				cou.setDetail(rs.getString("detail"));
//				cou.setOrderFlg(rs.getInt("orderFlg"));
//				cou.setPrice(rs.getInt("price"));
//				cou.setTypeId(rs.getInt("t_id"));
//				al.add(cou);
//			}
////			System.out.println(cou.getCourseId());
////			System.out.println(cou.getCourseName());
////			System.out.println(cou.getDetail());
////			System.out.println(cou.getOrderFlg());
////			System.out.println(cou.getPrice());
////			System.out.println(cou.getTypeId());
//		}catch(SQLException | ClassNotFoundException e) {
//			int i = IdealException.ERR_NO_DB_EXCEPTION;
//			throw new IdealException(i);
//
//		}finally {
//			try {
//				if(con != null) con.close();
//				if(st != null) st.close();
//				if(rs != null) rs.close();
//			}catch(Exception e) {
//
//			}
//		}
//		return al;
//	}


	public static int updateCourse(Course c,int mode,ArrayList<CourseCtl> coursectl) throws IdealException{

				InitialContext ic = null;
				DataSource ds = null;
				Connection con = null;
				PreparedStatement pst1 = null;
				PreparedStatement pst2 = null;
				PreparedStatement pst3 = null;
				String sql = null;
				ResultSet rs = null;

				try{
					ic = new InitialContext();
					ds = (DataSource)ic.lookup("java:comp/env/mysql");
					con = ds.getConnection();

					switch(mode) {

					//登録処理
					case CourseOperationSvl.INSERT:
						//courseテーブルから更新する
						sql ="INSERT INTO course VALUES(DEFAULT,?,?,?,?,?)";
						pst1 = con.prepareStatement(sql);
						pst1.setString(1, c.getCourseName());
						pst1.setString(2, c.getDetail());
						pst1.setInt(3, c.getOrderFlg());
						pst1.setInt(4, c.getPrice());
						pst1.setInt(5, c.getTypeId());
						pst1.executeUpdate();

						//↑で追加したコースのc_idを取得
						//しかしUNIQUEキーがc_id以外になく、指定できないので仕方なく↓で
						sql = "SELECT LAST_INSERT_ID()";
						pst2 = con.prepareStatement(sql);
						rs = pst2.executeQuery();

						//次にcoursectlテーブルを更新する
						for(CourseCtl cc : coursectl) {
						sql ="INSERT INTO coursectl VALUES(?,?)";
						pst3 = con.prepareStatement(sql);
						pst3.setInt(1, rs.getInt("LAST_INSERT_ID()"));
						pst3.setInt(2, cc.getM_Id());
						pst3.executeUpdate();
						}
						break;

					//変更処理
					case CourseOperationSvl.UPDATE:
						//一旦coursectlテーブルから該当するcourseIDのを全て消す
						sql = "DELETE FROM coursectl WHERE c_id = " + c.getCourseId();
						pst1 = con.prepareStatement(sql);
						pst1.executeUpdate();

						//削除後、改めてcoursectlテーブルから更新する
						for(CourseCtl cc : coursectl) {
						sql ="INSERT INTO coursectl VALUES(?,?)";
						pst2 = con.prepareStatement(sql);
						pst2.setInt(1, cc.getC_Id());
						pst2.setInt(2, cc.getM_Id());
						pst2.executeUpdate();
						}

						//次にcourseテーブルを更新する
						sql ="UPDATE course SET c_name = ?, detail = ?, orderFlg = ?, price = ? "
								+ "WHERE c_id = ?";
						pst3 = con.prepareStatement(sql);
						pst3.setString(1, c.getCourseName());
						pst3.setString(2, c.getDetail());
						pst3.setInt(3, c.getOrderFlg());
						pst3.setInt(4, c.getPrice());
						pst3.setInt(5, c.getCourseId());
						pst3.executeUpdate();
						break;

					//抹消処理
					case CourseOperationSvl.DELETE:
//						con.setAutoCommit(false);

						//先にcoursectlテーブルから削除
						sql = "DELETE FROM coursectl WHERE c_id = " + c.getCourseId();
						pst1 = con.prepareStatement(sql);
						pst1.executeUpdate();

						//次にcourseテーブルから削除
						sql = "DELETE FROM course WHERE c_id = " + c.getCourseId();
						pst2 = con.prepareStatement(sql);
						pst2.executeUpdate();

//						con.rollback();
//						con.commit();
						break;
					}

				}catch(SQLException | NamingException e) {
					throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
					//				System.out.println(msg);
				}finally{
					try{
//						con.setAutoCommit(true);
						if(pst1 != null) pst1.close();
						if(pst2 != null) pst2.close();
						if(pst3 != null) pst3.close();
						if(con != null) con.close();
					}
					catch(Exception e){
					}
				}

				//おそらくTypeIDを返す？ でもどのパターンも100のはず・・・
				return c.getTypeId();
			}

}
