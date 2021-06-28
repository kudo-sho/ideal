package controller;

import java.io.IOException;
import java.util.Calendar;

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
			// 予約日時が現在時刻の3時間後内であればメッセージをセットして予約一覧に戻る
			Calendar rsvCal = Calendar.getInstance(); // 予約日時
			rsvCal.set(r.getRsvYy(), r.getRsvMm()-1, r.getRsvDd(), r.getRsvHh(), r.getRsvMi());
			Calendar add0Cal = Calendar.getInstance(); // 現在日時
			Calendar add3Cal = Calendar.getInstance();
			add3Cal.add(Calendar.HOUR_OF_DAY,3); // 3時間後
			int diff0 = rsvCal.compareTo(add0Cal);
			int diff3 = rsvCal.compareTo(add3Cal);
			if(diff0>0 && diff3<= 0) {
				String errMsg = "予約時刻まで３時間以内です。予約の取消は直接店舗にお電話ください。";
				request.setAttribute("msg", errMsg);
				url = "ReserveListSvl";
			}else if(diff0<=0) {
				String errMsg = "予約時刻を過ぎていますので取消できません";
				request.setAttribute("msg", errMsg);
				url = "ReserveListSvl";
			} else {
				request.setAttribute("reserve",r);
				url = "/reserveDelete.jsp";
			}

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
