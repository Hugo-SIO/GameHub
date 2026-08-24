package fr.hugoal.gamehubbackend.dtos.users;

public record UserResponse(
        Long id,
        String username,
        String email,
        String role
) {
}