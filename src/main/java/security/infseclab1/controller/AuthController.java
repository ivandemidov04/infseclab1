package security.infseclab1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.infseclab1.domain.dto.LoginRequest;
import security.infseclab1.domain.dto.RegisterRequest;
import security.infseclab1.security.AuthenticationService;
import security.infseclab1.security.JwtAuthenticationResponse;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public JwtAuthenticationResponse register(@RequestBody @Valid RegisterRequest request) {
        return authenticationService.register(request);
    }

    @PostMapping("/login")
    public JwtAuthenticationResponse login(@RequestBody @Valid LoginRequest request) {
        return authenticationService.login(request);
    }
}
