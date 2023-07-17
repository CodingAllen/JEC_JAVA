package kadai1101.servlet;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kadai1101.Goods;

/**
 * Servlet implementation class KonyuServlet
 */
@WebServlet("/konyu")
public class KonyuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KonyuServlet() {
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
	    String userId = request.getParameter("userId");
	    HttpSession session = request.getSession();

	    List<Goods> list = (List<Goods>) session.getAttribute("list");
	    if (list == null) {
	        list = new ArrayList<>();
	        session.setAttribute("list", list);
	    }

	    ServletContext app = getServletContext();
	    String filePath = app.getRealPath("/WEB-INF/konyu.txt");
	    try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
	        for (Goods goods : list) {
	            String quantityString = request.getParameter("quantity_" + goods.getGoodsCode());
	            if (quantityString != null && !quantityString.isEmpty()) {
	                int quantity = Integer.parseInt(quantityString);
	                if (quantity > 0) {
	                    // TODO: Update the quantity of goods in the cart
	                    writer.println(userId + "," + goods.getGoodsCode() + "," + quantity);
	                }
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    request.getRequestDispatcher("/thankyou.html").forward(request, response);
	}


}
