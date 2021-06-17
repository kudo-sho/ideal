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
 * Servlet implementation class AdminInsertSvl
 */
@WebServlet("/AdminInsertSvl")
public class AdminInsertSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminInsertSvl() {
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

		//入力されたパラメータを受け取る
		String admName = request.getParameter("admName");
		String password = request.getParameter("password");
		String exp = request.getParameter("exp");

		//パラメータをセットしてinsertメソッドに渡す
		Admin adm = new Admin();
		adm.setAdmName(admName);
		adm.setPassword(password);
		adm.setExp(exp);

		try {
			//insertメソッドからの戻り値をリクエストに載せてフォアード
			adm = Admin.insert(adm);
			request.setAttribute("insertAdmInfo", adm);
			RequestDispatcher rd = request.getRequestDispatcher("/adminInsertCompletion.jsp");
			rd.forward(request, response);

		}catch(IdealException e) {
			String msg = ((IdealException) e).getMsg();
//			System.out.println(msg);
			request.setAttribute("msg", msg);
			RequestDispatcher rd = request.getRequestDispatcher("/adminInsert.jsp");
			rd.forward(request, response);


		}





	}

}
