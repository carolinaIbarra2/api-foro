package com.foro.apiforo.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataUserInsert(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password,
        @NotNull
        UserType userType,
        @NotBlank
        String name,
        @NotBlank
        String lastname
) {

}
