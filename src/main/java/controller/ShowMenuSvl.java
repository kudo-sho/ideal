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
import model.IdealException;
import model.Menu;

/**
 * Servlet implementation class ShowMenuSvl
 */
@WebServlet("/ShowMenuSvl")
public class ShowMenuSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowMenuSvl() {
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
		String url = "ShowMenu.jsp";

		HttpSession usrInfo = request.getSession(false);

		try{

			//「リクエストオブジェクト"courseList"にオーダー可能なコースの一覧情報を設定する」
			ArrayList<Course> al1 = new ArrayList<Course>();
			al1 = Course.getCourseList();
			request.setAttribute("courseList",al1);

			//「リクエストオブジェクト"menuList"にオーダー可能なメニューの一覧情報を設定する」
			ArrayList<Menu> al2 = new ArrayList<Menu>();
			al2 = Menu.getMenuList();
			request.setAttribute("menuList",al2);

			url = "/showMenu.jsp";

		}
		catch(Exception e){
			IdealException ie = new IdealException(msgNo);
			request.setAttribute("msg", ie.getMsg());

			if(usrInfo == null){
				url = "/home.jsp";
			}else{
				url = "/userIndex.jsp";
			}

		}

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request,response);
		return;

	}

}
