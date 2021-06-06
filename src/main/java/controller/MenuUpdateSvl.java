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
import model.Menu;

//工事中につき未完成

/**
 * Servlet implementation class MenuUpdateSvl
 */
@WebServlet("/MenuUpdateSvl")
public class MenuUpdateSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuUpdateSvl() {
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
		String url = "";

		int typeID = 0;
		int menuID = 0;
		int mType = 0;
		int msg = 0;
		AdminLoginSvl als = new AdminLoginSvl();
		ArrayList<Menu> al = new ArrayList<Menu>();
		ArrayList<Course> alc = new ArrayList<Course>();
		/* セッション情報(adminInfo)がnullの時、ホームページ(home.jsp)へ遷移する処理 */
		HttpSession session = request.getSession();		try {
			if (als == null) {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/home.jsp");
				dispatcher.forward(request, response);
			}
		/*リクエストパラメーター"typeID"と"menuID"を受け取る。*/
			typeID = Integer.parseInt(request.getParameter("typeID"));
			menuID = Integer.parseInt(request.getParameter("menuID"));
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
				request.setAttribute("typeMenuList", al);
				request.setAttribute("oneCourse", alc);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/courseUpdate.jsp");
				dispatcher.forward(request, response);

				/* typeIDが100以外の時の処理 */
				/*
				 * mTypeに分類一覧(ArryList<MenuType>)をセットする。
				 * リクエストオブジェクト"typeID"にtypeIDをセット。
				 * 遷移先urlに新規メニュー登録画面(menuInsert.jsp)をセットする。
				 */
			} else if (typeID != 100) {
				request.setAttribute("ArrayList<MenuType>", Menu.getMenu(mType));
				typeID = Integer.parseInt(request.getParameter("typeID"));
				request.setAttribute("Menu", Menu.getOneMenu(menuID, mType));
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/menuInsert.jsp");
				dispatcher.forward(request, response);

				/* 例外処理(まだちゃんと出来てない)
				 * リクエストオブジェクト"msg"に独自例外よりメッセージを取得し設定する。
				 */
			} else {
				msg = Integer.parseInt(request.getParameter("msg"));
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/ideal/WebContent/MenuMaintenanceSvl.java");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//ページのフォワーディング
		rd = request.getRequestDispatcher("menuInsert.jsp");
		rd.forward(request, response);
	}
}
