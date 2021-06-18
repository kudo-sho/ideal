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

import model.Course;
import model.IdealException;
import model.Reserve;

/**
 * Servlet implementation class ReserveUpdateSvl
 */
@WebServlet("/ReserveUpdateSvl")
public class ReserveUpdateSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveUpdateSvl() {
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

		HttpSession userInfo = request.getSession(false);
		if(userInfo == null){
			response.sendRedirect(url);
		}

		try{
			// セション情報が取得できるかチェック
			int usrId = (int)userInfo.getAttribute("usrId");
			// リクエストパラメータから予約番号を取得
			int	rsvId = Integer.parseInt(request.getParameter("rsvId"));
			//「リクエストオブジェクト"courseList"にオーダー可能なコースの一覧情報を設定する」
			ArrayList<Course> al = new ArrayList<Course>();
			al = Course.getOneCourseList();
			request.setAttribute("courseList",al);

			Reserve r = new Reserve();
			r = (Reserve)request.getAttribute("reserve");
			if(r==null) {
				r = Reserve.getReserve(rsvId);
			}
			request.setAttribute("reserve",r);
			url = "/reserveUpdate.jsp";

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
