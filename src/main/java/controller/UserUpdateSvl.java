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
 * Servlet implementation class UserUpdateSvl
 */
@WebServlet("/UserUpdateSvl")
public class UserUpdateSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateSvl() {
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

		System.out.println("Deleteサーブレット開始");

		//リクエストパラメータ"mode"を受け取る
		String mode = request.getParameter("mode");

		//セッション情報(userInfo)を取得する
		HttpSession userInfo = request.getSession(false);


		//セッション情報(userInfo)がnullの時、ホーム画面に遷移する
		if(userInfo == null){
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}

		System.out.println("Deleteサーブレット途中");
		//リクエストオブジェクト(user)に、セッション情報(userInfo)のusrIdに対応する顧客情報を設定する
		try{
			System.out.println("try開始");
			System.out.println((int)userInfo.getAttribute("usrId"));
			int usrId = (int)userInfo.getAttribute("usrId");	//Nullになる
			System.out.println("ユーザー取得メソッド開始");
			User user = User.getUser(usrId);
			System.out.println("ユーザー取得メソッド終了");
			//System.out.println(userInfo.getAttribute("usrId"));
			//System.out.println(userInfo.getAttribute("usrName"));
			System.out.println(userInfo.getAttribute("address"));
			//ビーンズでセッターに各々設定したであろうuserオブジェクトをリクエストに設定する(？)
			request.setAttribute("user", user);

			//顧客情報変更画面に遷移する
			request.getRequestDispatcher("userUpdate.jsp").forward(request, response);

		}catch(Exception e){
			String msg = ((IdealException) e).getMsg();
			request.setAttribute("msg", msg);

			//新規登録画面に戻る
			request.getRequestDispatcher("userIndex.jsp").forward(request, response);
		}




	}

}
