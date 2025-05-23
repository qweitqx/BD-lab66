package model;

public class MediaContentTag {
    private int mediaContentId;
    private int tagId;

    public MediaContentTag() {}

    public MediaContentTag(int mediaContentId, int tagId) {
        this.mediaContentId = mediaContentId;
        this.tagId = tagId;
    }

    public int getMediaContentId() { return mediaContentId; }
    public void setMediaContentId(int mediaContentId) { this.mediaContentId = mediaContentId; }

    public int getTagId() { return tagId; }
    public void setTagId(int tagId) { this.tagId = tagId; }

    @Override
    public String toString() {
        return "MediaContentTag{mediaContentId=" + mediaContentId +
                ", tagId=" + tagId +
                '}';
    }
}
