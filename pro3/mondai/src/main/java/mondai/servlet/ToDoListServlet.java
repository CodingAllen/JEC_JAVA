package mondai.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mondai.ToDo;
import mondai.ToDoDAO;

@WebServlet("/todo")
public class ToDoListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final ToDoDAO todoDAO = new ToDoDAO();

    public ToDoListServlet() {
        super();
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ToDo> todos = todoDAO.select();
        request.setAttribute("todos", todos);
        
        /**
         * servletで高中低を判断する
         *    Map<Integer, String> priorityMap = new HashMap<>();
        priorityMap.put(1, "低");
        priorityMap.put(2, "中");
        priorityMap.put(3, "高");
        request.setAttribute("priorityMap", priorityMap);
         * 
         * */
        

        RequestDispatcher dispatcher = request.getRequestDispatcher("/list.jsp");
        dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
