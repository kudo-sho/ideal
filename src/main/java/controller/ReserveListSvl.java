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

import model.IdealException;
import model.Reserve;

/**
 * Servlet implementation class ReserveListSvl
 */
@WebServlet("/ReserveListSvl")
public class ReserveListSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveListSvl() {
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
		response.setContentType("text/html;charset=UTF-8");

		// メッセージ番号と引継ぎ先URLの初期値をセット
		int msgNo = 0;
		String url = "home.jsp";

		// セション情報を取得し、なければhomeに戻る
		HttpSession usrInfo = request.getSession(false);
		if(usrInfo == null){
			response.sendRedirect(url);
		}

		try{
			// セション情報からusrIdを取得
			int usrId = (int)usrInfo.getAttribute("usrId");
			// usrIdをキーにreserveテーブルから予約情報を取得し、ArrayListに格納
			ArrayList<Reserve> al = new ArrayList<Reserve>();
			al = Reserve.getReserveList(usrId);
			// 予約情報が格納されたArrayListをリクエストパラメーターに格納しjspへ引継ぎ
			request.setAttribute("reserveList",al);
			url = "/reserveList.jsp";

		}
		catch(Exception e){
			// 例外発生時にメッセージ番号からメッセージ文を取得し、顧客メニューに戻る
			IdealException ie = new IdealException(msgNo);
			request.setAttribute("msg", ie.getMsg());
			url = "/userIndex.jsp";

		}

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request,response);
		return;

	}

}
