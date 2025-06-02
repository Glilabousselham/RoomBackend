package org.glila.room.v1.module.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.glila.room.v1.module.auth.dto.LoginRequest;
import org.glila.room.v1.module.auth.dto.RegisterRequest;
import org.glila.room.v1.module.auth.dto.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterRequest request) throws Exception {
        AuthResponse authResponse = authService.register(request);

        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) throws Exception {
        return authService.login(request);
    }
}
