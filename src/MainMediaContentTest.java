import dao.MediaContentDAOImpl;
import model.MediaContent;

import java.sql.*;
import java.util.List;

public class MainMediaContentTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bd_lab";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to database!");

            MediaContentDAOImpl mediaDao = new MediaContentDAOImpl(conn);

            // Додати новий медіаконтент
            MediaContent newMedia = new MediaContent(
                0,
                "Test Title",
                "Test Description",
                "This is the body of the media content.",
                "text/plain",
                new java.sql.Date(System.currentTimeMillis()),
                1 // userId (має бути існуючий користувач у таблиці User)
            );

            mediaDao.addMediaContent(newMedia);
            System.out.println("Media content inserted!");

            // Отримати всі медіаконтенти
            List<MediaContent> mediaList = mediaDao.getAllMediaContents();
            for (MediaContent mc : mediaList) {
                System.out.println(mc.getId() + ": " + mc.getTitle() + " - " + mc.getContentType());
            }

            // Отримати медіаконтент за ID (наприклад, останній доданий)
            if (!mediaList.isEmpty()) {
                int lastId = mediaList.get(mediaList.size() - 1).getId();
                MediaContent retrieved = mediaDao.getMediaContentById(lastId);
                if (retrieved != null) {
                    System.out.println("Retrieved: " + retrieved.getTitle());
                }

                // Оновити
                retrieved.setTitle("Updated Title");
                mediaDao.updateMediaContent(retrieved);
                System.out.println("Media content updated!");

                // Видалити
                mediaDao.deleteMediaContent(lastId);
                System.out.println("Media content deleted!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
