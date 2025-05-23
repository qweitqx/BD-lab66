import dao.PermissionDAOImpl;
import model.Permission;

import java.sql.*;
import java.util.List;

public class MainPermissionTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bd_lab";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to database!");

            PermissionDAOImpl permissionDao = new PermissionDAOImpl(conn);

            // Додати новий дозвіл
            Permission newPermission = new Permission(0, "EDIT_CONTENT");
            permissionDao.addPermission(newPermission);
            System.out.println("Permission inserted!");

            // Отримати всі дозволи
            List<Permission> permissions = permissionDao.getAllPermissions();
            for (Permission p : permissions) {
                System.out.println(p.getId() + ": " + p.getName());
            }

            // Отримати дозвіл за ID (наприклад, останній)
            if (!permissions.isEmpty()) {
                int lastId = permissions.get(permissions.size() - 1).getId();
                Permission retrieved = permissionDao.getPermissionById(lastId);
                if (retrieved != null) {
                    System.out.println("Retrieved: " + retrieved.getName());

                    // Оновити
                    retrieved.setName("UPDATED_PERMISSION");
                    permissionDao.updatePermission(retrieved);
                    System.out.println("Permission updated!");

                    // Видалити
                    permissionDao.deletePermission(lastId);
                    System.out.println("Permission deleted!");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
