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

@WebServlet("/insert")
public class InsertServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ToDoDAO todoDAO = new ToDoDAO();

    public InsertServlet() {
        super();
    }

    // Forward request to insert.jsp
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/insert.jsp");
        dispatcher.forward(request, response);
    }

    // Process form submission
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");

    	
        String deadline = request.getParameter("deadline");
        String subject = request.getParameter("subject");
        String priorityStr = request.getParameter("priority");
        String stateStr = request.getParameter("state");

        if (deadline != null && subject != null && priorityStr != null && stateStr != null) {
            int priority = Integer.parseInt(priorityStr);
            int state = Integer.parseInt(stateStr);

            ToDo todo = new ToDo();
            todo.setDeadline(deadline);
            todo.setSubject(subject);
            todo.setPriority(priority);
            todo.setState(state);

            todoDAO.insert(todo);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/todo");
        dispatcher.forward(request, response);

    }
}

