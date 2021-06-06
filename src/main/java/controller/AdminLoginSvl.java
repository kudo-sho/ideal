package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Admin;
import model.IdealException;

/**
 * Servlet implementation class AdminLoginSvl
 */
@WebServlet("/AdminLoginSvl")
public class AdminLoginSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginSvl() {
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
	 *
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String name ="";
		String pass ="";
		Admin adm = null;

		try {
			adm = new Admin();
			name = request.getParameter("admName");
			pass = request.getParameter("password");
			//System.out.println("渡された名前は"+ name + "パスワードは" + pass);
			adm = adm.login(name, pass);
//			System.out.println(adm.getAdmName());
//			System.out.println(adm.getPassword());
//			System.out.println(adm.getExp());
//			System.out.println("結果表示");
			if(adm != null) {
//				System.out.println("admには値が入っています");
				HttpSession session = request.getSession(true);
				session.setAttribute("adminInfo", adm.getAdmName());
//				session.setAttribute("pass", adm.getPassword());
//				session.setAttribute("exp", adm.getExp());
//				System.out.println(session.getAttribute("adminInfo"));;
				RequestDispatcher rd = request.getRequestDispatcher("/adminIndex.jsp");
				rd.forward(request, response);
//				String adminInfo = session.getId();
//				System.out.println(adminInfo);
			}else {
				int i = IdealException.ERR_NO_ADMIN_EXCEPTION;
				throw new IdealException(i);

			}

		}catch(IdealException | ServletException | IOException e) {
			String msg = ((IdealException) e).getMsg();
//			System.out.println(msg);
			request.setAttribute("msg", msg);
			RequestDispatcher rd = request.getRequestDispatcher("/adminLogin.jsp");
			rd.forward(request, response);


		}
	}


}
