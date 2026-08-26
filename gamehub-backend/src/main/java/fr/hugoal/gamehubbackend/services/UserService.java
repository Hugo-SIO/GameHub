package fr.hugoal.gamehubbackend.services;

import fr.hugoal.gamehubbackend.dtos.users.LoginRequest;
import fr.hugoal.gamehubbackend.dtos.users.LoginResponse;
import fr.hugoal.gamehubbackend.dtos.users.RegisterRequest;
import fr.hugoal.gamehubbackend.dtos.users.UserResponse;
import fr.hugoal.gamehubbackend.models.User;
import fr.hugoal.gamehubbackend.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UserResponse register(RegisterRequest request) {

        if (userRepository.existsByUsername(request.username())) {
            throw new IllegalArgumentException("Username already exists");
        }

        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role("USER")
                .build();

        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository
                .findByUsername(request.username())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Invalid username or password"
                        )
                );

        if (!passwordEncoder.matches(
                request.password(),
                user.getPassword()
        )) {
            throw new IllegalArgumentException(
                    "Invalid username or password"
            );
        }

        String token = jwtService.generateToken(user);

        return new LoginResponse(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                token
        );
    }
}