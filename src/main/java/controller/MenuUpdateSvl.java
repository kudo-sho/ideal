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
import model.MenuType;

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

		int typeID = 0;
		int menuID = 0;
		int mType = 0;
//		int msg;
//		ArrayList<Menu> al = new ArrayList<Menu>();
//		ArrayList<Course> alc = new ArrayList<Course>();

		try {
		/* セッション情報(adminInfo)がnullの時、ホームページ(home.jsp)へ遷移する処理 */
		HttpSession session = request.getSession(true);
		if(session.getAttribute("adminInfo") == null){
//			rd = request.getRequestDispatcher("/home.jsp");
//			rd.forward(request, response);
//			↓こっちの方が良さそう？
			response.sendRedirect("/ideal/home.jsp");
			return;
		}

		try {
		/*リクエストパラメーター"typeID"と"menuID"を受け取る。*/
			typeID = Integer.parseInt(request.getParameter("CtypeID"));
			menuID = Integer.parseInt(request.getParameter("CmenuID"));
		} catch (NumberFormatException e) {
//			e.printStackTrace();
		}

			/* typeIDが100の時の処理 */
			/*
			 * typeMenuListにコース構成分類ごとのメニュー一覧(ArrayList<Menu>)を取得し、設定する。
			 * その後の遷移先urlに新規コース登録画面(courseUpdate.jsp)をセットする。
			 */
//			if (typeID == 100) {
//				request.setAttribute("typeMenuList", al);
//				request.setAttribute("oneCourse", alc);
//				rd = request.getRequestDispatcher("/courseUpdate.jsp");
//				rd.forward(request, response);
//				return;

				/* typeIDが100以外の時の処理 */
				/*
				 * mTypeに分類一覧(ArryList<MenuType>)をセットする。
				 * リクエストオブジェクト"typeID"にtypeIDをセット。
				 * 遷移先urlに新規メニュー登録画面(menuUpdate.jsp)をセットする。
				 */
//			} else if (typeID != 100) {
				request.setAttribute("mType", MenuType.getAllType());
//				typeID = Integer.parseInt(request.getParameter("typeID"));
				request.setAttribute("oneMenu", Menu.getOneMenu(menuID, mType));
				rd = request.getRequestDispatcher("/menuUpdate.jsp");
				rd.forward(request, response);
				return;

				/* 例外処理(まだちゃんと出来てない)
				 * リクエストオブジェクト"msg"に独自例外よりメッセージを取得し設定する。
				 */
//			}
//			else {
//				msg = Integer.parseInt(request.getParameter("msg"));
//				rd = request.getRequestDispatcher("MenuMaintenanceSvl");
//				rd.forward(request, response);
//			}
		}catch(Exception e) {
			//			}catch(Exception e) {
			IdealException ie = new IdealException(IdealException.ERR_NO_EXCEPTION);
			request.setAttribute("msg", ie.getMsg());
			//			System.out.println(msg);
			rd = request.getRequestDispatcher("MenuMaintenanceSvl");
			rd.forward(request, response);
//			return;
//			e.printStackTrace();

		}
		//ページのフォワーディング
//		rd = request.getRequestDispatcher("/menuUpdate.jsp");
//		rd.forward(request, response);
	}
}
