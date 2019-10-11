package song;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//상품 입고 관리자
@WebServlet("/admin/pImport")
public class AdminPImport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<shoppingVo> list = DAO.adminpList();
		List<shoppingVo> pImportList = DAO.pImportList();
		
		request.setAttribute("list", list);
		request.setAttribute("pImportList", pImportList);
		
		request.setAttribute("tilte", "상품입고");
		request.setAttribute("view", "pImport.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String i_product = request.getParameter("i_product");
		String qty = request.getParameter("qty");
		
		shoppingVo vo = new shoppingVo();
		
		vo.setI_product(i_product);
		vo.setQty(qty);
		
		int result = DAO.pImport(vo);
		
		if(result == 0) {
			System.out.println("상품입고 실패");
			doGet(request,response);
		}
		if(result != 0) {
			System.out.println("상품입고 성공");
			DAO.Qty(vo);
			response.sendRedirect("pImport");
		}
		
	}

}
