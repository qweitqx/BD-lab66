package dao;

import model.Result;
import java.util.List;

public interface ResultDAO {
    void addResult(Result result) throws Exception;
    Result getResultById(int id) throws Exception;
    List<Result> getAllResults() throws Exception;
    void updateResult(Result result) throws Exception;
    void deleteResult(int id) throws Exception;
}
