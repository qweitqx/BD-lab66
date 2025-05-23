package dao;

import model.MediaContentTag;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MediaContentTagDao {
    private final Connection connection;

    public MediaContentTagDao(Connection connection) {
        this.connection = connection;
    }

    public void create(MediaContentTag mct) throws SQLException {
        String sql = "INSERT INTO MediaContentTag (mediaContent_id, tag_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, mct.getMediaContentId());
            stmt.setInt(2, mct.getTagId());
            stmt.executeUpdate();
        }
    }

    public MediaContentTag getByIds(int mediaContentId, int tagId) throws SQLException {
        String sql = "SELECT * FROM MediaContentTag WHERE mediaContent_id = ? AND tag_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, mediaContentId);
            stmt.setInt(2, tagId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new MediaContentTag(rs.getInt("mediaContent_id"), rs.getInt("tag_id"));
                }
            }
        }
        return null;
    }

    public List<MediaContentTag> getAll() throws SQLException {
        List<MediaContentTag> list = new ArrayList<>();
        String sql = "SELECT * FROM MediaContentTag";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new MediaContentTag(rs.getInt("mediaContent_id"), rs.getInt("tag_id")));
            }
        }
        return list;
    }
}
