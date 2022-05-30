create schema if not exists shorty;

create table if not exists shorty."role"
(
    "id"                bigserial primary key,
    "name"              char(50) not null
);

create table if not exists shorty.client
(
    "id"                bigserial primary key,
    "name"              char(50) not null,
    role_id             bigint   not null,
    second_name         char(50) not null,
    enabled             boolean not null,
    age                 char(50) not null,
    sex                 boolean not null,
    "password"          char(60) not null,
    photo               char(255) not null,
    constraint fk_role foreign key (role_id) references shorty."role" ("id")
);

create index if not exists client_tbl_role_id_idx on shorty.client
(
     role_id
);

create table if not exists shorty.rating
(
    "id"                bigserial primary key,
    like_counter        int not null,
    dislike_counter     int not null,
    views_counter       int not null
);

create table if not exists shorty.video
(
    "id"                bigserial primary key,
    "name"              char(50) not null,
    "link"              varchar(255),
    upload_date         timestamp not null default now(),
    rating_id           bigint, -- not null
    client_id           bigint, -- not null
    is_available        boolean not null,
    description         char(255) not null,
    constraint fk_client1 foreign key (client_id) references shorty.client ("id"),
    constraint fk_rating foreign key (rating_id) references shorty.rating ("id")
);

create index if not exists video_tbl_client_id_idx on shorty.video
(
     client_id
);

create index if not exists video_tbl_rating_id_idx on shorty.video
(
     rating_id
);

create table if not exists shorty."comment"
(
    "id"                bigserial primary key,
    comment_text        char(1000) not null,
    client_id           bigint not null,
    video_id            bigint  not null,
    constraint fk_client foreign key (client_id) references shorty.client ("id"),
    constraint fk_video foreign key (video_id) references shorty.video ("id")
);

create index if not exists comment_tbl_client_id_idx on shorty."comment"
(
     client_id
);

create index if not exists comment_tbl_video_id_idx on shorty."comment"
(
     video_id
);
