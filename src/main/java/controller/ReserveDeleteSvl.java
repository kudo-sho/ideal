package controller;

import java.io.IOException;

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
 * Servlet implementation class ReserveDeleteSvl
 */
@WebServlet("/ReserveDeleteSvl")
public class ReserveDeleteSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveDeleteSvl() {
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

		int msgNo = 0;
		String url = "home.jsp";

		HttpSession usrInfo = request.getSession(false);
		if(usrInfo == null){
			response.sendRedirect(url);
		}

		try{
			// セション情報が取得できるかチェック
			int usrId = (int)usrInfo.getAttribute("usrId");
			// リクエストパラメータから予約番号を取得
			int	rsvId = Integer.parseInt(request.getParameter("rsvId"));

			Reserve r = new Reserve();
			r = Reserve.getReserve(rsvId);
			request.setAttribute("reserve",r);
			url = "/reserveDelete.jsp";

		}
		catch(Exception e){
			IdealException ie = new IdealException(msgNo);
			request.setAttribute("msg", ie.getMsg());
			url = "ReserveListSvl";

		}

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request,response);
		return;


	}

}
