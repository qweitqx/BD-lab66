package model;

public class MediaContentSource {
    private int mediaContentId;
    private int sourceId;

    public MediaContentSource() {}

    public MediaContentSource(int mediaContentId, int sourceId) {
        this.mediaContentId = mediaContentId;
        this.sourceId = sourceId;
    }

    public int getMediaContentId() { return mediaContentId; }
    public void setMediaContentId(int mediaContentId) { this.mediaContentId = mediaContentId; }

    public int getSourceId() { return sourceId; }
    public void setSourceId(int sourceId) { this.sourceId = sourceId; }

    @Override
    public String toString() {
        return "MediaContentSource{mediaContentId=" + mediaContentId +
                ", sourceId=" + sourceId +
                '}';
    }
}
