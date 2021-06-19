package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

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
 * Servlet implementation class ReserveInsertSvl
 */
@WebServlet("/ReserveInsertSvl")
public class ReserveInsertSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveInsertSvl() {
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

		int rsvYy = 0;
		int rsvMm = 0;
		int rsvDd = 0;
		int rsvHh = 0;
		int rsvMi = 0;
		int usrId = 0;
		int person = 0;
		int courseId = 0;

		try{
			rsvYy = Integer.parseInt(request.getParameter("rsvYy"));
			rsvMm = Integer.parseInt(request.getParameter("rsvMm"));
			rsvDd = Integer.parseInt(request.getParameter("rsvDd"));
			rsvHh = Integer.parseInt(request.getParameter("rsvHh"));
			rsvMi = Integer.parseInt(request.getParameter("rsvMi"));
			usrId = Integer.parseInt(request.getParameter("usrId"));
			person = Integer.parseInt(request.getParameter("person"));
			courseId = Integer.parseInt(request.getParameter("courseId"));
		}catch(Exception e){

		}

		try{
			//usrIdが0の時、セション情報のusrIdをセット
			if(usrId == 0) {
				usrId = (int)userInfo.getAttribute("usrId");
			}
			System.out.println("iSv:"+rsvYy+" "+rsvMm+" "+rsvDd+" "+rsvHh+" "+rsvMi+
	    			" "+usrId+" "+person+" "+courseId);
			//「リクエストオブジェクト"courseList"にオーダー可能なコースの一覧情報を設定する」
			ArrayList<Course> al = new ArrayList<Course>();
			al = Course.getOneCourseList();
			request.setAttribute("courseList",al);

			if((rsvYy+rsvMm+rsvDd+rsvHh+rsvMi)==0) {
				Calendar cl = Calendar.getInstance();
				rsvYy = cl.get(Calendar.YEAR);
				rsvMm = cl.get(Calendar.MONTH)+1;
				rsvDd = cl.get(Calendar.DATE);
				rsvHh = cl.get(Calendar.HOUR);
				rsvMi = cl.get(Calendar.MINUTE);
			}

			Reserve r = new Reserve();
			r.setRsvYy(rsvYy);
			r.setRsvMm(rsvMm);
			r.setRsvDd(rsvDd);
			r.setRsvHh(rsvHh);
			r.setRsvMi(rsvMi);
			r.setUsrId(usrId);
			r.setPerson(person);
			r.setCourseId(courseId);

			request.setAttribute("reserve",r);
			url = "/reserveInsert.jsp";

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
