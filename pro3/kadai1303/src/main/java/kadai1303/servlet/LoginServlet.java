package kadai1303.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import kadai1303.Member;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	/**
	 * まずHttpServletRequestのgetParameterメソッドを使用して、
	 * フロントエンドから提出されたユーザーID（memberId）とパスワード（password）を取得します。
	
	次に、MySQLのJDBCドライバをロードし、MySQLデータベースへの接続を確立します。
	その後、Memberテーブルでユーザーが入力したIDとパスワードと一致するレコードが存在するかどうか
	を問い合わせるためのプリコンパイルされたSQL文を作成します。
	
	このSQL文は "SELECT * FROM Member WHERE memberId = ? AND password = ?"で、
	?はプレースホルダーで、ユーザーの実際の入力を代替します。
	
	ユーザーが入力したmemberIdとpasswordをそれぞれこのプリコンパイルされたSQL文の
	第一および第二のパラメータ位置に設定し、クエリを実行します。
	
	クエリが実行され、結果セットが返されます。結果セットにレコードが存在する場合、
	それはユーザーが入力したアカウントとパスワードがデータベースで一致するレコードを見つけたことを意味します。
	その場合、プログラムはMemberオブジェクトを作成し、その属性値をクエリで取得したレコードの各フィールドの値に設定し、
	そのオブジェクトをセッションに保存します。
	
	結果セットにレコードが存在しない場合、それはユーザーが入力したアカウントとパスワードがデータベースで一致する
	レコードを見つけられなかったことを意味します。その場合、プログラムはエラーメッセージを設定し、
	リクエストをログインページにリダイレクトします。
	 * */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String memberId = req.getParameter("memberId");
		String password = req.getParameter("password");

		try {
			InitialContext ic = new InitialContext();
			DataSource dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/pro3");
			try (Connection conn = dataSource.getConnection();

					PreparedStatement ps = conn
							.prepareStatement("SELECT * FROM Member WHERE memberId = ? AND password = ?");) {
				ps.setString(1, memberId);
				ps.setString(2, password);
				try (ResultSet rs = ps.executeQuery()) {

					if (rs.next()) {
						Member member = new Member();
						member.setMemberId(rs.getString("memberId"));
						member.setMemberName(rs.getString("memberName"));
						member.setZipcode(rs.getString("zipcode"));
						member.setAddress(rs.getString("address"));
						member.setTel(rs.getString("tel"));
						member.setPassword(rs.getString("password"));

						HttpSession session = req.getSession();
						session.setAttribute("member", member);
						req.getRequestDispatcher("index.jsp").forward(req, resp);
					} else {
						req.setAttribute("message", "会員ID又はパスワードの誤りです");
						req.getRequestDispatcher("login.jsp").forward(req, resp);
					}
				}
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}
}