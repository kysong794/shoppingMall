package song;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//상품 정보
@WebServlet("/pdetail")
public class pDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String i_product = request.getParameter("i_product");
		shoppingVo vo = DAO.pDetail(i_product);
		
		request.setAttribute("vo", vo);
		
		request.setAttribute("title", "상품 정보");
		request.setAttribute("view", "pDetail.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String basket = request.getParameter("basket");
		String purchase = request.getParameter("purchase");
		
		System.out.println("basket:"+basket);
		System.out.println("purchase:"+purchase);
		
		HttpSession session = request.getSession();
		String i_member = (String) session.getAttribute("i_member");
		
		shoppingVo vo = new shoppingVo();
		
		String i_product = request.getParameter("i_product");
		String cnt = request.getParameter("cnt");
		String price = request.getParameter("price");
		
		vo.setI_product(i_product);
		vo.setI_member(i_member);
		vo.setPrice(price);
		vo.setCnt(cnt); //qty
		
		if(basket !=null) {
		int result = DAO.basket(vo);
		
		if(result == 0) {
			System.out.println("장바구니에 담기 실패");
			doGet(request,response);
		}
		if(result != 0) {
			System.out.println("장바구니에 담기 성공");
			response.sendRedirect("pList");
		}
		}
		
		if(purchase != null) {
			
		}
	}

}
