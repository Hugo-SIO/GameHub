package fr.hugoal.gamehubbackend.controllers;


import fr.hugoal.gamehubbackend.dtos.users.RegisterRequest;
import fr.hugoal.gamehubbackend.dtos.users.UserResponse;
import fr.hugoal.gamehubbackend.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(
            @Valid @RequestBody RegisterRequest request) {

        return userService.register(request);
    }
}
