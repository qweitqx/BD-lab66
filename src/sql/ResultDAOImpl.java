package dao;

import model.Result;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultDAOImpl implements ResultDAO {
    private Connection connection;

    public ResultDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addResult(Result r) throws SQLException {
        String sql = "INSERT INTO Result (name, value, createdAt, analysisReportId) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, r.getName());
            ps.setString(2, r.getValue());
            ps.setDate(3, r.getCreatedAt());
            ps.setInt(4, r.getAnalysisReportId());
            ps.executeUpdate();
        }
    }

    @Override
    public Result getResultById(int id) throws SQLException {
        String sql = "SELECT * FROM Result WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Result(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("value"),
                        rs.getDate("createdAt"),
                        rs.getInt("analysisReportId")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Result> getAllResults() throws SQLException {
        List<Result> list = new ArrayList<>();
        String sql = "SELECT * FROM Result";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Result(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("value"),
                    rs.getDate("createdAt"),
                    rs.getInt("analysisReportId")
                ));
            }
        }
        return list;
    }

    @Override
    public void updateResult(Result r) throws SQLException {
        String sql = "UPDATE Result SET name = ?, value = ?, createdAt = ?, analysisReportId = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, r.getName());
            ps.setString(2, r.getValue());
            ps.setDate(3, r.getCreatedAt());
            ps.setInt(4, r.getAnalysisReportId());
            ps.setInt(5, r.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void deleteResult(int id) throws SQLException {
        String sql = "DELETE FROM Result WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
