import dao.UserRoleDao;
import model.UserRole;

import java.sql.*;
import java.util.List;

public class MainUserRoleTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bd_lab";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to database!");

            UserRoleDao userRoleDao = new UserRoleDao(conn);

            // Створення нового зв’язку користувача і ролі
            UserRole newUserRole = new UserRole(1, 2); // user_id = 1, role_id = 2
            userRoleDao.create(newUserRole);
            System.out.println("UserRole inserted!");

            // Отримання зв’язку за user_id і role_id
            UserRole fetched = userRoleDao.getByUserIdRoleId(1, 2);
            if (fetched != null) {
                System.out.println("Fetched UserRole: user_id=" + fetched.getUserId() + ", role_id=" + fetched.getRoleId());
            } else {
                System.out.println("UserRole not found.");
            }

            // Виведення всіх зв’язків
            List<UserRole> allUserRoles = userRoleDao.getAll();
            for (UserRole ur : allUserRoles) {
                System.out.println("UserRole: user_id=" + ur.getUserId() + ", role_id=" + ur.getRoleId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
