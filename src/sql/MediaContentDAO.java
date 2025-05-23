package dao;

import model.MediaContent;
import java.util.List;

public interface MediaContentDAO {
    void addMediaContent(MediaContent mediaContent) throws Exception;
    MediaContent getMediaContentById(int id) throws Exception;
    List<MediaContent> getAllMediaContents() throws Exception;
    void updateMediaContent(MediaContent mediaContent) throws Exception;
    void deleteMediaContent(int id) throws Exception;
}
