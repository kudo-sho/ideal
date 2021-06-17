package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Admin;
import model.IdealException;

/**
 * Servlet implementation class AdminUpdateSvl
 */
@WebServlet("/AdminUpdateSvl")
public class AdminUpdateSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUpdateSvl() {
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
		response.setContentType("text/html; utf-8");

		//選択されたamdIdのパラメータを受け取りint型にしてセット
		int admId = Integer.parseInt(request.getParameter("admId"));

		try {
			//getAdminメソッドからの戻り値をリクエストに載せてフォアード
			Admin adm = Admin.getAdmin(admId);
			request.setAttribute("updateAdmInfo", adm);
			RequestDispatcher rd = request.getRequestDispatcher("/adminUpdate.jsp");
			rd.forward(request, response);

		}catch(IdealException e) {
			String msg = ((IdealException) e).getMsg();
//			System.out.println(msg);
			request.setAttribute("msg", msg);
			RequestDispatcher rd = request.getRequestDispatcher("/adminMaintenance.jsp");
			rd.forward(request, response);
		}
	}

}
