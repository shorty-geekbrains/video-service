insert into shorty.video("name", "link", rating_id, client_id, is_available, description)
values ('sample1', 'video-service/video/classpath:videos/1.mp4', null, null, true, 'sample1'),
       ('sample2', 'video-service/video/classpath:videos/2.mp4', null, null, true, 'sample2'),
       ('sample3', 'video-service/video/classpath:videos/3.mp4', null, null, true, 'sample3');
insert into shorty.role("name")
values ('ROLE_USER');