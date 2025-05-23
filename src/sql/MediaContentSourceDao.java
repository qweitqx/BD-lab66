package dao;

import model.MediaContentSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MediaContentSourceDao {
    private final Connection connection;

    public MediaContentSourceDao(Connection connection) {
        this.connection = connection;
    }

    public void create(MediaContentSource mcs) throws SQLException {
        String sql = "INSERT INTO MediaContentSource (mediaContent_id, source_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, mcs.getMediaContentId());
            stmt.setInt(2, mcs.getSourceId());
            stmt.executeUpdate();
        }
    }

    public MediaContentSource getByIds(int mediaContentId, int sourceId) throws SQLException {
        String sql = "SELECT * FROM MediaContentSource WHERE mediaContent_id = ? AND source_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, mediaContentId);
            stmt.setInt(2, sourceId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new MediaContentSource(rs.getInt("mediaContent_id"), rs.getInt("source_id"));
                }
            }
        }
        return null;
    }

    public List<MediaContentSource> getAll() throws SQLException {
        List<MediaContentSource> list = new ArrayList<>();
        String sql = "SELECT * FROM MediaContentSource";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new MediaContentSource(rs.getInt("mediaContent_id"), rs.getInt("source_id")));
            }
        }
        return list;
    }
}
