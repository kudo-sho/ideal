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

//工事中につき未完成

/**
 * Servlet implementation class MenuInsertSvl
 */
@WebServlet("/MenuInsertSvl")
public class MenuInsertSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// ファイナル変数宣言
	public static final int MenuInsertSvl = 4;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		RequestDispatcher rd = null;
		String url = "";

		int typeID = 0;
		int menuID = 0;
		int mType = 0;
		int msg = 0;
		AdminLoginSvl als = new AdminLoginSvl();
		ArrayList<Menu> al = new ArrayList<Menu>();

		try {
			request.setAttribute("mType", MenuType.getAllType());
		} catch (IdealException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		/* セッション情報(adminInfo)がnullの時、ホームページ(home.jsp)へ遷移する処理 */
		HttpSession session = request.getSession();
		try {
			if (als == null) {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/home.jsp");
				dispatcher.forward(request, response);
			}
			typeID = Integer.parseInt(request.getParameter("typeID"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		try {
			/* typeIDが100の時の処理 */
			/*
			 * typeMenuListにコース構成分類ごとのメニュー一覧(ArrayList<Menu>)を取得し、設定する。
			 * その後の遷移先urlに新規コース登録画面(courseInsert.jsp)をセットする。
			 */
			if (typeID == 100) {
				request.setAttribute("typeMenuList",
						al);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/courseInsert.jsp");
				dispatcher.forward(request, response);

				/* typeIDが100以外の時の処理 */
				/*
				 * mTypeに分類一覧(ArryList<MenuType>)をセットする。
				 * リクエストオブジェクト"typeID"にtypeIDをセット。
				 * 遷移先urlに新規メニュー登録画面(menuInsert.jsp)をセットする。
				 */
			} else if (typeID != 100) {
				ArrayList<MenuType> almt = new ArrayList<MenuType>();
				request.setAttribute("mType", almt);
				typeID = Integer.parseInt(request.getParameter("typeID"));
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/menuInsert.jsp");
				dispatcher.forward(request, response);
				request.setAttribute("mt", MenuType.getAllType());

			} else {
				msg = Integer.parseInt(request.getParameter("msg"));
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("MenuMaintenanceSvl");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			int msgNo =0;
			IdealException ie= new IdealException(msgNo);
			request.setAttribute("msg", ie.getMsg());
			url = "MenuMaintenanceSvl";
		}
		url = "/WebContent/menuMaintenance.jsp";
		//ページのフォワーディング
		rd = request.getRequestDispatcher("/menuInsert.jsp");
		rd.forward(request, response);
	}
}
