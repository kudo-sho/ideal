package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AdminMaintenance;
import model.IdealException;

/**
 * Servlet implementation class AdminMaintenanceSvl
 */
@WebServlet("/AdminMaintenanceSvl")
public class AdminMaintenanceSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMaintenanceSvl() {
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
		RequestDispatcher rd = null;

		try{
			//セッション情報がnullならホームページへ
			HttpSession session = request.getSession(true);
			if(session.getAttribute("adminInfo") == null){
				rd = request.getRequestDispatcher("/home.jsp");
				rd.forward(request, response);
			}


			//adminリストを取得してセッションにセットする
			ArrayList<AdminMaintenance> alam = AdminMaintenance.getAdminList();
			request.setAttribute("admList",alam);

			//ここまで問題なければadminMaintenance画面に戻って再表示
			rd = request.getRequestDispatcher("/adminMaintenance.jsp");


			//独自例外が発生したらメッセージ取得＆管理者処理画面に遷移
		}catch(IdealException e) {
//			}catch(Exception e) {
			IdealException ie = new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			request.setAttribute("msg", ie.getMsg());
//			System.out.println(msg);
			rd = request.getRequestDispatcher("/adminIndex.jsp");
			rd.forward(request, response);

		}
	}

}
