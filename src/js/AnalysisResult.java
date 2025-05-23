package model;

import java.sql.Date;

public class AnalysisResult {
    private int id;
    private int analysisReportId;
    private String resultText;
    private Date createdAt;

    public AnalysisResult() {}

    public AnalysisResult(int id, int analysisReportId, String resultText, Date createdAt) {
        this.id = id;
        this.analysisReportId = analysisReportId;
        this.resultText = resultText;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getAnalysisReportId() { return analysisReportId; }
    public void setAnalysisReportId(int analysisReportId) { this.analysisReportId = analysisReportId; }

    public String getResultText() { return resultText; }
    public void setResultText(String resultText) { this.resultText = resultText; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "AnalysisResult{id=" + id +
                ", analysisReportId=" + analysisReportId +
                ", createdAt=" + createdAt +
                '}';
    }
}
