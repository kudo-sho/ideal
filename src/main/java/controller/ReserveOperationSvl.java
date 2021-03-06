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
		//	tableId = Integer.parseInt(request.getParameter("tableId"));
		}catch(Exception e){

		}
	   	System.out.println("oSv:"+rsvYy+" "+rsvMm+" "+rsvDd+" "+rsvHh+" "+rsvMi+
	   			" "+usrId+" "+person+" "+courseId);

		// 予約日時が現在時刻の3時間後内であればメッセージをセットして予約一覧に戻る
		Calendar rsvCal = Calendar.getInstance(); // 予約日時
		rsvCal.set(rsvYy, rsvMm-1, rsvDd, rsvHh, rsvMi);
		Calendar add0Cal = Calendar.getInstance(); // 現在日時
		Calendar add3Cal = Calendar.getInstance();
		add3Cal.add(Calendar.HOUR_OF_DAY,3); // 3時間後
		int diff0 = rsvCal.compareTo(add0Cal);
		int diff3 = rsvCal.compareTo(add3Cal);
		String errMsg = "";
		if(diff0>0 && diff3<= 0) {
			errMsg = "予約時刻まで３時間以内ですので直接店舗にお電話ください";
		}
	   	
		String url = "ReserveListSvl";
		int msgNo = 0;
		String mode = "";
		Reserve r = null;

		try{
			// セション情報が取得できるかチェック
			int sessionChk = (int)usrInfo.getAttribute("usrId");
			// リクエストパラメータからmodeを取得
			mode =request.getParameter("mode");
			
		}catch(Exception e){
			IdealException ie = new IdealException(msgNo);
			request.setAttribute("msg", ie.getMsg());
			url = "/home.jsp";

		}

		switch(mode) {
		case "登録処理":
			r = new Reserve();
			try{
				//r.setRsvId(rsvId);
				r.setRsvYy(rsvYy);
				r.setRsvMm(rsvMm);
				r.setRsvDd(rsvDd);
				r.setRsvHh(rsvHh);
				r.setRsvMi(rsvMi);
				r.setUsrId(usrId);
				r.setPerson(person);
				r.setCourseId(courseId);
				
				if(errMsg != "") {
					request.setAttribute("reserve",r);
					request.setAttribute("msg", errMsg);
					url = "ReserveInsertSvl";
					break;
				}

				Calendar cal = Calendar.getInstance();
				cal.set(rsvYy, rsvMm-1, rsvDd, rsvHh, rsvMi);
				Date date = cal.getTime();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm");
				String dateStr = dateFormat.format(date);
				//System.out.println("insertChk前:"+dateStr+","+person);

				TableLoc tl = Reserve.insertChk(dateStr, person);
				if(tl != null){
					r.setTableId(tl.getTableId());
					r.setTableName(tl.getTableName());
					//System.out.println("insertChk後:"+tl.getTableId()+","+tl.getTableName());

					Course cs = Course.getCourse(courseId);
					r.setCourseName(cs.getCourseName());

					//System.out.println("insert前:");
					r = Reserve.insert(r);
					//System.out.println("insert後:");
					rsvId = r.getRsvId();
					request.setAttribute("rsvId",rsvId);
					url = "/reserveCompletion.jsp";

				}else{
					//request.setAttribute("reserve",r);
					msgNo = IdealException.ERR_NO_NOT_VACANCY;
					throw new IdealException(msgNo);

				}

			}catch(Exception e){
				IdealException ie = new IdealException(msgNo);
				request.setAttribute("reserve",r);
				request.setAttribute("msg", ie.getMsg());
				url = "ReserveInsertSvl";

			}
			break;
		case "変更処理":
			r = new Reserve();
			try{
				rsvId = Integer.parseInt(request.getParameter("rsvId"));
				tableId = Integer.parseInt(request.getParameter("tableId"));

				r.setRsvId(rsvId);
				r.setRsvYy(rsvYy);
				r.setRsvMm(rsvMm);
				r.setRsvDd(rsvDd);
				r.setRsvHh(rsvHh);
				r.setRsvMi(rsvMi);
				r.setUsrId(usrId);
				r.setPerson(person);
				r.setCourseId(courseId);

				if(errMsg != "") {
					request.setAttribute("reserve",r);
					request.setAttribute("msg", errMsg);
					url = "ReserveUpdateSvl";
					break;
				}

				Calendar cal = Calendar.getInstance();
				cal.set(rsvYy, rsvMm-1, rsvDd, rsvHh, rsvMi);
				Date date = cal.getTime();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm");
				String dateStr = dateFormat.format(date);
				//System.out.println("updateChk前:"+rsvId+","+dateStr+","+person);
				
				TableLoc tl = Reserve.updateChk(rsvId, dateStr, person);
				if(tl != null){
					r.setTableId(tl.getTableId());
					r.setTableName(tl.getTableName());
					//System.out.println("updateChk後:"+tl.getTableId()+","+tl.getTableName());

					Course cs = Course.getCourse(courseId);
					r.setCourseName(cs.getCourseName());

					r = Reserve.update(r);
					request.setAttribute("msg","予約番号＜"+rsvId+"＞の予約を変更しました");
					url = "ReserveListSvl";

				}else{
					//request.setAttribute("reserve",r);
					msgNo = IdealException.ERR_NO_NOT_VACANCY;
					throw new IdealException(msgNo);

				}

			}catch(Exception e){
				IdealException ie = new IdealException(msgNo);
				request.setAttribute("msg", ie.getMsg());
				request.setAttribute("reserve",r);
				url = "ReserveUpdateSvl";

			}
			break;
		case "削除処理":
			r = new Reserve();
			try{
				rsvId = Integer.parseInt(request.getParameter("rsvId"));
				r.setRsvId(rsvId);
				Reserve.delete(r);
				request.setAttribute("msg","予約番号＜"+rsvId+"＞の予約を取消しました");
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
