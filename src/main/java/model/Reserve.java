package model;  // 6/14 予約登録、変更、削除の不具合修正済み 再プッシュ

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class Reserve {

	private int rsvId;			 //予約ID
	private int usrId;			 //顧客ID
	private String usrName;		//顧客名
	private int rsvYy;			//予約_年
	private int rsvMm;			//予約_月
	private int rsvDd;			//予約_日
	private int rsvHh;			//予約_時
	private int rsvMi;			//予約_分
	private int person;			//人数
	private int tableId;		//テーブルID
	private String tableName;	//テーブル名
	private int courseId;		//コースID
	private String courseName;		//コース名
	private String appDate;		//登録日時
	private int appYy;			//登録_年
	private int appMm;			//登録_月
	private int appDd;			//登録_日
	private int appHh;			//登録_時
	private int appMi;			//登録_分

	//コンストラクター

	public Reserve(){
		super();
	}

	//getter および setter 自動生成

	public int getRsvId() {
		return rsvId;
	}
	public int getUsrId() {
		return usrId;
	}
	public String getUsrName() {
		return usrName;
	}
	public int getRsvYy() {
		return rsvYy;
	}
	public int getRsvMm() {
		return rsvMm;
	}
	public int getRsvDd() {
		return rsvDd;
	}
	public int getRsvHh() {
		return rsvHh;
	}
	public int getRsvMi() {
		return rsvMi;
	}
	public int getPerson() {
		return person;
	}
	public int getTableId() {
		return tableId;
	}
	public String getTableName() {
		return tableName;
	}
	public int getCourseId() {
		return courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public String getAppDate() {
		return appDate;
	}
	public int getAppYy() {
		return appYy;
	}
	public int getAppMm() {
		return appMm;
	}
	public int getAppDd() {
		return appDd;
	}
	public int getAppHh() {
		return appHh;
	}
	public int getAppMi() {
		return appMi;
	}
	public void setRsvId(int rsvId) {
		this.rsvId = rsvId;
	}
	public void setUsrId(int usrId) {
		this.usrId = usrId;
	}
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}
	public void setRsvYy(int rsvYy) {
		this.rsvYy = rsvYy;
	}
	public void setRsvMm(int rsvMm) {
		this.rsvMm = rsvMm;
	}
	public void setRsvDd(int rsvDd) {
		this.rsvDd = rsvDd;
	}
	public void setRsvHh(int rsvHh) {
		this.rsvHh = rsvHh;
	}
	public void setRsvMi(int rsvMi) {
		this.rsvMi = rsvMi;
	}
	public void setPerson(int person) {
		this.person = person;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}
	public void setAppYy(int appYy) {
		this.appYy = appYy;
	}
	public void setAppMm(int appMm) {
		this.appMm = appMm;
	}
	public void setAppDd(int appDd) {
		this.appDd = appDd;
	}
	public void setAppHh(int appHh) {
		this.appHh = appHh;
	}
	public void setAppMi(int appMi) {
		this.appMi = appMi;
	}

	//以下メソッド、
	//----予約情報一覧取得処理----

	public static ArrayList<Reserve> getReserveList(int usrId)throws IdealException{
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<Reserve> al = new ArrayList<Reserve>();

		try{
			ic = new InitialContext();
			ds = (DataSource)ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			sql = "SELECT * FROM reserve INNER JOIN user USING(usr_id )"
					+ " INNER JOIN table_loc USING(table_id) INNER JOIN course USING(c_id)  WHERE usr_id = ? "
					+ " ORDER BY rsv_date " ;
			pst = con.prepareStatement(sql);
			pst.setInt(1,usrId);
			rs = pst.executeQuery();

			while(rs.next()){
				Reserve r = new Reserve();
				String rsv = rs.getString("rsv_date");
				String app = rs.getString("app_date");
				r.setRsvId(rs.getInt("rsv_id"));
				r.setUsrId(rs.getInt("usr_id"));
				r.setUsrName(rs.getString("usr_name"));
				r.setRsvYy(Integer.parseInt(rsv.substring(0,4)));
				r.setRsvMm(Integer.parseInt(rsv.substring(5,7)));
				r.setRsvDd(Integer.parseInt(rsv.substring(8,10)));
				r.setRsvHh(Integer.parseInt(rsv.substring(11,13)));
				r.setRsvMi(Integer.parseInt(rsv.substring(14,16)));
				r.setPerson(rs.getInt("person"));
				r.setTableId(rs.getInt("table_id"));
				r.setTableName(rs.getString("table_name"));
				r.setCourseId(rs.getInt("c_id"));
				r.setCourseName(rs.getString("c_name"));
				r.setAppDate(rs.getString("app_date"));
				r.setAppYy(Integer.parseInt(app.substring(0,4)));
				r.setAppMm(Integer.parseInt(app.substring(5,7)));
				r.setAppDd(Integer.parseInt(app.substring(8,10)));
				r.setAppHh(Integer.parseInt(app.substring(11,13)));
				r.setAppMi(Integer.parseInt(app.substring(14,16)));
				al.add(r);

			}

		}catch( SQLException | NamingException  e) {
			int i = IdealException.ERR_NO_DB_EXCEPTION;
			throw new IdealException(i);
		}finally{
			try{
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				if (con != null) con.close();
			}catch(Exception e){

			}
		}

		return al;

	}


	//----予約情報取得処理----

	public static Reserve getReserve(int rsvId)throws IdealException{
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		Reserve re = new Reserve();

		try{
			ic = new InitialContext();
			ds = (DataSource)ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			sql = "SELECT * FROM reserve INNER JOIN user USING(usr_id )"
					+ " INNER JOIN table_loc USING(table_id) INNER JOIN course USING(c_id)  WHERE rsv_id = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1,rsvId);
			rs = pst.executeQuery();

			while(rs.next()){
				String rsv = rs.getString("rsv_date");
				String app = rs.getString("app_date");
				re.setRsvId(rs.getInt("rsv_id"));
				re.setUsrId(rs.getInt("usr_id"));
				re.setUsrName(rs.getString("usr_name"));
				re.setRsvYy(Integer.parseInt(rsv.substring(0,4)));
				re.setRsvMm(Integer.parseInt(rsv.substring(5,7)));
				re.setRsvDd(Integer.parseInt(rsv.substring(8,10)));
				re.setRsvHh(Integer.parseInt(rsv.substring(11,13)));
				re.setRsvMi(Integer.parseInt(rsv.substring(14,16)));
				re.setPerson(rs.getInt("person"));
				re.setTableId(rs.getInt("table_id"));
				re.setTableName(rs.getString("table_name"));
				re.setCourseId(rs.getInt("c_id"));
				re.setCourseName(rs.getString("c_name"));
				re.setAppDate(rs.getString("app_date"));
				re.setAppYy(Integer.parseInt(app.substring(0,4)));
				re.setAppMm(Integer.parseInt(app.substring(5,7)));
				re.setAppDd(Integer.parseInt(app.substring(8,10)));
				re.setAppHh(Integer.parseInt(app.substring(11,13)));
				re.setAppMi(Integer.parseInt(app.substring(14,16)));
			}

		}catch( SQLException | NamingException  e) {
			int i = IdealException.ERR_NO_DB_EXCEPTION;
			throw new IdealException(i);
		}finally{
			try{
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				if (con != null) con.close();

			}catch(Exception e){

			}

		}

		return re;

	}

	//----コース予約確認処理----

	public static void reservCourseChk(int c_id)throws IdealException{

		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;

		try{
			ic = new InitialContext();
			ds = (DataSource)ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			sql = " SELECT * FROM reserve  WHERE c_id = ?  and  rsv_date > CURRENT_DATE();" ;
			pst = con.prepareStatement(sql);
			pst.setInt(1,c_id);
			rs = pst.executeQuery();

			while(rs.next()){
				int i = IdealException.ERR_NO_NOT_RESERV_DELETE;
				throw new IdealException(i);

			}

		}catch( SQLException | NamingException  e) {
			int i = IdealException.ERR_NO_DB_EXCEPTION;
			throw new IdealException(i);
		}finally{
			try{
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				if (con != null) con.close();

			}catch(Exception e){

			}

		}

	}

	//----新規予約確認処理----

	public static TableLoc insertChk(String dateStr ,int personNum)throws IdealException{
		System.out.println("insertChk開始"); //バグチェック用
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		TableLoc tl = new TableLoc();
		int cnt = 0;

		try{
			System.out.println("insertChkのtryブロック開始"); //バグチェック用
			ic = new InitialContext();
			ds = (DataSource)ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();

		//SQL文を大幅に変更しました。
		//table_locとreserveを「table_idが一致かつ、予約しようとする時刻の前後3時間が既にある予約と被る」
		//という条件で外部結合すると、予約がある席のrsv_dateには予約時刻が、無い席のrsv_dateにはnullが入ります。
		//この中からmax_capacityがrsv_dateにnullが入っているものだけを取り出せば空席を取得できますので、
		//その中で一番table_idが最小なものから必要な情報を取得します。

			sql =  " SELECT table_loc.table_id,table_name,max_capacity,rsv_date FROM table_loc"
				   +" LEFT OUTER JOIN reserve ON table_loc.table_id = reserve.table_id"
				   +" AND (reserve.rsv_date > ? AND reserve.rsv_date < ?)"
				   +" WHERE max_capacity >= ? AND rsv_date IS null ORDER BY max_capacity ";

			pst = con.prepareStatement(sql);

			System.out.println("受け取っているpersonNum =" + personNum);//バグチェック
			System.out.println("受け取っているdatestr=" + dateStr);//バグチェック


		//String型のdateStrから文字列を抜き出し、Int型にして各変数に設定
		//dateStrが 「xxxx-xx-xx xx:xx」の形式だという前提で作成しています。動かなかったらチェック
			int year =Integer.parseInt(dateStr.substring(0,4));
			int month =Integer.parseInt(dateStr.substring(5,7));
			int day = Integer.parseInt(dateStr.substring(8,10));
			int time = Integer.parseInt(dateStr.substring(11,13));
			int minute = Integer.parseInt(dateStr.substring(14,16));

		//時刻部分に３時間足し引きしたものをString型で作成しspl文へ入れる

			pst.setString(1, year + "-" + month + "-" + day + " " + (time - 3) + ":" + minute);
			pst.setString(2, year + "-" + month + "-" + day + " " + (time + 3) + ":" + minute);
			pst.setInt(3,personNum);

			rs = pst.executeQuery();

			System.out.println("insertChのrs取得");//バグチェック

			if(rs.next()){
				tl.setTableId(rs.getInt("table_id"));
				System.out.println("rsよりtable_id=" + rs.getInt("table_id"));//バグチェック

				tl.setTableName(rs.getString("table_name"));
				System.out.println("rsよりtable_name=" + rs.getString("table_name"));//バグチェック

				tl.setMaxCapacity(rs.getInt("max_capacity"));
				System.out.println("rsよりmax_capacity=" + rs.getInt("max_capacity"));//バグチェック
				cnt++;
			}
			System.out.println("cnt =" + cnt);

		}catch( SQLException | NamingException  e) {
			System.out.println("insertChk内で例外発生");//バグチェック
			int i = IdealException.ERR_NO_DB_EXCEPTION;
			throw new IdealException(i);
		}finally{
			try{
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				if (con != null) con.close();

			}catch(Exception e){

			}
		}

		if(cnt<1){
			System.out.println("nullを返している");//バグチェック
			return null;
		}else{
			System.out.println("tlを返している");//バグチェック
			System.out.println("tlよりTableId=" + tl.getTableId());//バグチェック
			System.out.println("tlよりTableName=" +tl.getTableName());//バグチェック
			System.out.println("tlよりMaxCapacity=" +tl.getMaxCapacity());//バグチェック
			return tl;
		}

	}

	//----変更予約確認処理----

	public static TableLoc updateChk(int rsvId ,String dateStr ,int personNum)throws IdealException{
		System.out.println("updateChk開始"); //バグチェック用
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		TableLoc tl = new TableLoc();
		int cnt = 0;

		try{
			System.out.println("updateChkのtryブロック開始"); //バグチェック用
			ic = new InitialContext();
			ds = (DataSource)ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();

			//SQL文を大幅に変更しました。
			//table_locとreserveを「table_idが一致かつ、予約しようとする時刻の前後3時間が既にある予約と被る」
			//という条件で外部結合すると、予約がある席のrsv_dateには予約時刻が、無い席のrsv_dateにはnullが入ります。
			//この中からmax_capacityがrsv_dateにnullが入っているものだけを取り出せば空席を取得できますので、
			//その中で一番table_idが最小なものから必要な情報を取得します。

			sql =  " SELECT table_loc.table_id,table_name,max_capacity,rsv_date FROM table_loc"
				   +" LEFT OUTER JOIN reserve ON table_loc.table_id = reserve.table_id"
				   +" AND (reserve.rsv_date > ? AND reserve.rsv_date < ?)"
				   +" WHERE max_capacity >= ?  AND ( rsv_date IS null or rsv_id = ? ) ORDER BY max_capacity ";
;

			pst = con.prepareStatement(sql);

			System.out.println("受け取っているrsvId =" + rsvId);//バグチェック
			System.out.println("受け取っているpersonNum =" + personNum);//バグチェック
			System.out.println("受け取っているdatestr=" + dateStr);//バグチェック


			pst.setInt(3,personNum);
			pst.setInt(4,rsvId);

			//String型のdateStrから文字列を抜き出し、Int型にして各変数に設定
			//dateStrが 「xxxx-xx-xx xx:xx」の形式だという前提で作成しています。動かなかったらチェック
			int year =Integer.parseInt(dateStr.substring(0,4));
			int month =Integer.parseInt(dateStr.substring(5,7));
			int day = Integer.parseInt(dateStr.substring(8,10));
			int time = Integer.parseInt(dateStr.substring(11,13));
			int minute = Integer.parseInt(dateStr.substring(14,16));

			//時刻部分に３時間足し引きしたものをString型で作成しsql文へ入れる

			pst.setString(1, year + "-" + month + "-" + day + " " + (time - 3) + ":" + minute);
			pst.setString(2, year + "-" + month + "-" + day + " " + (time + 3) + ":" + minute);

			rs = pst.executeQuery();
			System.out.println("updateChkのrs取得");//バグチェック


			if(rs.next()){
				tl.setTableId(rs.getInt("table_id"));
				System.out.println("rsよりtable_id=" + rs.getInt("table_id"));//バグチェック

				tl.setTableName(rs.getString("table_name"));
				System.out.println("rsよりtable_name=" + rs.getString("table_name"));//バグチェック

				tl.setMaxCapacity(rs.getInt("max_capacity"));
				System.out.println("rsよりmax_capacity=" + rs.getInt("max_capacity"));//バグチェック
				cnt++;



				cnt++;
			}


		}catch( SQLException | NamingException  e) {
			System.out.println("updateChk内で例外発生");//バグチェック
			int i = IdealException.ERR_NO_DB_EXCEPTION;
			throw new IdealException(i);
		}finally{
			try{
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				if (con != null) con.close();

			}catch(Exception e){

			}

		}

		if(cnt<1){
			System.out.println("nullを返している");//バグチェック
			return null;
		}else{
			System.out.println("tlを返している");//バグチェック
			System.out.println("tlよりTableId=" + tl.getTableId());//バグチェック
			System.out.println("tlよりTableName=" +tl.getTableName());//バグチェック
			System.out.println("tlよりMaxCapacity=" +tl.getMaxCapacity());//バグチェック
			return tl;
		}

	}

	//----予約情報登録処理----

	public static Reserve insert(Reserve reserve)throws IdealException{
		System.out.println("insert開始"); //バグチェック用
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst1 = null;
		PreparedStatement pst2 = null;
		ResultSet rs = null;
		String sql = null;
		Reserve re1 = reserve;
		Reserve re2 = new Reserve();

		try{
			System.out.println("insertのtryブロック開始"); //バグチェック用
			ic = new InitialContext();
			ds = (DataSource)ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			//pst1でデータベースへ登録
			sql = "insert into reserve (usr_id,rsv_date,person,table_id,c_id) values (?,?,?,?,?)";
			pst1 = con.prepareStatement(sql);

			System.out.println(re1.getUsrId());
			pst1.setInt(1,re1.getUsrId());
			//予約時刻はre1からそれぞれの情報を呼び出し、String型の文字列を構成してから渡す

			System.out.println(re1.getRsvYy() + "-" + re1.getRsvMm() + "-" + re1.getRsvDd() + " " + re1.getRsvHh() + ":" + re1.getRsvMi());
			pst1.setString(2, re1.getRsvYy() + "-" + re1.getRsvMm() + "-" + re1.getRsvDd() + " " + re1.getRsvHh() + ":" + re1.getRsvMi());
			System.out.println(re1.getPerson());
			pst1.setInt(3,re1.getPerson());
			System.out.println(re1.getTableId());
			pst1.setInt(4,re1.getTableId());


			System.out.println(re1.getCourseId());
			pst1.setInt(5,re1.getCourseId());

			pst1.executeUpdate();

			//従来はここにLAST_INSERT_ID()を求める旨の記述がありましたが、下記のようにすれば問題ないので
			//削除しました。削除に合わせて変数名等もつけなおしています。


			sql = " SELECT * FROM reserve WHERE rsv_id = LAST_INSERT_ID() ";

			pst2 = con.prepareStatement(sql);
			rs = pst2.executeQuery();

			System.out.println("結果セット取得成功"); //バグチェック用

			//この先未確認


			while(rs.next()){
				String rsv = rs.getString("rsv_date");
				re2.setRsvId(rs.getInt("rsv_id"));
				re2.setUsrId(rs.getInt("usr_id"));
				re2.setRsvYy(Integer.parseInt(rsv.substring(0,4)));
				re2.setRsvMm(Integer.parseInt(rsv.substring(5,7)));
				re2.setRsvDd(Integer.parseInt(rsv.substring(8,10)));
				re2.setRsvHh(Integer.parseInt(rsv.substring(11,13)));
				re2.setRsvMi(Integer.parseInt(rsv.substring(14,16)));
				re2.setPerson(rs.getInt("person"));
				re2.setCourseId(rs.getInt("c_id"));

			}


		}catch( SQLException | NamingException  e) {
			System.out.println("insert内で例外発生"); //バグチェック用
			int i = IdealException.ERR_NO_DB_EXCEPTION;
			throw new IdealException(i);
		}finally{
			try{
				if (rs != null) rs.close();
				if (pst1 != null) pst1.close();
				if (pst2 != null) pst2.close();
				if (con != null) con.close();

			}catch(Exception e){

			}

		}
		System.out.println("insert終了"); //バグチェック用
		return re2;

	}

	//----予約情報変更処理----

	public static Reserve update(Reserve  reserve)throws IdealException{

		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst1 = null;
		PreparedStatement pst2= null;
		ResultSet rs = null;
		String sql = null;
		Reserve re1 = reserve;
		Reserve re2 = new Reserve();


		try{
			ic = new InitialContext();
			ds = (DataSource)ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			//pst1で登録内容を更新する。
			sql = "UPDATE reserve SET usr_id = ?,rsv_date = ? , person = ? , c_id = ? , table_id = ? WHERE rsv_id = ? ";
			pst1 = con.prepareStatement(sql);
			pst1.setInt(1,re1.getUsrId());
			//予約時刻はre1からそれぞれの情報を呼び出し、String型の文字列を構成してから渡す
			pst1.setString(2, re1.getRsvYy() + "-" + re1.getRsvMm() + "-" + re1.getRsvDd() + " " + re1.getRsvHh() + ":" + re1.getRsvMi());
			pst1.setInt(3,re1.getPerson());
			pst1.setInt(4,re1.getCourseId());
			pst1.setInt(5,re1.getTableId());
			pst1.setInt(6,re1.getRsvId());
			pst1.executeUpdate();

			System.out.println("updateメソッド:テーブルの値を更新");

			//pst2で変更後の内容を問合せ
			sql = " SELECT * FROM reserve WHERE rsv_id = ? ";
			pst2 = con.prepareStatement(sql);
			pst2.setInt(1,re1.getRsvId());
			rs = pst2.executeQuery();

			System.out.println("updateメソッド:更新後の結果セット取得");


			while(rs.next()){
				String rsv = rs.getString("rsv_date");
				re2.setRsvId(rs.getInt("rsv_id"));
				re2.setUsrId(rs.getInt("usr_id"));
				re2.setRsvYy(Integer.parseInt(rsv.substring(0,4)));
				re2.setRsvMm(Integer.parseInt(rsv.substring(5,7)));
				re2.setRsvDd(Integer.parseInt(rsv.substring(8,10)));
				re2.setRsvHh(Integer.parseInt(rsv.substring(11,13)));
				re2.setRsvMi(Integer.parseInt(rsv.substring(14,16)));
				re2.setPerson(rs.getInt("person"));
				re2.setTableId(rs.getInt("table_id"));
				re2.setCourseId(rs.getInt("c_id"));
			}

		}catch( SQLException | NamingException  e) {
			System.out.println("updateメソッド内で例外発生");
			int i = IdealException.ERR_NO_DB_EXCEPTION;
			throw new IdealException(i);

		}finally{
			try{
				if (rs != null) rs.close();
				if (pst1 != null) pst1.close();
				if (pst2 != null) pst2.close();
				if (con != null) con.close();

			}catch(Exception e){

			}
		}
		System.out.println("updateメソッド終了");
		return re2;

	}

	//----予約情報削除処理----

	public static void delete(Reserve  reserve)throws IdealException{

		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		Reserve re = reserve;

		try{
			ic = new InitialContext();
			ds = (DataSource)ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			sql = " DELETE  FROM reserve WHERE rsv_id = ? ";
			pst = con.prepareStatement(sql);
			pst.setInt(1,re.getRsvId());
			pst.executeUpdate();

		}catch( SQLException | NamingException  e) {
			int i = IdealException.ERR_NO_DB_EXCEPTION;
			throw new IdealException(i);
		}finally{
			try{
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				if (con != null) con.close();

			}catch(Exception e){

			}
		}
	}
}
