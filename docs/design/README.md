# Проєктування бази даних

В рамках проекту розробляється: 
- модель бізнес-об'єктів
@startuml
skinparam linetype ortho
class User {
  +String email
  +String password
  +String firstName
  +String lastName
  +String profilePicture
  +String language
  +void signUp()
  +void signIn()
  +void resetPassword()
  +void updateProfile()
}

class Administrator {
  +void upgradeUserRole()
  +void removeUser()
}

class Content {
  +String title
  +String description
  +String file
  +void create()
  +void edit()
  +void delete()
}

class System {
  +void checkDataValidity()
  +void checkEmailAvailability()
  +void authenticateUser()
  +void updateUserRole()
  +void sendPasswordResetToken()
  +void storeNewContent()
  +void searchContent()
}

User "1" *-- "*" Content : Creates
User "1" *-- "*" Content : Edits
Administrator "1" *-- "*" User : Modifies Role
Administrator "1" *-- "*" User : Deactivates Account
User "1" *-- "*" Content : Deletes
System "1" *-- "*" User : Authenticates
System "1" *-- "*" User : Validates Registration
System "1" *-- "*" Content : Manages Content
System "1" *-- "*" User : Sends Password Reset
@enduml

- ER-модель

@startuml
skinparam linetype ortho

entity User {
  +id: Int
  +first_name: Text
  +last_name: Text
  +email: Text
  +password: Text
}

entity Role {
  +id: Int
  +name: Text
  +description: Text
}

entity Permission {
  +id: Int
  +name: Text
}

entity UserRole {
  user_id: Int
  role_id: Int
}

entity RolePermission {
  role_id: Int
  permission_id: Int
}

entity MediaContent {
  +id: Int
  +title: Text
  +description: Text
  +body: Text
  +content_type: Text
  +created_at: Date
  +user_id: Int
}

entity Tag {
  +id: Int
  +name: Text
}

entity MediaContentTag {
  mediaContent_id: Int
  tag_id: Int
}

entity Source {
  +id: Int
  +name: Text
  +url: Text
}

entity MediaContentSource {
  mediaContent_id: Int
  source_id: Int
}

entity AnalysisReport {
  +id: Int
  +title: Text
  +body: Text
  +created_at: Date
  +user_id: Int
}

entity AnalysisResult {
  +id: Int
  +created_at: Date
  +title: Text
  +description: Text
  +body: Text
  +analysisReport_id: Int
}

entity MediaContentAnalysisResult {
  mediaContent_id: Int
  analysisResult_id: Int
}

entity AnalysisResultTag {
  analysisResult_id: Int
  tag_id: Int
}

entity AnalysisReportTag {
  analysisReport_id: Int
  tag_id: Int
}

entity SourceTag {
  source_id: Int
  tag_id: Int
}

User ||--o{ UserRole
Role ||--o{ UserRole

Role ||--o{ RolePermission
Permission ||--o{ RolePermission

User ||--o{ MediaContent
MediaContent ||--o{ MediaContentTag
Tag ||--o{ MediaContentTag

MediaContent ||--o{ MediaContentSource
Source ||--o{ MediaContentSource

Source ||--o{ SourceTag
Tag ||--o{ SourceTag

User ||--o{ AnalysisReport
AnalysisReport ||--o{ AnalysisReportTag
Tag ||--o{ AnalysisReportTag

AnalysisReport ||--o{ AnalysisResult
AnalysisResult ||--o{ AnalysisResultTag
Tag ||--o{ AnalysisResultTag

MediaContent ||--o{ MediaContentAnalysisResult
AnalysisResult ||--o{ MediaContentAnalysisResult
@enduml
- реляційна схема

@startuml
hide circle
skinparam linetype ortho


hide circle
skinparam linetype ortho

entity User {
  *id : int
  --
  first_name : varchar(45)
  last_name : varchar(45)
  email : varchar(45)
  password : varchar(45)
}

entity Role {
  *id : int
  --
  name : varchar(45)
  description : varchar(255)
}

entity UserRole {
  *user_id : int
  *role_id : int
}

entity Permission {
  *id : int
  --
  name : varchar(45)
}

entity RolePermission {
  *role_id : int
  *permission_id : int
}

entity MediaContent {
  *id : int
  --
  Title : varchar(45)
  Description : varchar(255)
  Body : varchar(255)
  content_type : varchar(45)
  created_at : date
  user_id : int
}

entity MediaContentSource {
  *mediaContent_id : int
  *source_id : int
}

entity MediaContentTag {
  *mediaContent_id : int
  *tag_id : int
}

entity MediaContentAnalysisResult {
  *mediaContent_id : int
  *analysisResult_id : int
}

entity Source {
  *id : int
  --
  name : varchar(45)
  url : varchar(255)
}

entity SourceTag {
  *source_id : int
  *tag_id : int
}

entity Tag {
  *id : int
  --
  name : varchar(45)
}

entity AnalysisResult {
  *id : int
  --
  Title : varchar(45)
  Description : varchar(255)
  Body : varchar(255)
  created_at : date
  user_id : int
  analysisReport_id : int
}

entity AnalysisReport {
  *id : int
  --
  Title : varchar(45)
  Body : varchar(255)
  created_at : date
  user_id : int
}

entity AnalysisResultTag {
  *analysisResult_id : int
  *tag_id : int
}

entity AnalysisReportTag {
  *analysisReport_id : int
  *tag_id : int
}

' Relationships
User ||--o{ UserRole
Role ||--o{ UserRole
Role ||--o{ RolePermission
Permission ||--o{ RolePermission
User ||--o{ MediaContent
User ||--o{ AnalysisResult
User ||--o{ AnalysisReport

MediaContent ||--o{ MediaContentSource
Source ||--o{ MediaContentSource

MediaContent ||--o{ MediaContentTag
Tag ||--o{ MediaContentTag

MediaContent ||--o{ MediaContentAnalysisResult
AnalysisResult ||--o{ MediaContentAnalysisResult

Source ||--o{ SourceTag
Tag ||--o{ SourceTag

AnalysisResult ||--o{ AnalysisResultTag
Tag ||--o{ AnalysisResultTag

AnalysisReport ||--o{ AnalysisReportTag
Tag ||--o{ AnalysisReportTag

AnalysisResult ||--|| AnalysisReport
@enduml
