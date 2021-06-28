package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.IdealException;
import model.User;

/**
 * Servlet implementation class UserOperationSvl
 */
@WebServlet("/UserOperationSvl")
public class UserOperationSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserOperationSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		HttpSession userInfo = request.getSession(true);
		String mode = "";
		User user = new User();
		int msgNo = 0;

		try {
			//リクエストパラメータ"mode"を受け取る
			mode = request.getParameter("mode");

			//セッション情報を取得する ※この時点ではセッションはNULL
			//「セッション=NULL」かつ「登録処理以外」の場合はホームページへ戻る
			if((userInfo.getId() == null) & (!mode.equals("登録処理")) ){
				request.setAttribute("msg", "障害が発生しました");
				request.getRequestDispatcher("home.jsp").forward(request, response);
			}
	
			//リクエストパラメータを取得し、顧客情報(User)を生成する
			//User user = new User();
			user.setUsrName(request.getParameter("usrName"));
			user.setAddress(request.getParameter("address"));
			user.setPhone(request.getParameter("phone"));
			user.setMail(request.getParameter("mail"));
			user.setPassword(request.getParameter("password"));
	
		} catch(Exception e) {
			IdealException ie = new IdealException(msgNo);
			request.setAttribute("msg", ie.getMsg());

			//ホーム画面に戻る
			request.getRequestDispatcher("home.jsp").forward(request, response);

		}
		//modeにより処理を行う*************************************************
		switch(mode){

			case "登録処理":
				try {

					//ビーンズのinsertメソッドを実行する　(顧客情報を登録し、顧客IDを取得する)
					User.insert(user);

					//登録したユーザー情報をセッション(userInfo)に設定する
					userInfo.setAttribute("usrId", user.getUsrId());
					userInfo.setAttribute("usrName", user.getUsrName());
					userInfo.setAttribute("password", user.getPassword());
					userInfo.setAttribute("address", user.getAddress());
					userInfo.setAttribute("phone", user.getPhone());
					userInfo.setAttribute("mail", user.getMail());

					//完了画面にアクセスする
					request.getRequestDispatcher("userInsertCompletion.jsp").forward(request, response);

				} catch (Exception e) {

					//リクエストオブジェクト"msg"にメッセージを取得して設定する
					//String msg = ((IdealException) e).getMsg();
					//request.setAttribute("msg", msg);
					IdealException ie = new IdealException(msgNo);
					request.setAttribute("msg", ie.getMsg());


					//リクエストオブジェクト"user"に顧客情報(User)を設定する
					request.setAttribute("User", user);

					//新規登録画面に戻る
					request.getRequestDispatcher("userInsert.jsp").forward(request, response);

				}
				break;


			case "変更処理":
				try{
					//usrIdをセッションからuserにセットする
					user.setUsrId((int)userInfo.getAttribute("usrId"));

					//ビーンズのupdateメソッドを呼び出す
					User.update(user);

					//変更したユーザー情報をセッション(userInfo)に設定する
					userInfo.setAttribute("usrName", user.getUsrName());
					userInfo.setAttribute("password", user.getPassword());
					userInfo.setAttribute("address", user.getAddress());
					userInfo.setAttribute("phone", user.getPhone());
					userInfo.setAttribute("mail", user.getMail());

					//処理選択画面に戻る
					request.getRequestDispatcher("userIndex.jsp").forward(request, response);

				}catch(Exception e){
					//String msg = ((IdealException) e).getMsg();
					//request.setAttribute("msg", msg);
					IdealException ie = new IdealException(msgNo);
					request.setAttribute("msg", ie.getMsg());

					//顧客情報変更画面表示処理(Servlet)に戻る
					request.getRequestDispatcher("UserUpdateSvl").forward(request, response);
				}
				break;


			case "削除処理":
				try {
					System.out.println("削除メソッド開始前");

					//deleteメソッドを実行する
					if(User.delete((int)userInfo.getAttribute("usrId"))){
						
						System.out.println("削除メソッドtrue");
	
						//セッション情報を破棄する
						userInfo.removeAttribute("usrId");
						userInfo.removeAttribute("usrName");
						userInfo.removeAttribute("password");
						userInfo.removeAttribute("address");
						userInfo.removeAttribute("phone");
						userInfo.removeAttribute("mail");
						userInfo.removeAttribute("exp");
	
						System.out.println("セッション情報を破棄");
					
						//ホーム画面に戻る
						request.getRequestDispatcher("home.jsp").forward(request, response);
					
					}else{
						System.out.println("削除メソッドfalse");
						request.setAttribute("msg", "脱会するときは先に予約を削除してください。予約が残っていると脱会できません。");
						request.getRequestDispatcher("UserDeleteSvl").forward(request, response);
					}
					
					
				} catch (Exception e) {
					//String msg = ((IdealException) e).getMsg();
					//request.setAttribute("msg", msg);
					IdealException ie = new IdealException(msgNo);
					request.setAttribute("msg", ie.getMsg());

					//顧客情報削除画面表示処理に戻る
					//request.getRequestDispatcher("userDelete.jsp").forward(request, response);
					request.getRequestDispatcher("UserDeleteSvl").forward(request, response);

				}
				break;

		}
	}

}
