package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class User {

	private int usrId;
	private String usrName;
	private String password;
	private String address;
	private String phone;
	private String mail;
	private String exp;

	/*****************************************************/

	public int getUsrId() {
		return usrId;
	}
	public void setUsrId(int usrId) {
		this.usrId = usrId;
	}
	public String getUsrName() {
		return usrName;
	}
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}

	/***************ログイン************************************************************/

	public User login(int usrId, String password) throws IdealException  {
//		System.out.println("beans起動");
		int id = usrId;
		String pass = password;
		User a = null;
//		System.out.println("引数は"+ id +"と"+pass);


		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			a = new User();
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			st = con.createStatement();
			//del_flgが１以外を選択
			String sql = "SELECT * FROM user"
					+ " WHERE usr_id = '"+ id +"'"
							+ " AND password = '"+ pass +"'"
							+ " AND del_flg != 1";
//			System.out.println(sql);
			rs = st.executeQuery(sql);
//			System.out.println(rs);

			while(rs.next()){
				a.setUsrId(rs.getInt("usr_id"));
				a.setUsrName(rs.getString("usr_name"));
				a.setPassword(rs.getString("password"));
				a.setAddress(rs.getString("address"));
				a.setPhone(rs.getString("phone"));
				a.setMail(rs.getString("mail"));
				a.setExp(rs.getString("exp"));
			}
//			System.out.println("IDは"+a.getUsrId());
			if(a.getUsrId() == 0){
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

	/***************ユーザー情報取得************************************************************/
	public static User getUser(int usrId)throws IdealException  {
//		System.out.println("beans起動");
		int id = usrId;
		User a = null;

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			a = new User();
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			st = con.createStatement();
			//del_flgが1以外をセレクト
			String sql = "SELECT * FROM user"
					+ " WHERE usr_id = '"+ id +"'"
					+ "AND del_flg != 1";
//			System.out.println(sql);
			rs = st.executeQuery(sql);
			System.out.println(rs);

			while(rs.next()){
				a.setUsrId(rs.getInt("usr_id"));
				a.setUsrName(rs.getString("usr_name"));
				a.setPassword(rs.getString("password"));
				a.setAddress(rs.getString("address"));
				a.setPhone(rs.getString("phone"));
				a.setMail(rs.getString("mail"));
				a.setExp(rs.getString("exp"));
			}
			System.out.println("Userインスタンスに値セット完了");
//			System.out.println("名前は"+a.getUsrId());
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


	/***************新規登録************************************************************/
	public static User insert(User user) throws NamingException, IdealException{

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
			sql = "INSERT INTO user(usr_name, password, address, phone, mail) VALUES(?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1,user.usrName);
			pst.setString(2,user.password);
			pst.setString(3,user.address);
			pst.setString(4,user.phone);
			pst.setString(5,user.mail);
			pst.executeUpdate();

			//顧客IDを取得する
			sql = "select last_insert_id() as userInfo";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			rs.next();

			//顧客IDをセットする
			user.setUsrId(rs.getInt("userInfo"));

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
		return user;

	}


	/**************登録変更**************************************************/
	public static User update(User user) throws NamingException, IdealException{

		InitialContext ic = null;
		DataSource ds =null;
		Connection con = null;
		String sql = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try{
			ic = new InitialContext();
			ds = (DataSource)ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();

			sql = "UPDATE user SET usr_name = ? ,password = ?, address = ?, phone = ?, mail = ? WHERE usr_id = "
								+ user.getUsrId();
			pst = con.prepareStatement(sql);
			pst.setString(1,user.usrName);
			pst.setString(2,user.password);
			pst.setString(3,user.address);
			pst.setString(4,user.phone);
			pst.setString(5,user.mail);
			pst.executeUpdate();

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
		return user;

	}


	/***************登録削除************************************************************/
	public static boolean delete(int usrId) throws NamingException, IdealException{
		/*
		 * 仕様変更
		 * ①戻り値の型をvoidからbooleanに変更
		 * ②true:削除フラグを立てる false:未来に予約が残っているので削除フラグを設定しない
		 * ③処理手順
		 * 　・usrIdで本日以後の予約があるかをチェック
		 * 　・予約があればfalseを返す
		 * 　・予約がなければdel_flgに１とdel_dateにCURRENT_TIMESTAMP()をアップデート
		 * 　　trueを返す
		 */

		InitialContext ic = null;
		DataSource ds =null;
		Connection con = null;
		ResultSet rs = null;
		String sql = null;
		PreparedStatement pst = null;
		boolean bool;

		try{
			//DBに接続する
			ic = new InitialContext();
			ds = (DataSource)ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();

			con.setAutoCommit(false);

			//引数のusrIdで本日以後の予約を参照する
			sql = "select * from reserve where usr_id = "+usrId
					+" and rsv_date >= current_date()";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			//参照したテーブルに本日以後に予約があればfalseを返す
			if(rs.next()) {
				System.out.println("本日以後に予約があるよ");
				System.out.println("false");
				bool = false;
			}else {
				System.out.println("本日以後に予約がないよ");
				System.out.println("true");
				System.out.println("SQL②実行前:" + usrId);
				//顧客テーブルにフラグ１（削除）をセットするする
				sql = "UPDATE user SET del_flg = 1 , del_date = CURRENT_TIMESTAMP() WHERE usr_id = " + usrId;
				pst = con.prepareStatement(sql);
				pst.executeUpdate();
				System.out.println("削除フラグをセットしました。");
				bool =  true;
			}


			con.commit();


		}catch(SQLException e){
			try {
				System.out.println("DBエラーが発生したのでロールバックします");
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			int i = IdealException.ERR_NO_DB_EXCEPTION;
			throw new IdealException(i);
		}finally{
			try {
				pst.close();
				con.close();
				ic.close();
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return bool;

	}




}
