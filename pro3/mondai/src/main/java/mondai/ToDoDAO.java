package mondai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ToDoDAO {

    public List<ToDo> select() {
        List<ToDo> todoList = new ArrayList<>();
        String sql = "SELECT * FROM todo";

        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:/comp/env/jdbc/mondaidb"); // change the DataSource lookup path

            try (Connection connection = ds.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    ToDo todo = new ToDo(); // create new ToDo object
                    todo.setNo(resultSet.getInt("no"));
                    todo.setDeadline(resultSet.getString("deadline"));
                    todo.setSubject(resultSet.getString("subject"));
                    todo.setPriority(resultSet.getInt("priority"));
                    todo.setState(resultSet.getInt("state"));

                    todoList.add(todo); // add to list
                }
            }

        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }

        return todoList;
    }

    public void update(ToDo todo) {
        String sql = "UPDATE todo SET state = ? WHERE no = ?"; // Corrected column name to "no" from "No"

        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:/comp/env/jdbc/mondaidb"); // change the DataSource lookup path

            try (Connection connection = ds.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setInt(1, todo.getState());
                preparedStatement.setInt(2, todo.getNo());

                preparedStatement.executeUpdate();
            }

        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
    }

    public void insert(ToDo todo) {
        String sql = "INSERT INTO todo (deadline, subject, priority, state) VALUES (?, ?, ?, ?)"; // "no" is auto-increment, no need to insert it manually

        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:/comp/env/jdbc/mondaidb"); // change the DataSource lookup path

            try (Connection connection = ds.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1, todo.getDeadline());
                preparedStatement.setString(2, todo.getSubject());
                preparedStatement.setInt(3, todo.getPriority());
                preparedStatement.setInt(4, todo.getState());

                preparedStatement.executeUpdate();
            }

        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
    }
}
