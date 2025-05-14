# Реалізація інформаційного та програмного забезпечення

## SQL скрипти для ініціалізації та наповнення бази даних

```sql
CREATE TABLE User (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(45),
    last_name VARCHAR(45),
    email VARCHAR(45) UNIQUE,
    password VARCHAR(45)
);

CREATE TABLE Role (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45),
    description VARCHAR(255)
);

CREATE TABLE Permission (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45)
);

CREATE TABLE UserRole (
    user_id INT,
    role_id INT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES Role(id) ON DELETE CASCADE
);

CREATE TABLE RolePermission (
    role_id INT,
    permission_id INT,
    PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES Role(id) ON DELETE CASCADE,
    FOREIGN KEY (permission_id) REFERENCES Permission(id) ON DELETE CASCADE
);

CREATE TABLE Source (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45),
    url VARCHAR(255)
);

CREATE TABLE Tag (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45)
);

CREATE TABLE MediaContent (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(45),
    description VARCHAR(255),
    body VARCHAR(255),
    content_type VARCHAR(45),
    created_at DATE,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES User(id)
);

CREATE TABLE MediaContentSource (
    mediaContent_id INT,
    source_id INT,
    PRIMARY KEY (mediaContent_id, source_id),
    FOREIGN KEY (mediaContent_id) REFERENCES MediaContent(id) ON DELETE CASCADE,
    FOREIGN KEY (source_id) REFERENCES Source(id) ON DELETE CASCADE
);

CREATE TABLE SourceTag (
    source_id INT,
    tag_id INT,
    PRIMARY KEY (source_id, tag_id),
    FOREIGN KEY (source_id) REFERENCES Source(id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES Tag(id) ON DELETE CASCADE
);

CREATE TABLE MediaContentTag (
    mediaContent_id INT,
    tag_id INT,
    PRIMARY KEY (mediaContent_id, tag_id),
    FOREIGN KEY (mediaContent_id) REFERENCES MediaContent(id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES Tag(id) ON DELETE CASCADE
);

CREATE TABLE AnalysisReport (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(45),
    body VARCHAR(255),
    created_at DATE,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES User(id)
);

CREATE TABLE AnalysisResult (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(45),
    description VARCHAR(255),
    body VARCHAR(255),
    created_at DATE,
    user_id INT,
    analysisReport_id INT,
    FOREIGN KEY (user_id) REFERENCES User(id),
    FOREIGN KEY (analysisReport_id) REFERENCES AnalysisReport(id)
);

CREATE TABLE MediaContentAnalysisResult (
    mediaContent_id INT,
    analysisResult_id INT,
    PRIMARY KEY (mediaContent_id, analysisResult_id),
    FOREIGN KEY (mediaContent_id) REFERENCES MediaContent(id) ON DELETE CASCADE,
    FOREIGN KEY (analysisResult_id) REFERENCES AnalysisResult(id) ON DELETE CASCADE
);

CREATE TABLE AnalysisResultTag (
    analysisResult_id INT,
    tag_id INT,
    PRIMARY KEY (analysisResult_id, tag_id),
    FOREIGN KEY (analysisResult_id) REFERENCES AnalysisResult(id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES Tag(id) ON DELETE CASCADE
);

CREATE TABLE AnalysisReportTag (
    analysisReport_id INT,
    tag_id INT,
    PRIMARY KEY (analysisReport_id, tag_id),
    FOREIGN KEY (analysisReport_id) REFERENCES AnalysisReport(id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES Tag(id) ON DELETE CASCADE
);
-- USER
INSERT INTO User (first_name, last_name, email, password) VALUES
('John', 'Doe', 'john.doe@example.com', 'password123'),
('Jane', 'Smith', 'jane.smith@example.com', 'password456');

-- ROLE
INSERT INTO Role (name, description) VALUES
('Admin', 'Administrator with full access'),
('Editor', 'Can edit and manage content'),
('Viewer', 'Can view content only');

-- PERMISSION
INSERT INTO Permission (name) VALUES
('Create Content'),
('Edit Content'),
('Delete Content'),
('View Content');

-- USER_ROLE
INSERT INTO UserRole (user_id, role_id) VALUES
(1, 1), -- John is Admin
(2, 2); -- Jane is Editor

-- ROLE_PERMISSION
INSERT INTO RolePermission (role_id, permission_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), -- Admin has all permissions
(2, 1), (2, 2), (2, 4);        -- Editor has limited permissions

-- SOURCE
INSERT INTO Source (name, url) VALUES
('CNN', 'https://www.cnn.com'),
('BBC', 'https://www.bbc.com');

-- TAG
INSERT INTO Tag (name) VALUES
('Politics'),
('Technology'),
('Science'),
('Health');

-- MEDIA_CONTENT
INSERT INTO MediaContent (title, description, body, content_type, created_at, user_id) VALUES
('Tech News Today', 'Latest tech trends', 'Content body here...', 'Article', '2025-05-01', 1),
('Health Report', 'Health insights and news', 'Body of the health report...', 'Report', '2025-05-02', 2);

-- MEDIA_CONTENT_SOURCE
INSERT INTO MediaContentSource (mediaContent_id, source_id) VALUES
(1, 1),
(2, 2);

-- SOURCE_TAG
INSERT INTO SourceTag (source_id, tag_id) VALUES
(1, 2), -- CNN - Technology
(2, 4); -- BBC - Health

-- MEDIA_CONTENT_TAG
INSERT INTO MediaContentTag (mediaContent_id, tag_id) VALUES
(1, 2),
(2, 4);

-- ANALYSIS_REPORT
INSERT INTO AnalysisReport (title, body, created_at, user_id) VALUES
('Tech Analysis', 'Analysis of the latest tech content', '2025-05-05', 1),
('Health Analysis', 'Analysis of health topics', '2025-05-06', 2);

-- ANALYSIS_RESULT
INSERT INTO AnalysisResult (title, description, body, created_at, user_id, analysisReport_id) VALUES
('AI in 2025', 'Artificial Intelligence trends', 'AI will dominate...', '2025-05-07', 1, 1),
('Pandemic Recovery', 'Post-pandemic effects', 'Recovery analysis...', '2025-05-08', 2, 2);

-- MEDIA_CONTENT_ANALYSIS_RESULT
INSERT INTO MediaContentAnalysisResult (mediaContent_id, analysisResult_id) VALUES
(1, 1),
(2, 2);

-- ANALYSIS_RESULT_TAG
INSERT INTO AnalysisResultTag (analysisResult_id, tag_id) VALUES
(1, 2), -- AI in 2025 - Technology
(2, 4); -- Pandemic Recovery - Health

-- ANALYSIS_REPORT_TAG
INSERT INTO AnalysisReportTag (analysisReport_id, tag_id) VALUES
(1, 2),
(2, 4);
```
