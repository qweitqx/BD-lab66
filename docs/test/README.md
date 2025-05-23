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

**`MainTagTest.java`**

```java
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

```

**`TagDAOImpl.java`**

```java
package dao;

import model.Tag;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TagDAOImpl implements TagDAO {
    private Connection connection;

    public TagDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addTag(Tag tag) throws SQLException {
        String sql = "INSERT INTO Tag (name) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, tag.getName());
            ps.executeUpdate();
        }
    }

    @Override
    public Tag getTagById(int id) throws SQLException {
        String sql = "SELECT * FROM Tag WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Tag(
                        rs.getInt("id"),
                        rs.getString("name")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Tag> getAllTags() throws SQLException {
        List<Tag> tags = new ArrayList<>();
        String sql = "SELECT * FROM Tag";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                tags.add(new Tag(
                    rs.getInt("id"),
                    rs.getString("name")
                ));
            }
        }
        return tags;
    }

    @Override
    public void updateTag(Tag tag) throws SQLException {
        String sql = "UPDATE Tag SET name = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, tag.getName());
            ps.setInt(2, tag.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void deleteTag(int id) throws SQLException {
        String sql = "DELETE FROM Tag WHERE id = ?";
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

![зображення](https://github.com/user-attachments/assets/d61d4a23-ed08-4e0f-b832-61c0d661afd1)

