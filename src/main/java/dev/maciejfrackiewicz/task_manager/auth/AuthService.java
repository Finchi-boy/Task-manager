package dev.maciejfrackiewicz.task_manager.auth;

import dev.maciejfrackiewicz.task_manager.auth.dto.AuthResponse;
import dev.maciejfrackiewicz.task_manager.auth.dto.LoginRequest;
import dev.maciejfrackiewicz.task_manager.auth.dto.RegisterRequest;
import dev.maciejfrackiewicz.task_manager.user.User;
import dev.maciejfrackiewicz.task_manager.user.UserRepository;
import dev.maciejfrackiewicz.task_manager.user.UserService;
import dev.maciejfrackiewicz.task_manager.user.dto.CreateUserRequest;
import dev.maciejfrackiewicz.task_manager.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest request)
    {
        User user = User.builder()
                .email(request.email())
                .username(request.username())
                .passwordHash(passwordEncoder.encode(request.password()))
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user.getId());

        return new AuthResponse(token);
    }


    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(user.getId());

        return new AuthResponse(token);
    }

}
