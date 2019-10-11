package song;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {

	public static Connection getCon() throws Exception{
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection ("jdbc:oracle:thin:@//localhost:1521/xe","shopping4","hkit2019");
		System.out.println("DB연결");
		return con;

	}
	public static void close(Connection con,PreparedStatement ps,ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//유저 회원 가입
	public static int userJoin(shoppingVo vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " insert into t_member(i_member,mid,mpw,nm,sex) "
					+" VALUES ((select nvl(max(i_member),0)+1 from t_member),?,?,?,?) ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, vo.getMid());
			ps.setString(2, vo.getMpw());
			ps.setString(3, vo.getNm());
			ps.setString(4, vo.getSex());
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,null);
		}
		return result;
	}
	
	//유저로그인
	public static int userLogin(shoppingVo vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select i_member,mpw "
					+" from t_member "
					+" where mid = ? ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getMid());
			rs=ps.executeQuery();
			while(rs.next()) {
				String mpw = vo.getMpw();
				if(mpw.equals(rs.getString("mpw"))) {
					vo.setMpw("");
					result = rs.getInt("i_member");
					vo.setI_member(rs.getString("i_member"));//비번이 맞음
				} else {
					result = -1; //비번틀림
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}
		return result;
	}
	//관리자 로그인
	public static int adminLogin(shoppingVo vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select mpw "
					+" from t_admin "
					+" where mid = ? ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getMid());
			rs=ps.executeQuery();
			while(rs.next()) {
				String mpw = vo.getMpw();
				if(mpw.equals(rs.getString("mpw"))) {
					vo.setMpw("");
					result = 1; //비번이 맞음
				} else {
					result = -1; //비번틀림
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}
		return result;
	}
	
	//상품 등록 관리자
	public static int pReg(shoppingVo vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " insert into t_product(i_product,nm,price,pic,info) "
					+" VALUES ((select nvl(max(i_product),0)+1 from t_product),?,?,?,?) ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, vo.getNm());
			ps.setString(2, vo.getPrice());
			ps.setString(3, vo.getPic());
			ps.setString(4, vo.getInfo());
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,null);
		}
		return result;
	}
	
	//관리자 상품리스트 
	public static List<shoppingVo> adminpList(){
		List<shoppingVo> list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select * from t_product "
					+" order by i_product desc ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				shoppingVo vo = new shoppingVo();
				vo.setI_product(rs.getString("i_product"));
				vo.setPic(rs.getString("pic"));
				vo.setNm(rs.getString("nm"));
				vo.setPrice(rs.getString("price"));
				vo.setQty(rs.getString("qty"));
				vo.setYn_sale(rs.getString("yn_sale"));
				vo.setInfo(rs.getString("info"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}
		return list;
	}
	//관리자 입고 리스트
	public static List<shoppingVo> pImportList(){
		List<shoppingVo> list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select a.i_pi,b.nm,(b.price*a.qty) as saleprice,b.price,a.qty "
					+" from t_product_import a"
					+" inner join t_product b on a.i_product = b.i_product "
					+" order by i_pi desc";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				shoppingVo vo = new shoppingVo();
				
				vo.setI_pi(rs.getString("i_pi"));
				vo.setNm(rs.getString("nm"));
				vo.setSaleprice(rs.getString("saleprice"));
				vo.setPrice(rs.getString("price"));
				vo.setQty(rs.getString("qty"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}	
		return list;				
	}
	
	//관리자 상품 입고
	public static int pImport(shoppingVo vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " insert into t_product_import (i_pi,i_product,qty) "
					+" VALUES ((select nvl(max(i_pi),0)+1 from t_product_import),?,?)";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, vo.getI_product());
			ps.setString(2, vo.getQty());
			result=ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,null);
		}
		return result;
	}
	
	//입고 수량을 상품리스트수량에 누적되게하는 메소드
	public static int Qty(shoppingVo vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " UPDATE t_product "
					+" set qty = qty+? "
					+" where i_product = ?";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getQty());
			ps.setString(2, vo.getI_product());
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,null);
		}
		return result;
	}
	
	//상품 리스트 유저
	public static List<shoppingVo> pList(){
		List<shoppingVo> list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select i_product,nm,price,pic,qty,yn_sale,info "
					+" from t_product ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				shoppingVo vo = new shoppingVo();
				vo.setI_product(rs.getString("i_product"));
				vo.setPic(rs.getString("pic"));
				vo.setNm(rs.getString("nm"));
				vo.setPrice(rs.getString("Price"));
				vo.setQty(rs.getString("qty"));
				vo.setYn_sale(rs.getString("yn_sale"));
				vo.setInfo(rs.getString("info"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,null);
		}
		return list;
	}
	
	//상품 정보 유저
	public static shoppingVo pDetail(String i_product) {
		shoppingVo vo = new shoppingVo();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select i_product,nm,price,pic,qty,yn_sale,info "
					+" from t_product "
					+" where i_product = ? ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, i_product);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				vo.setI_product(rs.getString("i_product"));
				vo.setNm(rs.getString("nm"));
				vo.setPrice(rs.getString("price"));
				vo.setPic(rs.getString("pic"));
				vo.setQty(rs.getString("qty"));
				vo.setYn_sale(rs.getString("yn_sale"));
				vo.setInfo(rs.getString("info"));
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,null);
		}
		return vo;
	}
	
	//장바구니에담기 유저
	public static int basket(shoppingVo vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " insert into t_basket(i_basket,i_product,i_member,qty,price) "
					+" values ((select nvl(max(i_basket),0)+1 from t_basket),?,?,?,?) ";
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, vo.getI_product());
			ps.setString(2, vo.getI_member());
			ps.setString(3, vo.getCnt()); //qty
			ps.setString(4, vo.getPrice());
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,null);
		}
		return result;
	}
	
	
	//상품 수정 관리자
	public static int pMod(shoppingVo vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " update t_product "
					+" set price = ?, pic = ? , yn_sale = ? , info = ?"
					+" where nm = ?";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, vo.getPrice());
			ps.setString(2, vo.getPic());
			ps.setString(3, vo.getYn_sale());
			ps.setString(4, vo.getInfo());
			ps.setString(5, vo.getNm());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,null);
		}
		return result;
	}
	
	//장바구니 유저
	public static List<shoppingVo> userBasket(int i_member) {
		List<shoppingVo> list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select a.i_basket,a.i_member,a.i_product,b.pic,b.nm,a.price,(a.price*a.qty)as saleprice,b.qty,a.qty as saleqty,a.r_dt,b.yn_sale "
					+" from t_basket a "
					+" INNER join t_product b on a.i_product = b.i_product "
					+" where i_member = ? and yn_sale = 1";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, i_member);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				shoppingVo vo = new shoppingVo();
				vo.setI_basket(rs.getString("i_basket"));
				vo.setI_member(rs.getString("i_member"));
				vo.setI_product(rs.getString("i_product"));
				vo.setPic(rs.getString("pic"));
				vo.setNm(rs.getString("nm"));
				vo.setPrice(rs.getString("price"));
				vo.setSaleprice(rs.getString("saleprice"));
				vo.setQty(rs.getString("qty"));
				vo.setSaleqty(rs.getString("saleqty"));
				vo.setR_dt(rs.getString("r_dt"));
				vo.setYn_sale(rs.getString("yn_sale"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}
		return list;
	}
	
	
}
