package com.foro.apiforo.domain.topic;

import java.time.LocalDateTime;

public record DataTopicResponse(
        Long id,
        Long userId,
        String tittle,
        String message,
        LocalDateTime creationDate,
        Course course
) {

    public DataTopicResponse(Topic topic){
        this(
             topic.getId(),
             topic.getUser().getId(),
             topic.getTittle(),
             topic.getMessage(),
             topic.getCreationDate(),
             topic.getCourse()
        );
    }
}
