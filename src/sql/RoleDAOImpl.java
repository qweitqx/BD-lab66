package dao;

import model.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOImpl implements RoleDAO {
    private Connection connection;

    public RoleDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addRole(Role role) throws SQLException {
        String sql = "INSERT INTO Role (name, description) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, role.getName());
            ps.setString(2, role.getDescription());
            ps.executeUpdate();
        }
    }

    @Override
    public Role getRoleById(int id) throws SQLException {
        String sql = "SELECT * FROM Role WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Role(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Role> getAllRoles() throws SQLException {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT * FROM Role";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                roles.add(new Role(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description")
                ));
            }
        }
        return roles;
    }

    @Override
    public void updateRole(Role role) throws SQLException {
        String sql = "UPDATE Role SET name = ?, description = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, role.getName());
            ps.setString(2, role.getDescription());
            ps.setInt(3, role.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void deleteRole(int id) throws SQLException {
        String sql = "DELETE FROM Role WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
