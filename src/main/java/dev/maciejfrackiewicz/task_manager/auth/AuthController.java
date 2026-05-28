package dev.maciejfrackiewicz.task_manager.auth;


import dev.maciejfrackiewicz.task_manager.auth.dto.AuthResponse;
import dev.maciejfrackiewicz.task_manager.auth.dto.LoginRequest;
import dev.maciejfrackiewicz.task_manager.auth.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;


    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);

    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }
}


