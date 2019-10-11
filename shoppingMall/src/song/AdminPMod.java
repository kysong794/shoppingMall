package song;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//상품 수정 관리자
@WebServlet("/admin/pmod")
public class AdminPMod extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String nm = request.getParameter("nm");
		String price = request.getParameter("price");
		String pic = request.getParameter("pic");
		String yn_sale = request.getParameter("yn_sale");
		String info = request.getParameter("info");
		
	
		request.setAttribute("nm", nm);
		request.setAttribute("price", price);
		request.setAttribute("pic", pic);
		request.setAttribute("vn_sale", yn_sale);
		request.setAttribute("info", info);
		
		request.setAttribute("title", "상품수정");
		request.setAttribute("view", "pMod.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String nm = request.getParameter("nm");
		String price = request.getParameter("price");
		String pic = request.getParameter("pic");
		String yn_sale = request.getParameter("yn_sale");
		String info = request.getParameter("info");
		
		shoppingVo vo = new shoppingVo();
		
		vo.setNm(nm);
		vo.setPrice(price);
		vo.setPic(pic);
		vo.setYn_sale(yn_sale);
		vo.setInfo(info);
		
		int result = DAO.pMod(vo);
		
		if(result == 0) {
			System.out.println("수정 실패");
			doGet(request,response);
		}if(result != 0) {
			System.out.println("수정 성공");
			response.sendRedirect("pList");
		}
	
	}

}
