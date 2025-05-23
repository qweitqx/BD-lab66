package dao;

import model.AnalysisReport;
import java.util.List;

public interface AnalysisReportDAO {
    void addAnalysisReport(AnalysisReport ar) throws Exception;
    AnalysisReport getAnalysisReportById(int id) throws Exception;
    List<AnalysisReport> getAllAnalysisReports() throws Exception;
    void updateAnalysisReport(AnalysisReport ar) throws Exception;
    void deleteAnalysisReport(int id) throws Exception;
}
