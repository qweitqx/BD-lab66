# Модель прецедентів

В цьому файлі необхідно перелічити всі документи, розроблені в проекті та дати посилання на них.

*Модель прецедентів повинна містити загальні оглядові діаграми та специфікації прецедентів.*



Вбудовування зображень діаграм здійснюється з використанням сервісу [plantuml.com](https://plantuml.com/). 

В markdown-файлі використовується опис діаграми

```md

<center style="
    border-radius:4px;
    border: 1px solid #cfd7e6;
    box-shadow: 0 1px 3px 0 rgba(89,105,129,.05), 0 1px 1px 0 rgba(0,0,0,.025);
    padding: 1em;"
>

@startuml

    right header
        <font size=24 color=black>Package: <b>UCD_3.0
    end header

    title
        <font size=18 color=black>UC_8. Редагувати конфігурацію порталу
        <font size=16 color=black>Діаграма прецедентів
    end title


    actor "Користувач" as User #eeeeaa
    
    package UCD_1{
        usecase "<b>UC_1</b>\nПереглянути список \nзвітів" as UC_1 #aaeeaa
    }
    
    usecase "<b>UC_1.1</b>\nЗастосувати фільтр" as UC_1.1
    usecase "<b>UC_1.2</b>\nПереглянути метадані \nзвіту" as UC_1.2  
    usecase "<b>UC_1.2.1</b>\nДати оцінку звіту" as UC_1.2.1  
    usecase "<b>UC_1.2.2</b>\nПереглянути інформацію \nпро авторів звіту" as UC_1.2.2
    
    package UCD_1 {
        usecase "<b>UC_4</b>\nВикликати звіт" as UC_4 #aaeeaa
    }
    
    usecase "<b>UC_1.1.1</b>\n Використати \nпошукові теги" as UC_1.1.1  
    usecase "<b>UC_1.1.2</b>\n Використати \nрядок пошуку" as UC_1.1.2
    usecase "<b>UC_1.1.3</b>\n Використати \nавторів" as UC_1.1.3  
    
    
    
    User -> UC_1
    UC_1.1 .u.> UC_1 :extends
    UC_1.2 .u.> UC_1 :extends
    UC_4 .d.> UC_1.2 :extends
    UC_1.2 .> UC_1.2 :extends
    UC_1.2.1 .u.> UC_1.2 :extends
    UC_1.2.2 .u.> UC_1.2 :extends
    UC_1 ..> UC_1.2.2 :extends
    
    
    UC_1.1.1 -u-|> UC_1.1
    UC_1.1.2 -u-|> UC_1.1
    UC_1.1.3 -u-|> UC_1.1
    
    right footer
        Аналітичний портал. Модель прецедентів.
        НТУУ КПІ ім.І.Сікорського
        Киів-2020
    end footer

@enduml

**Діаграма прецедентів**

</center>
```

яка буде відображена наступним чином

@startuml
left to right direction
skinparam packageStyle rectangle
skinparam usecase {
  BackgroundColor White
  BorderColor Black
  ArrowColor Gray
  FontSize 12
}

actor "Користувач" as User
actor "Адміністратор" as Admin
User <|-- Admin : "розширення прав"

' ------------------- Use Case Groups -------------------

package "Реєстрація та Аутентифікація" {
  usecase "AuthAccess\n(Доступ до системи)" as UC_AuthAccess

  usecase "UserSignUp\n(Створення облікового запису)" as UC_SignUp
  usecase "UserSignIn\n(Вхід у систему)" as UC_SignIn
  usecase "UserPasswordReset\n(Відновлення паролю)" as UC_Reset

  UC_AuthAccess --> UC_SignUp : <<include>>
  UC_AuthAccess --> UC_SignIn : <<include>>
  UC_AuthAccess --> UC_Reset : <<include>>
}

package "Керування Користувачами" {
  usecase "UserManagement\n(Управління користувачами)" as UC_UserMgmt

  usecase "UserUpgradeRole\n(Зміна рівня доступу)" as UC_Upgrade
  usecase "UserRemove\n(Деактивація користувача)" as UC_Remove
  usecase "UserProfileUpdate\n(Оновлення профілю)" as UC_Profile

  UC_UserMgmt --> UC_Upgrade : <<include>>
  UC_UserMgmt --> UC_Remove : <<include>>
  UC_UserMgmt --> UC_Profile : <<include>>
}

package "Контент-менеджмент" {
  usecase "ContentHub\n(Робота з контентом)" as UC_ContentHub

  usecase "ContentCreate\n(Додати контент)" as UC_ContentCreate
  usecase "ContentEdit\n(Редагувати контент)" as UC_ContentEdit
  usecase "ContentDelete\n(Видалити контент)" as UC_ContentDelete
  usecase "ContentSearch\n(Пошук контенту)" as UC_ContentSearch

  UC_ContentHub --> UC_ContentCreate : <<include>>
  UC_ContentHub --> UC_ContentEdit : <<include>>
  UC_ContentHub --> UC_ContentDelete : <<include>>
  UC_ContentHub --> UC_ContentSearch : <<include>>
}

package "Медіа-аналітика" {
  usecase "MediaAnalytics\n(Аналіз медіа)" as UC_MediaHub

  usecase "MediaMonitor01\n(Виявлення фейкових новин)" as UC_MediaFake
  usecase "MediaMonitor02\n(Моніторинг негативних згадок)" as UC_MediaNeg
  usecase "MediaMonitor03\n(Рівень зацікавленості аудиторії)" as UC_MediaEngage
  usecase "MediaMonitor04\n(Першоджерело контенту)" as UC_MediaSource
  usecase "MediaMonitor05\n(Тенденції поширення контенту)" as UC_MediaTrends

  UC_MediaHub --> UC_MediaFake : <<include>>
  UC_MediaHub --> UC_MediaNeg : <<include>>
  UC_MediaHub --> UC_MediaEngage : <<include>>
  UC_MediaHub --> UC_MediaSource : <<include>>
  UC_MediaHub --> UC_MediaTrends : <<include>>
}

' ------------------- Actors associations -------------------

User --> UC_AuthAccess
User --> UC_UserMgmt
User --> UC_ContentHub
User --> UC_MediaHub

Admin --> UC_UserMgmt

@enduml


**Діаграма прецедентів**

</center>

