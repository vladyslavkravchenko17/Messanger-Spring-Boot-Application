INSERT INTO users (id, active, username, description, date_of_creation, avatar_image, password)
VALUES (1, true, 'admin', '', '2022-01-01', 'anonymous.jpg',
        crypt('123456789', gen_salt('bf', 8)));

INSERT INTO user_role (user_id, roles)
VALUES (1, 'USER'),
       (1, 'ADMIN');

CREATE EXTENSION IF NOT EXISTS pgcrypto;
