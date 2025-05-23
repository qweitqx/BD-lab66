package dao;

import model.AnalysisReportTag;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnalysisReportTagDao {
    private final Connection connection;

    public AnalysisReportTagDao(Connection connection) {
        this.connection = connection;
    }

    public void create(AnalysisReportTag art) throws SQLException {
        String sql = "INSERT INTO AnalysisReportTag (analysisReport_id, tag_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, art.getAnalysisReportId());
            stmt.setInt(2, art.getTagId());
            stmt.executeUpdate();
        }
    }

    public AnalysisReportTag getByIds(int analysisReportId, int tagId) throws SQLException {
        String sql = "SELECT * FROM AnalysisReportTag WHERE analysisReport_id = ? AND tag_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, analysisReportId);
            stmt.setInt(2, tagId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new AnalysisReportTag(rs.getInt("analysisReport_id"), rs.getInt("tag_id"));
                }
            }
        }
        return null;
    }

    public List<AnalysisReportTag> getAll() throws SQLException {
        List<AnalysisReportTag> list = new ArrayList<>();
        String sql = "SELECT * FROM AnalysisReportTag";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new AnalysisReportTag(rs.getInt("analysisReport_id"), rs.getInt("tag_id")));
            }
        }
        return list;
    }
}
