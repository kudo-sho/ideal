package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
import model.TableLoc;

/**
 * Servlet implementation class ReserveOperationSvl
 */
@WebServlet("/ReserveOperationSvl")
public class ReserveOperationSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static int INSERT = 11; //登録処理
	public static int UPDATE = 12; //変更処理
	public static int DELETE = 13; //削除処理

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveOperationSvl() {
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

		HttpSession usrInfo = request.getSession(false);
		if(usrInfo == null){
			response.sendRedirect("home.jsp");
		}

		String mode =request.getParameter("mode");

		int rsvId = 0;
		int rsvYy = 0;
		int rsvMm = 0;
		int rsvDd = 0;
		int rsvHh = 0;
		int rsvMi = 0;
		int usrId = 0;
		int person = 0;
		int courseId = 0;
		int tableId = 0;

		try{
		//	rsvId = Integer.parseInt(request.getParameter("rsvId"));
			rsvYy = Integer.parseInt(request.getParameter("rsvYy"));
			rsvMm = Integer.parseInt(request.getParameter("rsvMm"));
			rsvDd = Integer.parseInt(request.getParameter("rsvDd"));
			rsvHh = Integer.parseInt(request.getParameter("rsvHh"));
			rsvMi = Integer.parseInt(request.getParameter("rsvMi"));
			usrId = Integer.parseInt(request.getParameter("usrId"));
			person = Integer.parseInt(request.getParameter("person"));
			courseId = Integer.parseInt(request.getParameter("courseId"));
			tableId = Integer.parseInt(request.getParameter("tableId"));
		}catch(Exception e){

		}

		String url = "ReserveListSvl";
		int msgNo = 0;

		switch(mode) {
		case "登録処理":
			try{
				Reserve r = new Reserve();
				//r.setRsvId(rsvId);
				r.setRsvYy(rsvYy);
				r.setRsvMm(rsvMm);
				r.setRsvDd(rsvDd);
				r.setRsvHh(rsvHh);
				r.setRsvMi(rsvMi);
				r.setUsrId(usrId);
				r.setPerson(person);
				r.setCourseId(courseId);

				Calendar cal = Calendar.getInstance();
				cal.set(rsvYy, rsvMm-1, rsvDd, rsvHh, rsvMi);
				Date date = cal.getTime();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm");
				String dateStr = dateFormat.format(date);
				System.out.println("insertChk前:"+dateStr);

				TableLoc tl = Reserve.insertChk(dateStr, person);
				if(tl != null){
					r.setTableId(tl.getTableId());
					r.setTableName(tl.getTableName());

					Course cs = Course.getCourse(courseId);
					r.setCourseName(cs.getCourseName());

					Reserve r2 = Reserve.insert(r);
					rsvId = r2.getRsvId();
					request.setAttribute("rsvId",rsvId);
					url = "/reserveCompletion.jsp";

				}else{
					request.setAttribute("reserve",r);
					msgNo = IdealException.ERR_NO_NOT_VACANCY;
					throw new IdealException(msgNo);

				}

			}catch(Exception e){
				IdealException ie = new IdealException(msgNo);
				request.setAttribute("msg", ie.getMsg());
				url = "ReserveInsertSvl";

			}
			break;
		case "変更処理":
			try{
				rsvId = Integer.parseInt(request.getParameter("rsvId"));

				Reserve r = new Reserve();
				r.setRsvId(rsvId);
				r.setRsvYy(rsvYy);
				r.setRsvMm(rsvMm);
				r.setRsvDd(rsvDd);
				r.setRsvHh(rsvHh);
				r.setRsvMi(rsvMi);
				r.setUsrId(usrId);
				r.setPerson(person);
				r.setCourseId(courseId);

				Calendar cal = Calendar.getInstance();
				cal.set(rsvYy, rsvMm-1, rsvDd, rsvHh, rsvMi);
				Date date = cal.getTime();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm");
				String dateStr = dateFormat.format(date);
				System.out.println("updateChk前:"+dateStr);
				
				TableLoc tl = Reserve.updateChk(rsvId, dateStr, person);
				if(tl != null){
					r.setTableId(tl.getTableId());
					r.setTableName(tl.getTableName());

					Course cs = Course.getCourse(courseId);
					r.setCourseName(cs.getCourseName());

					Reserve r2 = Reserve.update(r);
					request.setAttribute("reserve",r2);
					url = "ReserveListSvl";

				}else{
					request.setAttribute("reserve",r);
					msgNo = IdealException.ERR_NO_NOT_VACANCY;
					throw new IdealException(msgNo);

				}

			}catch(Exception e){
				IdealException ie = new IdealException(msgNo);
				request.setAttribute("msg", ie.getMsg());
				url = "ReserveUpdateSvl";

			}
			break;
		case "削除処理":
			try{
				Reserve r = new Reserve();
				r.setRsvId(rsvId);
				Reserve.delete(r);
				url = "ReserveListSvl";

			}catch(Exception e){
				IdealException ie = new IdealException(msgNo);
				request.setAttribute("msg", ie.getMsg());
				url = "ReserveDeleteSvl";

			}
			break;
		}

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request,response);
		return;

	}

}
