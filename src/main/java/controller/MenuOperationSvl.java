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
import model.Menu;

/**
 * Servlet implementation class MenuOperationSvl
 */
@WebServlet("/MenuOperationSvl")
public class MenuOperationSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final int INSERT = 11;
	public static final int UPDATE = 12;
	public static final int DELETE= 13;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MenuOperationSvl() {
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

		Menu menu = null;
		int mode  = 0;
		int menuID = 0;
		String menuName ;
		String detail ;
//		String typeName;
		int orderFlg ;
		int price ;
		int typeID ;

		menuName = request.getParameter("menuName");
		detail = request.getParameter("detail");
//		typeName = request.getParameter("typeName");
		try{
			menuID = Integer.parseInt(request.getParameter("menuID"));
			orderFlg = Integer.parseInt(request.getParameter("orderFlg"));
			price = Integer.parseInt(request.getParameter("price"));
			typeID = Integer.parseInt(request.getParameter("typeID"));
		}catch(NumberFormatException e){
			orderFlg = 0;
			price = 0;
			typeID = 0;
		}

		try{
			//セッション情報がnullならホームページへ
						HttpSession session = request.getSession(true);
						if(session.getAttribute("adminInfo") == null){
							rd = request.getRequestDispatcher("/home.jsp");
							rd.forward(request, response);
						}

			mode = Integer.parseInt(request.getParameter("mode"));

			//おそらく↓だけど、もしかしたら処理によってm_idとかでDBから情報引っ張ってくる・・・？
			//削除時
			if(mode == 13){
				menu = Menu.getOneMenu(menuID, typeID);


				//変更時
			}else if(mode == 12){
				menu = new Menu();
				menu.setMenuId(menuID);
				menu.setMenuName(menuName);
				menu.setDetail(detail);
				menu.setPrice(price);
				menu.setOrderFlg(orderFlg);
				menu.setTypeId(typeID);


			//登録時
		}else if(mode == 11){
			menu = new Menu();
			menu.setMenuName(menuName);
			menu.setDetail(detail);
			menu.setOrderFlg(orderFlg);
			menu.setPrice(price);
			menu.setTypeId(typeID);

		}

			//メニュー情報更新
			typeID = Menu.updateMenu(menu,mode);

			request.setAttribute("typeID", typeID);

			//ここまで問題なければメニューメンテ画面（Svl）に戻って再表示
			rd = request.getRequestDispatcher("MenuMaintenanceSvl");


			//独自例外が発生したらメッセージ取得＆管理者処理画面に遷移
			//			}catch(IdealException |ServletException | IOException e) {
		}catch(Exception e) {

			//			}catch(Exception e) {
			IdealException ie = new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			request.setAttribute("msg", ie.getMsg());
			request.setAttribute("onemenu", menu);
			//			System.out.println(msg);

			//mode次第で遷移先を変更
			if(mode == MenuOperationSvl.INSERT){
				rd = request.getRequestDispatcher("MenuInsertSvl");
			}else if(mode == MenuOperationSvl.UPDATE){
				rd = request.getRequestDispatcher("MenuUpdateSvl");
			}else if(mode == MenuOperationSvl.DELETE){
				rd = request.getRequestDispatcher("MenuDeleteSvl");
			}else{
				rd = request.getRequestDispatcher("MenuMaintenanceSvl");
			}
		}finally{
			rd.forward(request, response);
		}
	}
}
