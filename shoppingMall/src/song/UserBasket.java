package song;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//장바구니
@WebServlet("/basket")
public class UserBasket extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		shoppingVo login = (shoppingVo)session.getAttribute("userlogin");
		if(login == null) {
			response.sendRedirect("userlogin");
			return;
		}
		int i_member = (int) session.getAttribute("i_member");
		
		List<shoppingVo> list = DAO.userBasket(i_member);
		
		request.setAttribute("list", list);
		
		request.setAttribute("title", "장바구니");
		request.setAttribute("view", "UserBasket.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	
	}

}
