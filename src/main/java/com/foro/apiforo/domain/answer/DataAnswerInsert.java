package com.foro.apiforo.domain.answer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataAnswerInsert(

        @NotNull
        Long userId,

        @NotNull
        Long topicId,

        @NotBlank
        String message,

        @NotNull
        LocalDateTime creationDate
) {
}
