package dao;

import model.RolePermission;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RolePermissionDao {
    private final Connection connection;

    public RolePermissionDao(Connection connection) {
        this.connection = connection;
    }

    public void create(RolePermission rp) throws SQLException {
        String sql = "INSERT INTO RolePermission (role_id, permission_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, rp.getRoleId());
            stmt.setInt(2, rp.getPermissionId());
            stmt.executeUpdate();
        }
    }

    public RolePermission getByRoleIdPermissionId(int roleId, int permissionId) throws SQLException {
        String sql = "SELECT * FROM RolePermission WHERE role_id = ? AND permission_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, roleId);
            stmt.setInt(2, permissionId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new RolePermission(rs.getInt("role_id"), rs.getInt("permission_id"));
                }
            }
        }
        return null;
    }

    public List<RolePermission> getAll() throws SQLException {
        List<RolePermission> list = new ArrayList<>();
        String sql = "SELECT * FROM RolePermission";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new RolePermission(rs.getInt("role_id"), rs.getInt("permission_id")));
            }
        }
        return list;
    }
}
