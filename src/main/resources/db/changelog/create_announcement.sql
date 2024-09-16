--liquibase formatted sql
--changeset aliya:script01

drop table if exists image;
drop table if exists announcement;

create table announcement
(
    id           serial primary key,
    text         varchar(500),
    lat          decimal,
    lng          decimal,
    phone_number varchar(10),
    date         timestamp
);

create table image
(
    id              serial primary key,
    image_link      varchar(200),
    is_preview      boolean,
    announcement_id int,
    foreign key (announcement_id) references announcement (id)
)

