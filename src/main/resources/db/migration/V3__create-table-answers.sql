CREATE TABLE answers(
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    topic_id BIGINT NOT NULL,
    message VARCHAR(500) NOT NULL,
    creation_date DATETIME NOT NULL,
    flag TINYINT NOT NULL,

    PRIMARY KEY (id),

    CONSTRAINT fk_answers_users_id FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_answers_topics_id FOREIGN KEY (topic_id) REFERENCES topics(id)
);

