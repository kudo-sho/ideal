package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CourseCtl;
import model.IdealException;
import model.Menu;

//工事中につき未完成

/**
 * Servlet implementation class MenuDeleteSvl
 */
@WebServlet("/MenuDeleteSvl")
public class MenuDeleteSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MenuDeleteSvl() {
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
		int typeID ;
		int menuID ;
		Menu menu;

		try{
			//セッション情報がnullならホームページへ
			HttpSession session = request.getSession(true);
			if(session.getAttribute("adminInfo") == null){
//				rd = request.getRequestDispatcher("/home.jsp");
//				rd.forward(request, response);
//				↓こっちの方が良さそう？
				response.sendRedirect("/ideal/home.jsp");
				return;
			}

			typeID = Integer.parseInt(request.getParameter("DtypeID"));
//			request.setAttribute("typeID", typeID);
			menuID = Integer.parseInt(request.getParameter("DmenuID"));
//			request.setAttribute("menuID", menuID);
			//↑これとか使わないかも


			if(typeID == 100) { //コースの時

				//まだ書いてない。

			}else { //メニューの時
				try {
					//↓コースに含まれてるメニューだったら例外が投げられてくるので、メニューメンテ画面に戻す
					CourseCtl.courseMenuChk(menuID);
				}catch(IdealException e) {
					IdealException ie = new IdealException(IdealException.ERR_NO_NOT_MENU_DELETE);
					request.setAttribute("msg", ie.getMsg());
					//			System.out.println(msg);

					rd = request.getRequestDispatcher("MenuMaintenanceSvl");
					rd.forward(request, response);
					return;
				}
				menu = Menu.getOneMenu(menuID, typeID);
				request.setAttribute("oneMenu",menu);


				rd = request.getRequestDispatcher("/menuDelete.jsp");
				rd.forward(request, response);
//				rd = request.getRequestDispatcher("MenuMaintenanceSvl");

			}

			//独自例外が発生したらメッセージ取得＆メニューメンテ画面に遷移
			//			}catch(IdealException |ServletException | IOException e) {
		}catch(Exception e) {
			//			}catch(Exception e) {
			IdealException ie = new IdealException(IdealException.ERR_NO_EXCEPTION);
			request.setAttribute("msg", ie.getMsg());
			//			System.out.println(msg);
			rd = request.getRequestDispatcher("MenuMaintenanceSvl");
			rd.forward(request, response);

		}

	}

}
