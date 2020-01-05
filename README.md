# Mood Music

# Описание приложения:
Приложение позволяет просмотреть популярные группы музыкальных исполнителей, треки, формировать собственные альбомы треков.
Также присутствует возможность поиска как артиста, так и отдельных треков с последующим просмотром подробной информации, прослушивании (при наличии)

# Техническое описание и реализация:
1. Приложение разбито по пакетам component, shared, feature в рамках одного gradle модуля. Такая разбивка позволяет при желании разбить приложение на модули без каких-дибо проблем. В рамках модулей Clean Arhitecture c MVP (Moxy) для presentation слоя. Общая архитектура позволяет легко вносить изменения и расширять функциональность при необходимости.
2. Запросы к API сервера Last.fm реализованы с помощью Retrofit2 + Rxjava2
3. Для реализации DI используется библиотека внедрения зависимостей Dagger2
4. Использована ORM Room + Rxjava2 для сохранения и загрузки избранных альбомов с треками
5. Добавлена библиотека Butteknife для упрощения работы с view элементами
6. Реализована обработка и перехват ошибок
7. Основная логика покрыта unit-тестами
8. Реализована правильная обработка сохранения состояния при смене ориентации
9. Экран треков и артистов можно протестировать без интернета при помощи стабов

Планируется добавить:
1. UI-тестов (экраны делаем через page object, сами тесты на espresso + какая-либо абстрация - barista и т.д)
2. Перевести верстку на Constraint Layout
3. Проверить на старых девайсах производительность списков (возможно добавить diff utils)

[![Screenshot-20190307-225452.png](https://i.postimg.cc/mDvQkYG7/Screenshot-20190307-225452.png)](https://postimg.cc/hz0J37vG)

[![Screenshot-20190307-225607.png](https://i.postimg.cc/W1zhRdBV/Screenshot-20190307-225607.png)](https://postimg.cc/Jsw1Zz7Y)

[![Screenshot-20190307-225655.png](https://i.postimg.cc/q7nB0yQP/Screenshot-20190307-225655.png)](https://postimg.cc/1gm1rgkB)

[![Screenshot-20190307-225525.png](https://i.postimg.cc/xTTdHkTm/Screenshot-20190307-225525.png)](https://postimg.cc/mhJRWg9Z)

