package com.foro.apiforo.domain.answer;

import jakarta.validation.constraints.NotNull;

public record DataAnswerUpdate(@NotNull Long id, String message) {
}
