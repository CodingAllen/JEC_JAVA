package kadai1101.servlet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kadai1101.Goods;

/**
 * Servlet implementation class GoodsListServlet
 */
@WebServlet("/goodslist")
public class GoodsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private List<Goods> goodsList = new ArrayList<>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init() throws ServletException{
    	ServletContext app = getServletContext();
    	String filePath = app.getRealPath("/WEB-INF/goods.txt");
    	
    	try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
    		String record;
    		while((record = br.readLine()) != null) {
    			String[] items = record.split(",");
    			
    			Goods goods = new Goods();
    			goods.setGoodsCode(items[0]);
    			goods.setGoodsName(items[1]);
    			goods.setPrice(Integer.parseInt(items[2]));
    			goods.setComment(items[3]);
    			goods.setImageFile(items[4]);
    			goodsList.add(goods);
    		}
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String priceParam = request.getParameter("price");
	    int price = Integer.MAX_VALUE;  

	    if (priceParam != null && !priceParam.isEmpty()) {
	        try {
	            price = Integer.parseInt(priceParam);
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	        }
	    }

	    List<Goods> list = new ArrayList<>();
	    for(Goods goods : goodsList) {
	        if(goods.getPrice() <= price) {
	            list.add(goods);
	        }
	    }
	    
	    if(price == Integer.MAX_VALUE){
	        list = goodsList;
	    }

	    request.setAttribute("list", list);
	    RequestDispatcher rd = request.getRequestDispatcher("mypage.jsp");
	    rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
