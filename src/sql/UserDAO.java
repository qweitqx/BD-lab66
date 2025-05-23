package dao;

import java.util.List;
import model.User;

public interface UserDAO {
    void insert(User user);
    User getById(int id);
    List<User> getAll();
    void update(User user);
    void delete(int id);
}
