import dao.SourceDAOImpl;
import model.Source;

import java.sql.*;
import java.util.List;

public class MainSourceTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bd_lab";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to database!");

            SourceDAOImpl sourceDao = new SourceDAOImpl(conn);

            // Додати нове джерело
            Source newSource = new Source(0, "Example Source", "https://example.com");
            sourceDao.addSource(newSource);
            System.out.println("Source inserted!");

            // Отримати всі джерела
            List<Source> sources = sourceDao.getAllSources();
            for (Source s : sources) {
                System.out.println(s.getId() + ": " + s.getName() + " (" + s.getUrl() + ")");
            }

            // Отримати останнє додане джерело
            if (!sources.isEmpty()) {
                Source last = sources.get(sources.size() - 1);
                Source fetched = sourceDao.getSourceById(last.getId());

                if (fetched != null) {
                    System.out.println("Fetched: " + fetched.getName());

                    // Оновити
                    fetched.setName("Updated Source");
                    fetched.setUrl("https://updated.com");
                    sourceDao.updateSource(fetched);
                    System.out.println("Source updated!");

                    // Видалити
                    sourceDao.deleteSource(fetched.getId());
                    System.out.println("Source deleted!");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
