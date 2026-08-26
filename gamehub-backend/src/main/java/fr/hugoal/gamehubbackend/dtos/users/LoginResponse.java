package fr.hugoal.gamehubbackend.dtos.users;

public record LoginResponse(
        Long id,
        String username,
        String role,
        String token
) {
}
