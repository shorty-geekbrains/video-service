create table videos
(
    "id"            bigserial primary key,
    "name"          varchar(255),
    "link"          varchar(255),
    date_upload     timestamp default current_timestamp,
    rating_id       bigint,
    user_id         bigint,
    is_available    boolean,
    comment_id      bigint,
    description     varchar(255)
);

insert into videos(name, link, rating_id, user_id, is_available, comment_id, description)
values('sample', 'classpath:videos/sample.mp4', null, null, true, null, 'sample');