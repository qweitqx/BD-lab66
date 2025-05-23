package dao;

import model.UserRole;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDao {
    private final Connection connection;

    public UserRoleDao(Connection connection) {
        this.connection = connection;
    }

    public void create(UserRole userRole) throws SQLException {
        String sql = "INSERT INTO UserRole (user_id, role_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userRole.getUserId());
            stmt.setInt(2, userRole.getRoleId());
            stmt.executeUpdate();
        }
    }

    public UserRole getByUserIdRoleId(int userId, int roleId) throws SQLException {
        String sql = "SELECT * FROM UserRole WHERE user_id = ? AND role_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, roleId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new UserRole(rs.getInt("user_id"), rs.getInt("role_id"));
                }
            }
        }
        return null;
    }

    public List<UserRole> getAll() throws SQLException {
        List<UserRole> list = new ArrayList<>();
        String sql = "SELECT * FROM UserRole";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new UserRole(rs.getInt("user_id"), rs.getInt("role_id")));
            }
        }
        return list;
    }
}
