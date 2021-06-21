package model;

public class IdealException extends Exception {
	//フィールド
	private int errCd;
	private static String[] ERR_MSG = {
			"障害が発生しました。",
			"データベース処理で例外が発生しました。",
			"お客様ID、パスワードを確認してください。",
			"管理者名、パスワードを確認してください。",
			"ご指定された日時に、空席がございませんでした。",
			"予約されているコースなので削除できません。",
			"コースに登録されているメニューなので削除できません。",
			"セッションが切れました。再度ログインしてください。"
	};

	public static int ERR_NO_EXCEPTION = 0;
	public static int ERR_NO_DB_EXCEPTION = 1;
	public static int ERR_NO_NOT_MEMBER_EXCEPTION = 2;
	public static int ERR_NO_ADMIN_EXCEPTION = 3;
	public static int ERR_NO_NOT_VACANCY = 4;
	public static int ERR_NO_NOT_RESERV_DELETE = 5;
	public static int ERR_NO_NOT_MENU_DELETE = 6;
	public static int ERR_NO_NOT_SESSION = 7;


	//コンストラクター
	public IdealException(int errCd){
		this.errCd = errCd;
	}

	public String getMsg() {
		String msg = ERR_MSG[errCd];
		return msg;
	}
}
