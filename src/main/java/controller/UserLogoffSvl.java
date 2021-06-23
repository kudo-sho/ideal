package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserLogoffSvl
 */
@WebServlet("/UserLogoffSvl")
public class UserLogoffSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogoffSvl() {
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

		HttpSession userInfo = request.getSession(false);
		System.out.println("セッション情報をすべて破棄します。");

		userInfo.removeAttribute("usrId");
		userInfo.removeAttribute("usrName");
		userInfo.removeAttribute("password");
		userInfo.removeAttribute("address");
		userInfo.removeAttribute("phone");
		userInfo.removeAttribute("mail");
		userInfo.removeAttribute("exp");
		System.out.println("セッション情報を表示します。");
		System.out.println(userInfo.getAttribute("usrId"));
		System.out.println(userInfo.getAttribute("usrName"));
		System.out.println(userInfo.getAttribute("password"));
		System.out.println(userInfo.getAttribute("address"));
		System.out.println(userInfo.getAttribute("phone"));
		System.out.println(userInfo.getAttribute("mail"));
		System.out.println(userInfo.getAttribute("exp"));
		response.sendRedirect("./home.jsp");
	}

}
