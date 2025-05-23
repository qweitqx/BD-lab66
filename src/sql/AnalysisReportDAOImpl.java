package dao;

import model.AnalysisReport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnalysisReportDAOImpl implements AnalysisReportDAO {
    private Connection connection;

    public AnalysisReportDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addAnalysisReport(AnalysisReport ar) throws SQLException {
        String sql = "INSERT INTO AnalysisReport (name, description, createdAt, userId) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, ar.getName());
            ps.setString(2, ar.getDescription());
            ps.setDate(3, ar.getCreatedAt());
            ps.setInt(4, ar.getUserId());
            ps.executeUpdate();
        }
    }

    @Override
    public AnalysisReport getAnalysisReportById(int id) throws SQLException {
        String sql = "SELECT * FROM AnalysisReport WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new AnalysisReport(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDate("createdAt"),
                        rs.getInt("userId")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<AnalysisReport> getAllAnalysisReports() throws SQLException {
        List<AnalysisReport> list = new ArrayList<>();
        String sql = "SELECT * FROM AnalysisReport";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new AnalysisReport(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getDate("createdAt"),
                    rs.getInt("userId")
                ));
            }
        }
        return list;
    }

    @Override
    public void updateAnalysisReport(AnalysisReport ar) throws SQLException {
        String sql = "UPDATE AnalysisReport SET name = ?, description = ?, createdAt = ?, userId = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, ar.getName());
            ps.setString(2, ar.getDescription());
            ps.setDate(3, ar.getCreatedAt());
            ps.setInt(4, ar.getUserId());
            ps.setInt(5, ar.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void deleteAnalysisReport(int id) throws SQLException {
        String sql = "DELETE FROM AnalysisReport WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
