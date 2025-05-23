package dao;

import model.Source;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SourceDAOImpl implements SourceDAO {
    private Connection connection;

    public SourceDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addSource(Source source) throws SQLException {
        String sql = "INSERT INTO Source (name, url) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, source.getName());
            ps.setString(2, source.getUrl());
            ps.executeUpdate();
        }
    }

    @Override
    public Source getSourceById(int id) throws SQLException {
        String sql = "SELECT * FROM Source WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Source(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("url")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Source> getAllSources() throws SQLException {
        List<Source> sources = new ArrayList<>();
        String sql = "SELECT * FROM Source";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                sources.add(new Source(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("url")
                ));
            }
        }
        return sources;
    }

    @Override
    public void updateSource(Source source) throws SQLException {
        String sql = "UPDATE Source SET name = ?, url = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, source.getName());
            ps.setString(2, source.getUrl());
            ps.setInt(3, source.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void deleteSource(int id) throws SQLException {
        String sql = "DELETE FROM Source WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
