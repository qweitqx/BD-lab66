package model;
import java.sql.Date;

public class Result {
    private int id;
    private String name;
    private String value;
    private Date createdAt;
    private int analysisReportId;

    public Result() {}

    public Result(int id, String name, String value, Date createdAt, int analysisReportId) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.createdAt = createdAt;
        this.analysisReportId = analysisReportId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public int getAnalysisReportId() { return analysisReportId; }
    public void setAnalysisReportId(int analysisReportId) { this.analysisReportId = analysisReportId; }
}
