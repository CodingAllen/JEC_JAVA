package kadai1201.servlet;

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

import kadai1201.Kamoku;
import kadai1201.Student;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
	    List<Student> students = new ArrayList<>();
	    List<Kamoku> kamokus = new ArrayList<>();
		ServletContext app = getServletContext();
		String filePath1 = app.getRealPath("/WEB-INF/student.txt");
		String filePath2 = app.getRealPath("/WEB-INF/kamoku.txt");

		try (BufferedReader br = new BufferedReader(new FileReader(filePath1))) {
			String record;
			while ((record = br.readLine()) != null) {
				String[] items = record.split(",");

				Student student = new Student();
				student.setGakuseiNo(items[0]);
				student.setGakuseiName(items[1]);
				students.add(student);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try (BufferedReader br = new BufferedReader(new FileReader(filePath2))) {
			String record;
			while ((record = br.readLine()) != null) {
				String[] items = record.split(",");

				Kamoku kamoku = new Kamoku();
				kamoku.setKamokuNo(items[0]);
				kamoku.setKamokuName(items[1]);
				kamokus.add(kamoku);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		app.setAttribute("students", students);
		app.setAttribute("kamokus", kamokus);
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/nyuryoku.jsp");
        dispatcher.forward(request, response);
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
