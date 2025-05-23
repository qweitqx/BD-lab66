package dao;

import model.Tag;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TagDAOImpl implements TagDAO {
    private Connection connection;

    public TagDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addTag(Tag tag) throws SQLException {
        String sql = "INSERT INTO Tag (name) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, tag.getName());
            ps.executeUpdate();
        }
    }

    @Override
    public Tag getTagById(int id) throws SQLException {
        String sql = "SELECT * FROM Tag WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Tag(
                        rs.getInt("id"),
                        rs.getString("name")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Tag> getAllTags() throws SQLException {
        List<Tag> tags = new ArrayList<>();
        String sql = "SELECT * FROM Tag";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                tags.add(new Tag(
                    rs.getInt("id"),
                    rs.getString("name")
                ));
            }
        }
        return tags;
    }

    @Override
    public void updateTag(Tag tag) throws SQLException {
        String sql = "UPDATE Tag SET name = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, tag.getName());
            ps.setInt(2, tag.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void deleteTag(int id) throws SQLException {
        String sql = "DELETE FROM Tag WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
