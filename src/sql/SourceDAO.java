package dao;

import model.Source;
import java.util.List;

public interface SourceDAO {
    void addSource(Source source) throws Exception;
    Source getSourceById(int id) throws Exception;
    List<Source> getAllSources() throws Exception;
    void updateSource(Source source) throws Exception;
    void deleteSource(int id) throws Exception;
}
