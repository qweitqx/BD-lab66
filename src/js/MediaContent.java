package model;

import java.sql.Date;

public class MediaContent {
    private int id;
    private String title;
    private String description;
    private String body;
    private String contentType;
    private Date createdAt;
    private int userId;

    public MediaContent() {}

    public MediaContent(int id, String title, String description, String body, String contentType, Date createdAt, int userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.body = body;
        this.contentType = contentType;
        this.createdAt = createdAt;
        this.userId = userId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public String getContentType() { return contentType; }
    public void setContentType(String contentType) { this.contentType = contentType; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
}
