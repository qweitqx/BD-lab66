package model;

public class SourceTag {
    private int sourceId;
    private int tagId;

    public SourceTag() {}

    public SourceTag(int sourceId, int tagId) {
        this.sourceId = sourceId;
        this.tagId = tagId;
    }

    public int getSourceId() { return sourceId; }
    public void setSourceId(int sourceId) { this.sourceId = sourceId; }

    public int getTagId() { return tagId; }
    public void setTagId(int tagId) { this.tagId = tagId; }

    @Override
    public String toString() {
        return "SourceTag{sourceId=" + sourceId +
                ", tagId=" + tagId +
                '}';
    }
}
