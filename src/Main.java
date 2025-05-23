import dao.UserDAOImpl;
import model.User;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bd_lab";
        String user = "root"; 
        String password = "";


        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to database!");

            UserDAOImpl userDao = new UserDAOImpl(conn);

            // Додати користувача
            User newUser = new User(0, "John", "Doe", "john@example.com", "password123");
            userDao.insert(newUser);
            System.out.println("User inserted!");

            // Отримати всіх
            for (User u : userDao.getAll()) {
                System.out.println(u.getId() + ": " + u.getFirstName() + " " + u.getLastName());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
