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
import javax.sql.DataSource;

import kadai1303.Goods;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		String sql = "select * from Goods where goodsname like ?";
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource) ic.lookup("java:/comp/env/jdbc/pro3");
			try (Connection con = ds.getConnection();
					PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, "%" + keyword + "%");
				try (ResultSet rs = ps.executeQuery()) {

					List<Goods> goodsList = new ArrayList<>();

					while (rs.next()) {
						Goods goods = new Goods();
						goods.setGoodsCode(rs.getString("goodscode"));
						goods.setGoodsName(rs.getNString("goodsname"));
						goods.setPrice(rs.getInt("price"));
						goods.setDetail(rs.getString("detail"));
						goods.setGroupCode(rs.getInt("groupcode"));
						goods.setRecommend(rs.getBoolean("recommend"));
						goods.setGoodsImage(rs.getString("goodsimage"));

						goodsList.add(goods);
					}
					request.setAttribute("goodsList", goodsList);
				}
			}
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
