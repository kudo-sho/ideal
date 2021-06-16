package controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AdminMaintenance;
import model.IdealException;

/**
 * Servlet implementation class AdmInsertSvl
 */
@WebServlet("/AdmInsertSvl")
public class AdmInsertSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdmInsertSvl() {
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

		//パラメータを受け取る
		String admName = request.getParameter("admName");
		String password = request.getParameter("password");
		String exp = request.getParameter("exp");

		//パラメータをセットしてinsertメソッドに渡す
		AdminMaintenance am = new AdminMaintenance();
		am.setAdminName(admName);
		am.setPassword(password);
		am.setAdminExp(exp);

		try {
			//insertメソッドからの戻り値をセッションに載せてフォアード
			am = AdminMaintenance.insert(am);
			request.setAttribute("insertAdmName", am.getAdminName());
			request.setAttribute("insertAdmPassword", am.getPassword());
			request.setAttribute("insertAdmExp", am.getAdminExp());
			RequestDispatcher rd = request.getRequestDispatcher("/adminInsertCompletion.jsp");
			rd.forward(request, response);

		}catch(IdealException | NamingException  e) {
			String msg = ((IdealException) e).getMsg();
//			System.out.println(msg);
			request.setAttribute("msg", msg);
			RequestDispatcher rd = request.getRequestDispatcher("/adminInset.jsp");
			rd.forward(request, response);


		}





	}

}
