package mondai.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mondai.ToDo;
import mondai.ToDoDAO;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final ToDoDAO todoDAO = new ToDoDAO();

    public UpdateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int no = Integer.parseInt(request.getParameter("no"));
        int state = Integer.parseInt(request.getParameter("state"));

        ToDo todo = new ToDo();
        todo.setNo(no);
        todo.setState(state);

        todoDAO.update(todo);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/todo");
        dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
