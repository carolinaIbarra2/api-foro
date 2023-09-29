package com.foro.apiforo.domain.topic;

import jakarta.validation.constraints.NotNull;

public record DataTopicUpdate(@NotNull Long id, String tittle, String message, Course course) {
}
