package kadai1303.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

import kadai1303.Member;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        HttpSession session = request.getSession();
	        Member member = (Member) session.getAttribute("member");

	        if (member != null) {
	            String memberId = member.getMemberId();
	            String goodsCode = request.getParameter("goodsCode");

	            try {
	                InitialContext ic = new InitialContext();
	                DataSource ds = (DataSource) ic.lookup("java:/comp/env/jdbc/pro3");
	                try (Connection con = ds.getConnection()) {
	                 
	                    String sql = "select * from Cart where memberId = ? and goodsCode = ?";
	                    try (PreparedStatement ps = con.prepareStatement(sql)) {
	                        ps.setString(1, memberId);
	                        ps.setString(2, goodsCode);
	                        try (ResultSet rs = ps.executeQuery()) {
	                            if (rs.next()) {
	                                
	                                sql = "update Cart set num = num + 1 where memberId = ? and goodsCode = ?";
	                            } else {
	                                
	                                sql = "insert into Cart (memberId, goodsCode, num) values (?, ?, 1)";
	                            }
	                        }
	                    }

	                    
	                    try (PreparedStatement ps = con.prepareStatement(sql)) {
	                        ps.setString(1, memberId);
	                        ps.setString(2, goodsCode);
	                        ps.executeUpdate();
	                    }
	                }
	            } catch (SQLException | NamingException e) {
	                e.printStackTrace();
	            }
	        }

	      
	        RequestDispatcher rd = request.getRequestDispatcher("/mypage");
            rd.forward(request, response);
	    }

}
