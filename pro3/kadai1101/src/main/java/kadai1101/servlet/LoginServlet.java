package kadai1101.servlet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kadai1101.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, User> userMap = new HashMap<>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		ServletContext app = getServletContext();
		String filePath = app.getRealPath("/WEB-INF/user.txt");

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String record;
			while ((record = br.readLine()) != null) {
				String[] f = record.split(",");

				User user = new User(f[0], f[1], f[2], f[3]);
				userMap.put(f[0], user);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userID = request.getParameter("userid");
		String passWord = request.getParameter("password");
		User user = userMap.get(userID);
		if(user != null && user.getPassword().equals(passWord)) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			RequestDispatcher rd = request.getRequestDispatcher("/mypage.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("message", "ユーザID又はパスワードに誤りがあります");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
	}

}
