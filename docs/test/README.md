# 🧪 Тестування працездатності системи

## ✅ Засоби тестування

Для тестування функціональності DAO-рівня було використано:

* **Java 17**
* **JDBC** (з'єднання з MySQL)
* **MySQL** — для зберігання та перевірки даних
* **Власні Java main-класи** як тестові сценарії (`MainUserRoleTest`, `MainMediaContentTest` тощо)
* Компіляція і запуск через **PowerShell / Command Prompt**

---

## 🧾 Структура тестів

Кожен тест реалізований у вигляді окремого класу `Main<ClassName>Test.java`, який:

* Встановлює з'єднання з базою даних
* Викликає методи DAO-реалізацій (наприклад, `addMediaContent`, `getById`)
* Виводить результати виконання операцій у консоль

---

## 📁 Приклад вихідного коду тесту

**`MainMediaContentTest.java`**

```java
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

```

**`MediaContentDAOImpl.java`**

```java
package dao;

import model.MediaContent;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MediaContentDAOImpl implements MediaContentDAO {
    private Connection connection;

    public MediaContentDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addMediaContent(MediaContent mc) throws SQLException {
        String sql = "INSERT INTO MediaContent (title, description, body, content_type, created_at, user_Id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, mc.getTitle());
            ps.setString(2, mc.getDescription());
            ps.setString(3, mc.getBody());
            ps.setString(4, mc.getContentType());
            ps.setDate(5, mc.getCreatedAt());
            ps.setInt(6, mc.getUserId());
            ps.executeUpdate();
        }
    }

    @Override
    public MediaContent getMediaContentById(int id) throws SQLException {
        String sql = "SELECT * FROM MediaContent WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new MediaContent(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("body"),
                        rs.getString("content_type"),
                        rs.getDate("created_at"),
                        rs.getInt("user_Id")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<MediaContent> getAllMediaContents() throws SQLException {
        List<MediaContent> list = new ArrayList<>();
        String sql = "SELECT * FROM MediaContent";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new MediaContent(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("body"),
                    rs.getString("content_type"),
                    rs.getDate("created_at"),
                    rs.getInt("user_Id")
                ));
            }
        }
        return list;
    }

    @Override
    public void updateMediaContent(MediaContent mc) throws SQLException {
        String sql = "UPDATE MediaContent SET title = ?, description = ?, body = ?, content_type = ?, created_at = ?, user_Id = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, mc.getTitle());
            ps.setString(2, mc.getDescription());
            ps.setString(3, mc.getBody());
            ps.setString(4, mc.getContentType());
            ps.setDate(5, mc.getCreatedAt());
            ps.setInt(6, mc.getUserId());
            ps.setInt(7, mc.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void deleteMediaContent(int id) throws SQLException {
        String sql = "DELETE FROM MediaContent WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
```
---

## 📤 Результати тестування

![зображення](https://github.com/user-attachments/assets/9271f91e-dbdc-4369-a504-01136a2ccbb7)

![зображення](https://github.com/user-attachments/assets/1d4f47be-f0d2-4755-9cbb-410d7e4a4ae1)
