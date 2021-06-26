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
import model.CourseCtl;
import model.IdealException;

/**
 * Servlet implementation class CourseOperationSvl
 */
@WebServlet("/CourseOperationSvl")
public class CourseOperationSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final int INSERT = 11;
	public static final int UPDATE = 12;
	public static final int DELETE = 13;
	public static final int[] COURSE_MENU_TYPE_ID = {200,210,220,300,310,400};


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CourseOperationSvl() {
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

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		RequestDispatcher rd = null;

		Course c = null;
		ArrayList<CourseCtl> coursectl = null;
		int mode  = 0;
		int courseID = 0;
		String courseName = null ;
		String detail = null ;
//		String typeName;
		int orderFlg = 0 ;
		int price = 0 ;
		int typeID = 0 ;

		int appetizerID = 0;
		int soupID = 0;
		int pastaID = 0;
		int meatID = 0;
		int fishID = 0;
		int dessertID = 0;

		try {
		typeID = Integer.parseInt(request.getParameter("typeID"));
		}catch(NumberFormatException e) {
		}
		try {
		courseID = Integer.parseInt(request.getParameter("courseID"));
		}catch(NumberFormatException e) {
		}
		try {
		courseName = request.getParameter("courseName");
		price = Integer.parseInt(request.getParameter("price"));
		orderFlg = Integer.parseInt(request.getParameter("orderFlg"));
		detail = request.getParameter("detail");
		appetizerID = Integer.parseInt(request.getParameter("appetizerID"));
		soupID = Integer.parseInt(request.getParameter("soupID"));
		pastaID = Integer.parseInt(request.getParameter("pastaID"));
		meatID = Integer.parseInt(request.getParameter("meatID"));
		fishID = Integer.parseInt(request.getParameter("fishID"));
		dessertID = Integer.parseInt(request.getParameter("dessertID"));
		}catch(NumberFormatException e) {
		}
//		typeName = request.getParameter("typeName");
		int[] ids = {appetizerID,soupID,pastaID,meatID,fishID,dessertID};

		try{
			//セッション情報がnullならホームページへ
						HttpSession session = request.getSession(true);
						if(session.getAttribute("adminInfo") == null){
							response.sendRedirect("/ideal/home.jsp");
							return;
						}

						try {
							mode = Integer.parseInt(request.getParameter("mode"));
						}catch(NumberFormatException e) {
							mode = 100;
						}
			//削除時
			if(mode == CourseOperationSvl.DELETE){
				c = Course.getCourse(courseID);
				coursectl = new ArrayList<CourseCtl>();

			//変更時
			}else if(mode == CourseOperationSvl.UPDATE){
				c = new Course();
				c.setCourseId(courseID);
				c.setCourseName(courseName);
				c.setDetail(detail);
				c.setOrderFlg(orderFlg);
				c.setPrice(price);
				c.setTypeId(typeID);

				coursectl = new ArrayList<CourseCtl>();
				for(int x = 0; x < ids.length; x++) {
					CourseCtl ctl = new CourseCtl();
					ctl.setC_Id(courseID);
					ctl.setM_Id(ids[x]);
					if(ids[x] != 0) {
						coursectl.add(ctl);
					}
				}


			//登録時
		}else if(mode == CourseOperationSvl.INSERT){
			c = new Course();
			c.setCourseName(courseName);
			c.setDetail(detail);
			c.setOrderFlg(orderFlg);
			c.setPrice(price);
			c.setTypeId(typeID);

			coursectl = new ArrayList<CourseCtl>();
			for(int x = 0; x < ids.length; x++) {
				CourseCtl ctl = new CourseCtl();
				ctl.setC_Id(0);
				ctl.setM_Id(ids[x]);
				if(ids[x] != 0) {
					coursectl.add(ctl);
				}
			}

		}

			//コース情報更新
			typeID = Course.updateCourse(c,mode,coursectl);
//			request.setAttribute("typeID", typeID);


			//独自例外が発生したらメッセージ取得＆管理者処理画面に遷移
		}catch(Exception e) {
//			e.printStackTrace();
			IdealException ie = new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			request.setAttribute("msg", ie.getMsg());
			//			System.out.println(msg);

		}finally{
			//仕様書ではmodeごとに↓書くような感じだけど、遷移先全部一緒で無意味なので一回にまとめる
			rd = request.getRequestDispatcher("MenuMaintenanceSvl");
			rd.forward(request, response);
		}
	}
}
