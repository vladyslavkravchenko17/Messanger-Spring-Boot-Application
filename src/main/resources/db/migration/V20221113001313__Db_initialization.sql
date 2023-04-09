CREATE TABLE message
(
    id          int8          NOT NULL,
    user_id     int8          NOT NULL,
    dialogue_id int8          NOT NULL,
    text        VARCHAR(2048) NOT NULL,
    date_time   TIMESTAMP     NOT NULL,
    file        VARCHAR(255)  NULL,
    PRIMARY KEY (id)
);

CREATE SEQUENCE hibernate_sequence START 1 INCREMENT 1;

CREATE TABLE user_role
(
    user_id int8 NOT NULL,
    roles   VARCHAR(255)
);

CREATE TABLE users
(
    id               int8         NOT NULL,
    active           boolean      NOT NULL,
    username         VARCHAR(255) NOT NULL,
    description      VARCHAR(255) NULL,
    date_of_creation DATE         NOT NULL,
    avatar_image     VARCHAR(255) NULL,
    password         VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE dialogue
(
    id        int8      NOT NULL,
    date_time TIMESTAMP NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE users_dialogue
(
    user_id     int8 NOT NULL,
    dialogue_id int8 NOT NULL,
    CONSTRAINT users_dialogue_fk1
        FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT users_dialogue_fk2
        FOREIGN KEY (dialogue_id) REFERENCES dialogue (id),
    PRIMARY KEY (user_id, dialogue_id)

);

ALTER TABLE IF EXISTS message
    ADD CONSTRAINT message_user_fk
        FOREIGN KEY (user_id) REFERENCES users (id),
    ADD CONSTRAINT message_dialogue_fk
        FOREIGN KEY (dialogue_id) REFERENCES dialogue (id);

ALTER TABLE IF EXISTS user_role
    ADD CONSTRAINT user_role_user_fk
        FOREIGN KEY (user_id) REFERENCES users (id);

CREATE TABLE message_files (
                               message_id BIGINT NOT NULL,
                               file_name VARCHAR(255) NOT NULL,
                               PRIMARY KEY (message_id, file_name),
                               FOREIGN KEY (message_id) REFERENCES message(id)
);

