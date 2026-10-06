package fitness_coach.controller;

import fitness_coach.entity.UserAccount;
import fitness_coach.service.AuthenticationService;
import fitness_coach.service.JwtService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    public AuthenticationController(
            AuthenticationService authenticationService,
            JwtService jwtService
    ) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public AuthResponse register(
            @RequestBody UserAccount userAccount
    ) {

        UserAccount savedUser =
                authenticationService.register(userAccount);

        return new AuthResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getUserProfileId(),
                "Registration successful",
                null
        );
    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequest request
    ) {

        UserAccount user =
                authenticationService.login(
                        request.username,
                        request.password
                );

        String token = jwtService.generateToken(
                user.getUsername()
        );

        return new AuthResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getUserProfileId(),
                "Login successful",
                token
        );
    }

    public static class LoginRequest {

        public String username;
        public String password;
    }
}