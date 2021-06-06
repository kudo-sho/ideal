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
import model.Menu;
import model.MenuType;


/**
 * Servlet implementation class MenuMaintenanceSvl
 */
@WebServlet("/MenuMaintenanceSvl")
public class MenuMaintenanceSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MenuMaintenanceSvl() {
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
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		RequestDispatcher rd = null;
		int typeId = 100;

		try{
			//セッション情報がnullならホームページへ
			HttpSession session = request.getSession(true);
			if(session.getAttribute("adminInfo") == null){
				rd = request.getRequestDispatcher("/home.jsp");
				rd.forward(request, response);
			}

			if(request.getParameter("typeID") == null){
				request.setAttribute("typeID", 100);
			}else{

				typeId = Integer.parseInt(request.getParameter("typeID"));
				request.setAttribute("typeID", typeId);
				//↑これとか使わないかも
			}

			ArrayList<MenuType> almt = MenuType.getAllType();
			request.setAttribute("mType",almt);

			ArrayList<Menu> alm = Menu.getMenu(typeId);
			request.setAttribute("menu",alm);

			//ここまで問題なければメニューメンテ画面に戻って再表示
			rd = request.getRequestDispatcher("/menuMaintenance.jsp");


			//独自例外が発生したらメッセージ取得＆管理者処理画面に遷移
//			}catch(IdealException |ServletException | IOException e) {
		}catch(IdealException e) {
//			}catch(Exception e) {
			IdealException ie = new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			request.setAttribute("msg", ie.getMsg());
//			System.out.println(msg);
			rd = request.getRequestDispatcher("/adminIndex.jsp");

		}finally{
			rd.forward(request, response);
		}

	}

}
