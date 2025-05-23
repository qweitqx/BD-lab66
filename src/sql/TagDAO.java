package dao;

import model.Tag;
import java.util.List;

public interface TagDAO {
    void addTag(Tag tag) throws Exception;
    Tag getTagById(int id) throws Exception;
    List<Tag> getAllTags() throws Exception;
    void updateTag(Tag tag) throws Exception;
    void deleteTag(int id) throws Exception;
}
