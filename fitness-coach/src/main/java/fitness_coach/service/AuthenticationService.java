package fitness_coach.service;

import fitness_coach.entity.UserAccount;
import fitness_coach.repository.UserAccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(
            UserAccountRepository userAccountRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserAccount register(UserAccount userAccount) {

        // Check if username already exists
        if (userAccountRepository.existsByUsername(
                userAccount.getUsername())) {

            throw new RuntimeException("Username already exists");
        }

        // Check if email already exists
        if (userAccountRepository.existsByEmail(
                userAccount.getEmail())) {

            throw new RuntimeException("Email already exists");
        }

        // Hash password before saving
        String hashedPassword =
                passwordEncoder.encode(userAccount.getPassword());

        userAccount.setPassword(hashedPassword);

        // Profile will be created later
        // after the user enters their fitness information.
        userAccount.setUserProfileId(null);

        return userAccountRepository.save(userAccount);
    }

    public UserAccount login(String username, String password) {

        UserAccount userAccount =
                userAccountRepository.findByUsername(username)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Invalid username or password"
                                )
                        );

        // Compare entered password with BCrypt hash
        if (!passwordEncoder.matches(
                password,
                userAccount.getPassword()
        )) {

            throw new RuntimeException(
                    "Invalid username or password"
            );
        }

        return userAccount;
    }
}