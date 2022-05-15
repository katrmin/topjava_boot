--liquibase formatted sql

--changeset Minaeva E.A.:001.create_tables.sql-1
SET SCHEMA VREST;
create table USERS
(
    id       bigint auto_increment PRIMARY KEY,
    name     VARCHAR(255),
    email    VARCHAR(255),
    password VARCHAR(255)
);

--changeset Minaeva E.A.:001.create_tables.sql-2
create table USER_ROLES
(
    id      bigint auto_increment PRIMARY KEY,
    role    VARCHAR(255),
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES USERS (ID)
);

--changeset Minaeva E.A.:001.create_tables.sql-3
create table RESTAURANTS
(
    id   bigint auto_increment PRIMARY KEY,
    name VARCHAR(255)
);

--changeset Minaeva E.A.:001.create_tables.sql-4
create table DISHES
(
    id   bigint auto_increment PRIMARY KEY,
    name VARCHAR(255)
);

--changeset Minaeva E.A.:001.create_tables.sql-5
create table MENU_RESTAURANTS_DISHES
(
    id            bigint auto_increment PRIMARY KEY,
    restaurant_id BIGINT,
    dish_price    DECIMAL(9,2),
    dish_id       BIGINT,
    date_time     datetime,
    FOREIGN KEY (dish_id) REFERENCES DISHES (ID),
    FOREIGN KEY (restaurant_id) REFERENCES RESTAURANTS (ID)
);