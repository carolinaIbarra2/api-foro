package com.foro.apiforo.domain.user;

public record DataUserResponse(
        Long id,
        String email,
        UserType userType,
        String name,
        String lastname
) {

    public DataUserResponse(User user){
        this(
                user.getId(),
                user.getEmail(),
                user.getUserType(),
                user.getName(),
                user.getLastname()
        );
    }
}
