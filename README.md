# Spring

### Functional requirements:
1. As a user, I want to be able to browse the list of all available courses to select the ones I am interested in.
2. As a user, I want to be able to view the details of a particular course to find out more information about it.
3. As an administrator, I want to be able to add new courses to expand the available learning content.
4. As an administrator, I want to be able to edit existing courses to update information or make changes.
5. As an administrator, I want to be able to delete courses that are no longer relevant to keep the course list organized.
6. As a user, I want to be able to register on the platform to create an account and have access to the system's functionality.
7. As a user, I want to be able to log in with a username and password to protect my account.
8. As an administrator, I want to be able to view a list of all students in the system to monitor and track their activity.
9. As an administrator, I want to add new students to the system to register them for courses or enter their information manually.
10. As an administrator, I want to edit student information to make changes or correct data.
11. As an administrator, I want to delete students from the system to clean up the database from inactive or mistakenly added records.

### System behavior & endpoints:
1. Система повинна відображати список усіх курсів у відповідь на запит до ендпоінта `GET /courses`.
2. Система повинна повертати деталі конкретного курсу у відповідь на запит до ендпоінта `GET /course/{id}`.
3. Система повинна надавати форму для створення нового курсу за запитом до ендпоінта `GET /course/new`.
4. Система повинна зберігати новий курс у базі даних у відповідь на запит до ендпоінта `POST /saveCourse`.
5. Система повинна надавати форму для редагування існуючого курсу у відповідь на запит до ендпоінта `GET /course/edit/{id}`.
6. Система повинна оновлювати дані курсу в базі даних у відповідь на запит до ендпоінта `POST /saveCourse`.
7. Система повинна видаляти курс із бази даних у відповідь на запит до ендпоінта `GET /course/delete/{id}`.
8. Система повинна відображати список усіх студентів у відповідь на запит до ендпоінта `GET /students`.
9. Система повинна надавати форму для створення нового студента за запитом до ендпоінта `GET /student/new`.
10. Система повинна зберігати нові дані про студента в базі даних у відповідь на запит до ендпоінта `POST /saveStudent`.
11. Система повинна надавати форму для редагування даних студента у відповідь на запит до ендпоінта `GET /student/edit/{id}`.
12. Система повинна видаляти студента із бази даних у відповідь на запит до ендпоінта `GET /student/delete/{id}`.
13. Система повинна надавати форму для реєстрації нового користувача за запитом до ендпоінта `GET /signup`.
14. Система повинна зберігати нового користувача в базі даних та перевіряти правильність введених даних у відповідь на запит до ендпоінта `POST /saveSignup`.
15. Система повинна відображати сторінку для входу користувачів у відповідь на запит до ендпоінта `GET /login`.