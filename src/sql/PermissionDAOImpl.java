package dao;

import model.Permission;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PermissionDAOImpl implements PermissionDAO {
    private Connection connection;

    public PermissionDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addPermission(Permission permission) throws SQLException {
        String sql = "INSERT INTO Permission (name) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, permission.getName());
            ps.executeUpdate();
        }
    }

    @Override
    public Permission getPermissionById(int id) throws SQLException {
        String sql = "SELECT * FROM Permission WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Permission(
                        rs.getInt("id"),
                        rs.getString("name")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Permission> getAllPermissions() throws SQLException {
        List<Permission> permissions = new ArrayList<>();
        String sql = "SELECT * FROM Permission";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                permissions.add(new Permission(
                    rs.getInt("id"),
                    rs.getString("name")
                ));
            }
        }
        return permissions;
    }

    @Override
    public void updatePermission(Permission permission) throws SQLException {
        String sql = "UPDATE Permission SET name = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, permission.getName());
            ps.setInt(2, permission.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void deletePermission(int id) throws SQLException {
        String sql = "DELETE FROM Permission WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
