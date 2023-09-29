package com.foro.apiforo.domain.user;

import jakarta.validation.constraints.NotNull;

public record DataUserUpdate(@NotNull Long id, String password, String name, String lastname) {
}
