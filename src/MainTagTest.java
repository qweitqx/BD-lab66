import dao.TagDAOImpl;
import model.Tag;

import java.sql.*;
import java.util.List;

public class MainTagTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bd_lab";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to database!");

            TagDAOImpl tagDao = new TagDAOImpl(conn);

            // Додати новий тег
            Tag newTag = new Tag(0, "Technology");
            tagDao.addTag(newTag);
            System.out.println("Tag inserted!");

            // Отримати всі теги
            List<Tag> tags = tagDao.getAllTags();
            for (Tag tag : tags) {
                System.out.println(tag.getId() + ": " + tag.getName());
            }

            // Отримати останній тег
            if (!tags.isEmpty()) {
                Tag lastTag = tags.get(tags.size() - 1);
                Tag fetchedTag = tagDao.getTagById(lastTag.getId());

                if (fetchedTag != null) {
                    System.out.println("Fetched Tag: " + fetchedTag.getName());

                    // Оновити тег
                    fetchedTag.setName("UpdatedTag");
                    tagDao.updateTag(fetchedTag);
                    System.out.println("Tag updated!");

                    // Видалити тег
                    tagDao.deleteTag(fetchedTag.getId());
                    System.out.println("Tag deleted!");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
