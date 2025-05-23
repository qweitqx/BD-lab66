package dao;

import model.MediaContent;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MediaContentDAOImpl implements MediaContentDAO {
    private Connection connection;

    public MediaContentDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addMediaContent(MediaContent mc) throws SQLException {
        String sql = "INSERT INTO MediaContent (title, description, body, content_type, created_at, user_Id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, mc.getTitle());
            ps.setString(2, mc.getDescription());
            ps.setString(3, mc.getBody());
            ps.setString(4, mc.getContentType());
            ps.setDate(5, mc.getCreatedAt());
            ps.setInt(6, mc.getUserId());
            ps.executeUpdate();
        }
    }

    @Override
    public MediaContent getMediaContentById(int id) throws SQLException {
        String sql = "SELECT * FROM MediaContent WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new MediaContent(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("body"),
                        rs.getString("content_type"),
                        rs.getDate("created_at"),
                        rs.getInt("user_Id")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<MediaContent> getAllMediaContents() throws SQLException {
        List<MediaContent> list = new ArrayList<>();
        String sql = "SELECT * FROM MediaContent";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new MediaContent(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("body"),
                    rs.getString("content_type"),
                    rs.getDate("created_at"),
                    rs.getInt("user_Id")
                ));
            }
        }
        return list;
    }

    @Override
    public void updateMediaContent(MediaContent mc) throws SQLException {
        String sql = "UPDATE MediaContent SET title = ?, description = ?, body = ?, content_type = ?, created_at = ?, user_Id = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, mc.getTitle());
            ps.setString(2, mc.getDescription());
            ps.setString(3, mc.getBody());
            ps.setString(4, mc.getContentType());
            ps.setDate(5, mc.getCreatedAt());
            ps.setInt(6, mc.getUserId());
            ps.setInt(7, mc.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void deleteMediaContent(int id) throws SQLException {
        String sql = "DELETE FROM MediaContent WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
