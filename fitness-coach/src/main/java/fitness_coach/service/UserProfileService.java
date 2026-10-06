package fitness_coach.service;

import fitness_coach.entity.UserAccount;
import fitness_coach.entity.UserProfile;
import fitness_coach.repository.UserAccountRepository;
import fitness_coach.repository.UserProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserAccountRepository userAccountRepository;

    public UserProfileService(
            UserProfileRepository userProfileRepository,
            UserAccountRepository userAccountRepository
    ) {
        this.userProfileRepository = userProfileRepository;
        this.userAccountRepository = userAccountRepository;
    }

    public UserProfile createProfile(UserProfile profile) {
        return userProfileRepository.save(profile);
    }

    @Transactional
    public UserProfile createProfileForUser(
            UserProfile profile,
            String username
    ) {

        UserAccount userAccount =
                userAccountRepository.findByUsername(username)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User account not found"
                                ));

        if (userAccount.getUserProfileId() != null) {
            throw new RuntimeException(
                    "Profile already exists for this user"
            );
        }

        UserProfile savedProfile =
                userProfileRepository.save(profile);

        userAccount.setUserProfileId(savedProfile.getId());

        userAccountRepository.save(userAccount);

        return savedProfile;
    }

    public List<UserProfile> getAllProfiles() {
        return userProfileRepository.findAll();
    }

    public UserProfile getProfileById(Long id) {
        return userProfileRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Profile not found with id: " + id
                        ));
    }

    public UserProfile updateProfile(
            Long id,
            UserProfile updatedProfile
    ) {

        UserProfile existingProfile =
                userProfileRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Profile not found with id: " + id
                                ));

        existingProfile.setName(updatedProfile.getName());
        existingProfile.setAge(updatedProfile.getAge());
        existingProfile.setGender(updatedProfile.getGender());
        existingProfile.setHeightCm(updatedProfile.getHeightCm());
        existingProfile.setWeightKg(updatedProfile.getWeightKg());
        existingProfile.setFitnessGoal(updatedProfile.getFitnessGoal());
        existingProfile.setActivityLevel(updatedProfile.getActivityLevel());
        existingProfile.setDietaryPreference(
                updatedProfile.getDietaryPreference()
        );
        existingProfile.setTrainingFrequency(
                updatedProfile.getTrainingFrequency()
        );
        existingProfile.setExperienceLevel(
                updatedProfile.getExperienceLevel()
        );
        existingProfile.setAverageWorkoutDuration(
                updatedProfile.getAverageWorkoutDuration()
        );
        existingProfile.setAverageSteps(
                updatedProfile.getAverageSteps()
        );
        existingProfile.setAverageCaloriesConsumed(
                updatedProfile.getAverageCaloriesConsumed()
        );

        return userProfileRepository.save(existingProfile);
    }

    public void deleteProfile(Long id) {

        userProfileRepository.deleteById(id);
    }
}