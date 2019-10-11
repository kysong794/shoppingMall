package song;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//관리자 로그인 Admin Login
@WebServlet("/admin/adminlogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mid = request.getParameter("mid");
		request.setAttribute("mid", mid);
				
		request.setAttribute("title", "AdminLogin");
		request.setAttribute("view", "AdminLogin.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		
		shoppingVo vo = new shoppingVo();
		
		vo.setMid(mid);
		vo.setMpw(mpw);
		
		System.out.println("mid:"+mid);
		System.out.println("mpw:"+mpw);
		
		int result = DAO.adminLogin(vo);
		
		if(result == 0) {
			System.out.println("로그인 실패");
			doGet(request,response);
		}
		if(result >= 1) {
			System.out.println("로그인 성공");
			HttpSession sesstion = request.getSession();
			sesstion.setAttribute("adminlogin", sesstion);
			sesstion.setAttribute("i_member", result);
			response.sendRedirect("AdminHome.jsp");
		}
		if(result == -1) {
			System.out.println("비밀번호 틀림");
			doGet(request,response);
		}
	}
}
