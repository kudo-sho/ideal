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
		int admId;
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
				admId = Integer.parseInt(request.getParameter("admId"));
				adm.setAdmId(admId);
				adm.setAdmName(admName);
				adm.setPassword(password);
				adm.setExp(exp);

				try {
					//updateメソッドからの戻り値をadminInfoセッションに載せてフォアード？
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


			case "削除処理":
				try {
					//confPassの比較
					password = (String)adminInfo.getAttribute("password");
					String confPass = request.getParameter("confPass");

					if(password.equals(confPass)){//PW一致したら削除開始
						System.out.println("削除メソッド開始前");

						//deleteメソッドを実行する
						admId = Integer.parseInt(request.getParameter("admId"));
						Admin.delete(admId);

						System.out.println("削除メソッド終了");

						//セッション情報を破棄する
						adminInfo.removeAttribute("admId");
						adminInfo.removeAttribute("admName");
						adminInfo.removeAttribute("password");
						adminInfo.removeAttribute("admExp");
//						System.out.println("セッション情報を破棄");

						//管理者メンテナンス画面に戻る
						String msg = "管理者情報を削除しました";
						request.setAttribute("msg", msg);
						request.getRequestDispatcher("adminMaintenance.jsp").forward(request, response);

					}else {//一致していない場合はエラーメッセージ渡して戻る
						String msg = "入力されたパスワードが不正です。"
								+ "正しいパスワードを入力してください。";
						request.setAttribute("msg", msg);
						request.getRequestDispatcher("adminMaintenance.jsp").forward(request, response);
					}

				} catch (IdealException e) {
				String msg = ((IdealException) e).getMsg();
//				System.out.println(msg);
				request.setAttribute("msg", msg);
				RequestDispatcher rd = request.getRequestDispatcher("/adminDelete.jsp");
				rd.forward(request, response);
				}
				break;

		}

	}

}
