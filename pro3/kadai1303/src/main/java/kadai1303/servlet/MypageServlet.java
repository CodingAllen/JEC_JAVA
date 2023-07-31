package kadai1303.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import kadai1303.Cart;
import kadai1303.Member;

/**
 * Servlet implementation class MypageServlet
 */
@WebServlet("/mypage")
public class MypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MypageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");

        if (member != null) {
            String memberId = member.getMemberId();
         
            String sql = "SELECT c.memberId, c.goodsCode, c.num, g.goodsName, g.price, g.goodsImage FROM Cart c JOIN Goods g ON c.goodsCode = g.goodsCode WHERE c.memberId = ?";

            try {
                InitialContext ic = new InitialContext();
                DataSource ds = (DataSource) ic.lookup("java:/comp/env/jdbc/pro3");
                try (Connection con = ds.getConnection();
                    PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, memberId);
                    try (ResultSet rs = ps.executeQuery()) {
                        List<Cart> cartList = new ArrayList<>();
                        while (rs.next()) {
                            Cart cart = new Cart();
                            cart.setMemberId(rs.getString("memberId"));
                            cart.setGoodsCode(rs.getString("goodsCode"));
                            cart.setNum(rs.getInt("num"));
                            cart.setGoodsName(rs.getString("goodsName"));
                            cart.setPrice(rs.getInt("price"));
                            cart.setGoodsImage(rs.getString("goodsImage"));
                            cartList.add(cart);
                        }
                        request.setAttribute("cartList", cartList);
                    }
                }
            } catch (SQLException | NamingException e) {
                e.printStackTrace();
            }
            RequestDispatcher rd = request.getRequestDispatcher("/mypage.jsp");
            rd.forward(request, response);
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
