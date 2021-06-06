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
import model.User;

/**
 * Servlet implementation class UserLoginSvl
 */
@WebServlet("/UserLoginSvl")
public class UserLoginSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginSvl() {
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

		int id ;
		String pass ="";
		User usr = null;

		try {
			usr = new User();
			id =Integer.parseInt(request.getParameter("usrId"));
			pass = request.getParameter("password");
			//System.out.println("渡されたIDは"+ id + "パスワードは" + pass);

			//データベースへ接続し入力情報と照合
			usr = usr.login(id, pass);
//			System.out.println(usr.getUsrId());
//			System.out.println(usr.getPassword());
//			System.out.println(usr.getExp());
//			System.out.println("結果表示");

			if(usr != null) {
				System.out.println("セッションに顧客情報を追加します。");
				HttpSession userInfo = request.getSession(true);
				userInfo.setAttribute("usrId", usr.getUsrId());
				userInfo.setAttribute("usrName", usr.getUsrName());
				userInfo.setAttribute("password", usr.getPassword());
				userInfo.setAttribute("address", usr.getAddress());
				userInfo.setAttribute("phone", usr.getPhone());
				userInfo.setAttribute("mail", usr.getMail());
				userInfo.setAttribute("exp", usr.getExp());
				System.out.println("セッション情報を表示します。");
				System.out.println(userInfo.getAttribute("usrId"));
				System.out.println(userInfo.getAttribute("usrName"));
				System.out.println(userInfo.getAttribute("password"));
				System.out.println(userInfo.getAttribute("address"));
				System.out.println(userInfo.getAttribute("phone"));
				System.out.println(userInfo.getAttribute("mail"));
				System.out.println(userInfo.getAttribute("exp"));
				RequestDispatcher rd = request.getRequestDispatcher("/userIndex.jsp");
				rd.forward(request, response);
//				String userInfo = session.getId();
//				System.out.println(userInfo);
			}else {
				int i = IdealException.ERR_NO_NOT_MEMBER_EXCEPTION;
				throw new IdealException(i);

			}

		}catch(IdealException | ServletException | IOException e) {
			String msg = ((IdealException) e).getMsg();
//			System.out.println(msg);
			request.setAttribute("msg", msg);
			RequestDispatcher rd = request.getRequestDispatcher("/userLogin.jsp");
			rd.forward(request, response);


		}
	}


}
