create table product
(
    product_id          int auto_increment
        primary key,
    title               varchar(20) not null,
    product_description text        null,
    image               text        null,
    price               double      null
);

create table purchased_item
(
    purchased_item_id int auto_increment
        primary key,
    product_id        int           null,
    count             int default 1 null,
    constraint purchased_item_ibfk_1
        foreign key (product_id) references product (product_id)
);

create index product_id
    on purchased_item (product_id);

create table user
(
    user_id       int auto_increment
        primary key,
    first_name    varchar(20)  not null,
    last_name     varchar(20)  not null,
    email         varchar(30)  not null,
    phone         varchar(10)  not null,
    user_password varchar(300) null,
    user_role     text         null,
    address        varchar(70)  null,
    constraint email
        unique (email)
);