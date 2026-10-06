package fitness_coach.controller;

import fitness_coach.entity.UserAccount;
import fitness_coach.entity.UserProfile;
import fitness_coach.repository.UserAccountRepository;
import fitness_coach.service.UserProfileService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class UserProfileController {

    private final UserProfileService userProfileService;
    private final UserAccountRepository userAccountRepository;

    public UserProfileController(
            UserProfileService userProfileService,
            UserAccountRepository userAccountRepository
    ) {
        this.userProfileService = userProfileService;
        this.userAccountRepository = userAccountRepository;
    }

    // Create profile for currently logged-in user
    @PostMapping("/me")
    public ResponseEntity<UserProfile> createMyProfile(
            @Valid @RequestBody UserProfile profile,
            Authentication authentication
    ) {

        UserProfile savedProfile =
                userProfileService.createProfileForUser(
                        profile,
                        authentication.getName()
                );

        return ResponseEntity.ok(savedProfile);
    }

    // Get profile of currently logged-in user
    @GetMapping("/me")
    public ResponseEntity<UserProfile> getMyProfile(
            Authentication authentication
    ) {

        UserAccount userAccount =
                userAccountRepository.findByUsername(
                        authentication.getName()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "User account not found"
                        )
                );

        if (userAccount.getUserProfileId() == null) {
            return ResponseEntity.notFound().build();
        }

        UserProfile profile =
                userProfileService.getProfileById(
                        userAccount.getUserProfileId()
                );

        return ResponseEntity.ok(profile);
    }

    // Existing endpoint - get all profiles
    @GetMapping
    public List<UserProfile> getAllProfiles() {
        return userProfileService.getAllProfiles();
    }

    // Existing endpoint - get profile by ID
    @GetMapping("/{id}")
    public UserProfile getProfileById(
            @PathVariable Long id
    ) {
        return userProfileService.getProfileById(id);
    }

    // Existing endpoint - update profile
    @PutMapping("/{id}")
    public UserProfile updateProfile(
            @PathVariable Long id,
            @Valid @RequestBody UserProfile updatedProfile
    ) {

        return userProfileService.updateProfile(
                id,
                updatedProfile
        );
    }

    // Existing endpoint - delete profile
    @DeleteMapping("/{id}")
    public String deleteProfile(
            @PathVariable Long id
    ) {

        userProfileService.deleteProfile(id);

        return "Profile deleted successfully";
    }
}