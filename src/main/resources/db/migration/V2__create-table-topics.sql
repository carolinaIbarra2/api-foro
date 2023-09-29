CREATE TABLE topics(
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    tittle VARCHAR(100) NOT NULL,
    message VARCHAR(100) NOT NULL,
    creation_date DATETIME NOT NULL,
    course VARCHAR(100) NOT NULL,
    flag TINYINT NOT NULL,

    PRIMARY KEY (id),

    CONSTRAINT fk_topics_users_id FOREIGN KEY (user_id) REFERENCES users(id)
);

