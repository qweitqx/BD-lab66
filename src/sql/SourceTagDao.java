package dao;

import model.SourceTag;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SourceTagDao {
    private final Connection connection;

    public SourceTagDao(Connection connection) {
        this.connection = connection;
    }

    public void create(SourceTag st) throws SQLException {
        String sql = "INSERT INTO SourceTag (source_id, tag_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, st.getSourceId());
            stmt.setInt(2, st.getTagId());
            stmt.executeUpdate();
        }
    }

    public SourceTag getByIds(int sourceId, int tagId) throws SQLException {
        String sql = "SELECT * FROM SourceTag WHERE source_id = ? AND tag_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, sourceId);
            stmt.setInt(2, tagId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new SourceTag(rs.getInt("source_id"), rs.getInt("tag_id"));
                }
            }
        }
        return null;
    }

    public List<SourceTag> getAll() throws SQLException {
        List<SourceTag> list = new ArrayList<>();
        String sql = "SELECT * FROM SourceTag";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new SourceTag(rs.getInt("source_id"), rs.getInt("tag_id")));
            }
        }
        return list;
    }
}
