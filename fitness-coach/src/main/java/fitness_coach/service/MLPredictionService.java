package fitness_coach.service;

import fitness_coach.dto.MLPredictionRequest;
import fitness_coach.dto.MLPredictionResponse;
import fitness_coach.repository.ProgressRecordRepository;
import fitness_coach.repository.UserProfileRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class MLPredictionService {

    private final RestClient restClient;
    private final UserProfileRepository userProfileRepository;
    private final ProgressRecordRepository progressRecordRepository;

    public MLPredictionService(
            UserProfileRepository userProfileRepository,
            ProgressRecordRepository progressRecordRepository
    ) {

        this.userProfileRepository = userProfileRepository;
        this.progressRecordRepository = progressRecordRepository;

        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:5000")
                .build();
    }


    // Sends prediction request to Flask ML service
    public MLPredictionResponse predictWorkout(
            MLPredictionRequest request
    ) {

        return restClient.post()
                .uri("/predict")
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(MLPredictionResponse.class);
    }


    // Automatically gets user data from MySQL
    // and sends it to the ML model
    public MLPredictionResponse getAutomaticRecommendation(
            Long userId
    ) {

        // Get user profile
        var profile = userProfileRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User profile not found")
                );


        // Get latest progress record
        var progress = progressRecordRepository
                .findTopByUserProfileIdOrderByRecordDateDesc(userId)
                .orElseThrow(() ->
                        new RuntimeException("Progress record not found")
                );


        // Calculate BMI
        double heightInMeters =
                profile.getHeightCm() / 100.0;

        double bmi =
                profile.getWeightKg()
                        / (heightInMeters * heightInMeters);


        // Create request for ML model
        MLPredictionRequest request =
                new MLPredictionRequest(

                        profile.getAge(),

                        progress.getWeightKg(),

                        bmi,

                        convertActivityLevel(
                                profile.getActivityLevel()
                        ),

                        progress.getWorkoutDurationMinutes(),

                        progress.getSteps(),

                        progress.getCaloriesConsumed(),

                        5,

                        2
                );


        // Send data to Flask ML service
        return predictWorkout(request);
    }


    // Converts activity level from String to number
    private int convertActivityLevel(
            String activityLevel
    ) {

        if (activityLevel == null) {
            return 1;
        }


        switch (activityLevel.toLowerCase()) {

            case "sedentary":
                return 1;

            case "light":
            case "lightly active":
                return 2;

            case "moderate":
            case "moderately active":
                return 2;

            case "active":
            case "very active":
                return 3;

            default:
                return 1;
        }
    }
}