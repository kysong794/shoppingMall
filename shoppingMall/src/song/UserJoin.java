package song;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//유저 조인(회원가입)
@WebServlet("/userjoin")
public class UserJoin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		request.setAttribute("title", "UserJoin");
		request.setAttribute("view", "UserJoin.jsp");
		request.getRequestDispatcher("logintemp.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		String nm = request.getParameter("nm");
		String sex = request.getParameter("sex");
		
		shoppingVo vo = new shoppingVo();
		
		vo.setMid(mid);
		vo.setMpw(mpw);
		vo.setNm(nm);
		vo.setSex(sex);
		
		int result = DAO.userJoin(vo);
		
		if(result == 0) {
			System.out.println("회원가입 실패");
			doGet(request,response);
		}
		if(result != 0) {
			System.out.println("회원가입 성공");
			response.sendRedirect("userlogin");
		}
		
	}

}
