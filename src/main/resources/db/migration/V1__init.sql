create schema shorty_video_service;

create table shorty_video_service.client_role
(
    role_id             bigserial  not null,
    role_name           char(50) not null,
    constraint PK_53 primary key ( role_id )
);

create table shorty_video_service.client
(
    client_id           bigserial not null,
    client_name         char(50) not null,
    role_id             bigint   not null,
    user_second_name    char(50) not null,
    age                 int not null,
    sex                 boolean not null,
    user_password       char(50) not null,
    user_photo          char(255) not null,
    constraint PK_44 primary key ( client_id ),
    constraint FK_58 foreign key ( role_id ) references shorty_video_service.client_role ( role_id )
);

create table shorty_video_service.video_rating
(
    rating_id           bigserial not null,
    like_counter        int not null,
    dislike_counter     int not null,
    views_counter       int  not null,
    constraint PK_16 primary key ( rating_id )
);

create table shorty_video_service.video
(
    video_id            bigserial not null,
    video_name          char(50)  not null,
    rating_id           bigint       not null,
    client_id           bigint       not null,
    upload_date         timestamp not null,
    is_available        boolean   not null,
    video_description   char(255) not null,
    constraint PK_6 primary key (video_id),
    constraint FK_67 foreign key (client_id) references shorty_video_service.client (client_id),
    constraint FK_94 foreign key (rating_id) references shorty_video_service.video_rating (rating_id)
);

create index FK_69 on shorty_video_service.video
(
     client_id
);

create index FK_96 on shorty_video_service.video
(
     rating_id
);

create index FK_60 on shorty_video_service.client
(
     role_id
);

create table shorty_video_service.video_comment
(
    comment_id          bigserial not null,
    comment_text        char(1000) not null,
    client_id           bigint not null,
    video_id            bigint  not null,
    constraint PK_72 primary key ( comment_id ),
    constraint FK_107 foreign key ( client_id ) references shorty_video_service.client ( client_id ),
    constraint FK_86 foreign key ( video_id ) references shorty_video_service.video ( video_id )
);

create index FK_109 on shorty_video_service.video_comment
(
     client_id
);

create index FK_90 on shorty_video_service.video_comment
(
     video_id
);
