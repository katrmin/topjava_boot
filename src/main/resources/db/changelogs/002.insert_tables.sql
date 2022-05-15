--liquibase formatted sql
SET SCHEMA VREST;
--changeset Minaeva E.A.:002.insert_tables.sql
INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('Guest', 'guest@gmail.com', '{noop}guest'),
       ('Root', 'root@gmail.com', '123');

INSERT INTO USER_ROLES (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2),
       ('USER', 4);

insert into RESTAURANTS(name)
values ('Первый ресторан'),
       ('Второй ресторан'),
       ('Третий ресторан');

INSERT INTO DISHES (name)
VALUES ('Яичница-глазунья'),
       ('Борщ украинский'),
       ('Ватрушки к борщу'),
       ('Бефстроганов из говядины с пюре'),
       ('Пирожное'),
       ('Компот'),
       ('Чай'),
       ('Кофе');

insert into MENU_RESTAURANTS_DISHES(restaurant_id,
                                    dish_id,
                                    dish_price,
                                    date_time)
values (1, 1, 500.30, '2022-05-12 10:00:00'),
       (1, 2, 330.30, '2022-05-12 10:01:00'),
       (1, 3, 60.0, '2022-05-12 10:02:00'),
       (1, 6, 100.0, '2022-05-12 09:03:00'),
       (2, 1, 300.30, '2022-05-12 09:00:00'),
       (2, 2, 230.30, '2022-05-12 09:01:00'),
       (2, 3, 40.0, '2022-05-12 09:02:00'),
       (2, 6, 80.0, '2022-05-12 09:03:00'),
       (3, 4, 400.30, '2022-05-12 09:30:00'),
       (3, 2, 230.30, '2022-05-12 09:31:00'),
       (3, 3, 40.0, '2022-05-12 09:32:00'),
       (3, 7, 100.0, '2022-05-12 09:33:00')