import dao.RolePermissionDao;
import model.RolePermission;

import java.sql.*;
import java.util.List;

public class MainRolePermissionTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bd_lab";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to database!");

            RolePermissionDao rpDao = new RolePermissionDao(conn);

            // Додати зв'язок роль-дозвіл (role_id = 1, permission_id = 1 повинні існувати)
            RolePermission newRp = new RolePermission(1, 1);
            rpDao.create(newRp);
            System.out.println("RolePermission inserted!");

            // Отримати конкретний зв'язок
            RolePermission fetchedRp = rpDao.getByRoleIdPermissionId(1, 1);
            if (fetchedRp != null) {
                System.out.println("Fetched: role_id = " + fetchedRp.getRoleId() + ", permission_id = " + fetchedRp.getPermissionId());
            } else {
                System.out.println("No such RolePermission found.");
            }

            // Отримати всі зв'язки
            List<RolePermission> all = rpDao.getAll();
            for (RolePermission rp : all) {
                System.out.println("Role ID: " + rp.getRoleId() + ", Permission ID: " + rp.getPermissionId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
