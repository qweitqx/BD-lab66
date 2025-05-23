package model;

public class AnalysisReportTag {
    private int analysisReportId;
    private int tagId;

    public AnalysisReportTag() {}

    public AnalysisReportTag(int analysisReportId, int tagId) {
        this.analysisReportId = analysisReportId;
        this.tagId = tagId;
    }

    public int getAnalysisReportId() { return analysisReportId; }
    public void setAnalysisReportId(int analysisReportId) { this.analysisReportId = analysisReportId; }

    public int getTagId() { return tagId; }
    public void setTagId(int tagId) { this.tagId = tagId; }

    @Override
    public String toString() {
        return "AnalysisReportTag{analysisReportId=" + analysisReportId +
                ", tagId=" + tagId +
                '}';
    }
}
