package fr.hugoal.gamehubbackend.services;

import fr.hugoal.gamehubbackend.models.User;
import fr.hugoal.gamehubbackend.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(
            String username,
            String email,
            String password){
        if(userRepository.existsByUsername(username)){
            throw new IllegalArgumentException("Username already exists");
        }

        if(userRepository.existsByEmail(email)){
            throw new IllegalArgumentException("Email already exists");
        }

        User user = User.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .role("USER")
                .build();

        return userRepository.save(user);
    }
}
