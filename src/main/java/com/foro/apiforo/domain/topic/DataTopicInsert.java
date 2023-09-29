package com.foro.apiforo.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataTopicInsert(
        @NotNull
        Long userId,

        @NotBlank
        String tittle,

        @NotBlank
        String message,

        @NotNull
        LocalDateTime creationDate,

        @NotNull
        Course course
) {
}
