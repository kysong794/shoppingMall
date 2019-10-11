package song;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//상품 등록
@WebServlet("/admin/pReg")
public class pReg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		
		request.setAttribute("title", "상품등록");
		request.setAttribute("view", "pReg.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String nm = request.getParameter("nm");
		String price = request.getParameter("price");
		String pic = request.getParameter("pic");
		String info = request.getParameter("info");
		
		System.out.println("nm :"+nm);
		System.out.println("price :"+price);
		System.out.println("pic :"+pic);
		System.out.println("info :"+info);
		
		shoppingVo vo = new shoppingVo();
		
		vo.setNm(nm);
		vo.setPrice(price);
		vo.setPic(pic);
		vo.setInfo(info);
		
		int result = DAO.pReg(vo);
		
		if(result == 0) {
			System.out.println("상품 등록 실패");
			doGet(request,response);
		}
		if(result != 0 ) {
			System.out.println("상품 등록 성공");
			response.sendRedirect("pReg");
		}
	}

}
