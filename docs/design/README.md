# Проєктування бази даних

В рамках проекту розробляється: 
- модель бізнес-об'єктів 
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

