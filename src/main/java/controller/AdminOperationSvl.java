package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Admin;
import model.IdealException;

/**
 * Servlet implementation class AdminOperationSvl
 */
@WebServlet("/AdminOperationSvl")
public class AdminOperationSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminOperationSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		System.out.println("サーブレット開始");
		System.out.println(request.getParameter("admId"));
		//リクエストパラメータ"mode"を受け取る
		String mode = request.getParameter("mode");
		System.out.println("modeは"+mode);

		//セッション情報を取得する ※この時点ではセッションはNULL
				//「セッション=NULL」かつ「登録処理以外」の場合はホームページへ戻る
				HttpSession adminInfo = request.getSession(true);
				if((adminInfo.getId() == null) & (!mode.equals("登録処理")) ){
					request.getRequestDispatcher("home.jsp").forward(request, response);
				}
		//各処理の為に入力されたパラメータを受け取る
		int admId = Integer.parseInt(request.getParameter("admId"));
		String admName = request.getParameter("admName");
		String password = request.getParameter("password");
		String exp = request.getParameter("exp");
		Admin adm;


		//modeにより処理を行う*************************************************
		switch(mode){

			case "登録処理":
				//パラメータをセットしてinsertメソッドに渡す
				adm = new Admin();
				adm.setAdmName(admName);
				adm.setPassword(password);
				adm.setExp(exp);

				try {
					//insertメソッドからの戻り値をリクエストに載せてフォアード
					adm = Admin.insert(adm);
					request.setAttribute("insertAdmInfo", adm);
					RequestDispatcher rd = request.getRequestDispatcher("/adminInsertCompletion.jsp");
					rd.forward(request, response);

				}catch(IdealException e) {
					String msg = ((IdealException) e).getMsg();
//					System.out.println(msg);
					request.setAttribute("msg", msg);
					RequestDispatcher rd = request.getRequestDispatcher("/adminInsert.jsp");
					rd.forward(request, response);
				}
				break;


			case "変更処理":
				//パラメータをセットしてupdateメソッドに渡す
				adm = new Admin();
				adm.setAdmId(admId);
				adm.setAdmName(admName);
				adm.setPassword(password);
				adm.setExp(exp);

				try {
					//updateメソッドからの戻り値をadminInfoセッションに載せてフォアード
					adm = Admin.update(adm); //ケースによっては戻り値不要の可能性あり
//					adminInfo.setAttribute("admId", adm.getAdmId());
//					adminInfo.setAttribute("admName", adm.getAdmName());
//					adminInfo.setAttribute("password", adm.getPassword());
//					adminInfo.setAttribute("admExp", adm.getExp());
					String msg = "管理者情報を変更しました";
					request.setAttribute("msg", msg);
					RequestDispatcher rd = request.getRequestDispatcher("/adminMaintenance.jsp");
					rd.forward(request, response);

				}catch(IdealException e) {
					String msg = ((IdealException) e).getMsg();
//					System.out.println(msg);
					request.setAttribute("msg", msg);
					RequestDispatcher rd = request.getRequestDispatcher("/adminUpdate.jsp");
					rd.forward(request, response);
				}
				break;


//			case "削除処理":
//				try {
//					System.out.println("削除メソッド開始前");
//
//					//deleteメソッドを実行する
//					User.delete((int)userInfo.getAttribute("usrId"));
//
//					System.out.println("削除メソッド終了");
//
//					//セッション情報を破棄する
//					userInfo.removeAttribute("usrId");
//					userInfo.removeAttribute("usrName");
//					userInfo.removeAttribute("password");
//					userInfo.removeAttribute("address");
//					userInfo.removeAttribute("phone");
//					userInfo.removeAttribute("mail");
//					userInfo.removeAttribute("exp");
//
//					System.out.println("セッション情報を破棄");
//
//					//ホーム画面に戻る
//					request.getRequestDispatcher("home.jsp").forward(request, response);
//
//				} catch (Exception e) {
//					String msg = ((IdealException) e).getMsg();
//					request.setAttribute("msg", msg);
//
//					//顧客情報削除画面表示処理に戻る
//					request.getRequestDispatcher("userDelete.jsp").forward(request, response);
//				}
//				break;
//
		}

	}

}
