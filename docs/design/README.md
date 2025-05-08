# Проєктування бази даних

В рамках проекту розробляється: 
- модель бізнес-об'єктів
- ER-модель


модель бізнес-об'єктів

@startuml
left to right direction

' --- Користувачі ---
entity User <> #900052
entity User.id <> #ed2f9b
entity User.first_name <> #ed2f9b
entity User.last_name <> #ed2f9b
entity User.email <> #ed2f9b
entity User.password <> #ed2f9b

User.id --* User
User.first_name --* User
User.last_name --* User
User.email --* User
User.password --* User

entity Role <> #055e29
entity Role.id <> #10c75a
entity Role.name <> #10c75a
entity Role.description <> #10c75a

Role.id --* Role
Role.name --* Role
Role.description --* Role

entity Permission <> #316e7a
entity Permission.id <> #5ebed1
entity Permission.name <> #5ebed1

Permission.id --* Permission
Permission.name --* Permission

entity UserRole <> #417035
entity RolePermission <> #702a48

User "1.1" -- "0..*" UserRole
UserRole "0..*" -- "1.1" Role

Role "1.1" -- "0..*" RolePermission
RolePermission "0..*" -- "1.1" Permission

' --- Контент ---
entity MediaContent <> #450561
entity MediaContent.id <> #941ac9
entity MediaContent.title <> #941ac9
entity MediaContent.description <> #941ac9
entity MediaContent.body <> #941ac9
entity MediaContent.content_type <> #941ac9
entity MediaContent.created_at <> #941ac9

MediaContent.id --* MediaContent
MediaContent.title --* MediaContent
MediaContent.description --* MediaContent
MediaContent.body --* MediaContent
MediaContent.content_type --* MediaContent
MediaContent.created_at --* MediaContent

User "1.1" -- "0..*" MediaContent

entity MediaContentTag <> #662923
entity Tag <> #04378a
entity Tag.id <> #3d7feb
entity Tag.name <> #3d7feb

Tag.id --* Tag
Tag.name --* Tag
MediaContent "1.1" -- "0..*" MediaContentTag
MediaContentTag "0..*" -- "1.1" Tag

' --- Джерела ---
entity Source <> #ad5a00
entity Source.id <> #e6861e
entity Source.name <> #e6861e
entity Source.url <> #e6861e

Source.id --* Source
Source.name --* Source
Source.url --* Source

entity MediaContentSource <> #804c32
MediaContent "1.1" -l- "0..*" MediaContentSource
MediaContentSource "0..*" -- "1.1" Source

entity SourceTag <> #3d7361
Source "1.1" -- "0..*" SourceTag
SourceTag "0..*" -- "1.1" Tag

' --- Аналітика ---
entity AnalysisResult <> #8f031a
entity AnalysisResult.id <> #e32040
entity AnalysisResult.created_at <> #e32040
entity AnalysisResult.title <> #e32040
entity AnalysisResult.description <> #e32040
entity AnalysisResult.body <> #e32040

AnalysisResult.id --* AnalysisResult
AnalysisResult.created_at --* AnalysisResult
AnalysisResult.title --* AnalysisResult
AnalysisResult.description --* AnalysisResult
AnalysisResult.body --* AnalysisResult

entity MediaContentAnalysisResult <> #592d33
MediaContent "1.1" -- "0..*" MediaContentAnalysisResult
MediaContentAnalysisResult "0..*" -- "1.1" AnalysisResult

User "1.1" -- "0..*" AnalysisResult

entity AnalysisResultTag <> #432b75
AnalysisResult "1.1" -- "0..*" AnalysisResultTag
AnalysisResultTag "0..*" -- "1.1" Tag

entity AnalysisReport <> #5b0673
entity AnalysisReport.id <> #bb38e0
entity AnalysisReport.created_at <> #bb38e0
entity AnalysisReport.title <> #bb38e0
entity AnalysisReport.body <> #bb38e0

AnalysisReport.id --* AnalysisReport
AnalysisReport.created_at --* AnalysisReport
AnalysisReport.title --* AnalysisReport
AnalysisReport.body --* AnalysisReport

AnalysisResult "1..*" -- "1.1" AnalysisReport
User "1.1" -- "0..*" AnalysisReport

entity AnalysisReportTag <> #1c4f3e
AnalysisReport "1.1" -- "0..*" AnalysisReportTag
AnalysisReportTag "0..*" -- "1.1" Tag

@enduml
 
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
