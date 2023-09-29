package com.foro.apiforo.domain.answer;

import java.time.LocalDateTime;

public record DataAnswerResponse(
        Long id,

        Long userId,

        Long topicId,

        String message,

        LocalDateTime creationDate
) {

    public DataAnswerResponse(Answer answer){
        this(
                answer.getId(),
                answer.getUser().getId(),
                answer.getTopic().getId(),
                answer.getMessage(),
                answer.getCreationDate()
        );
    }
}
