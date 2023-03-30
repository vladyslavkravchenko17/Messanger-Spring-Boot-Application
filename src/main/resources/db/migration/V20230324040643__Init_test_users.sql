INSERT INTO users (id, active, username, description, date_of_creation, avatar_image, password)
VALUES (2, true, 'qwe', '', '2022-01-01', 'anonymous.jpg',crypt('qweqweqwe', gen_salt('bf', 8))),
       (3, true, 'asd', '', '2022-01-01', 'anonymous.jpg',crypt('asdasdasd', gen_salt('bf', 8))),
       (4, true, 'zxc', '', '2022-01-01', 'anonymous.jpg',crypt('zxczxczxc', gen_salt('bf', 8))),
       (5, true, 'wer', '', '2022-01-01', 'anonymous.jpg',crypt('werwerwer', gen_salt('bf', 8))),
       (6, true, 'sdf', '', '2022-01-01', 'anonymous.jpg',crypt('sdfsdfsdf', gen_salt('bf', 8))),
       (7, true, 'xcv', '', '2022-01-01', 'anonymous.jpg',crypt('xcvxcvxcv', gen_salt('bf', 8)));

INSERT INTO user_role (user_id, roles)
VALUES (2, 'USER'),
       (3, 'USER'),
       (4, 'USER'),
       (5, 'USER'),
       (6, 'USER'),
       (7, 'USER');