package security.infseclab1.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import security.infseclab1.domain.dto.RegisterRequest;
import security.infseclab1.domain.dto.LoginRequest;
import security.infseclab1.domain.model.User;
import security.infseclab1.error.InvalidUpdateException;
import security.infseclab1.error.UserAlreadyExistsException;
import security.infseclab1.service.UserService;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse register(RegisterRequest request) {

        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .status(request.getStatus())
                .build();

        userService.create(user);

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse login(LoginRequest request) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            ));

            var user = userService
                    .userDetailsService()
                    .loadUserByUsername(request.getUsername());

            var jwt = jwtService.generateToken(user);
            return new JwtAuthenticationResponse(jwt);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Неверное имя пользователя или пароль");
        }
    }
}
