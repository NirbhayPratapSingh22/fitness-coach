package fitness_coach.controller;

import fitness_coach.entity.UserProfile;
import fitness_coach.repository.UserProfileRepository;
import fitness_coach.service.MLRecommendationService;
import fitness_coach.service.WorkoutPlanService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ml")
public class MLRecommendationController {

    private final MLRecommendationService mlRecommendationService;
    private final UserProfileRepository userProfileRepository;
    private final WorkoutPlanService workoutPlanService;

    public MLRecommendationController(
            MLRecommendationService mlRecommendationService,
            UserProfileRepository userProfileRepository,
            WorkoutPlanService workoutPlanService
    ) {
        this.mlRecommendationService = mlRecommendationService;
        this.userProfileRepository = userProfileRepository;
        this.workoutPlanService = workoutPlanService;
    }

    @GetMapping("/recommend/{userId}")
    public MLRecommendationResponse recommendWorkout(
            @PathVariable Long userId
    ) {

        // =====================================================
        // 1. Get user profile from MySQL
        // =====================================================

        UserProfile profile =
                userProfileRepository.findById(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User profile not found"
                                )
                        );


        // =====================================================
        // 2. Calculate BMI
        // =====================================================

        double heightMeters =
                profile.getHeightCm() / 100.0;

        double bmi =
                profile.getWeightKg() /
                        (heightMeters * heightMeters);


        // =====================================================
        // 3. Convert activity level
        // =====================================================

        int activityLevel =
                convertActivityLevel(
                        profile.getActivityLevel()
                );


        // =====================================================
        // 4. Get training information
        // =====================================================

        int trainingFrequency =
                profile.getTrainingFrequency();

        int experienceLevel =
                profile.getExperienceLevel();

        int workoutDuration =
                profile.getAverageWorkoutDuration();

        int steps =
                profile.getAverageSteps();

        int caloriesConsumed =
                profile.getAverageCaloriesConsumed();


        // =====================================================
        // 5. Send data to Python ML service
        // =====================================================

        Map<String, Object> mlResponse =
                mlRecommendationService.getWorkoutRecommendation(
                        profile.getAge(),
                        profile.getWeightKg(),
                        bmi,
                        activityLevel,
                        workoutDuration,
                        steps,
                        caloriesConsumed,
                        trainingFrequency,
                        experienceLevel
                );


        // =====================================================
        // 6. Get ML prediction
        // =====================================================

        String recommendation =
                String.valueOf(
                        mlResponse.get("recommended_workout")
                );

        String explanation =
                String.valueOf(
                        mlResponse.get("explanation")
                );

        String model =
                String.valueOf(
                        mlResponse.get("model")
                );

        String status =
                String.valueOf(
                        mlResponse.get("status")
                );


        // =====================================================
        // 7. Generate workout plan based on ML prediction
        // =====================================================

        Map<String, Object> workoutPlan =
                workoutPlanService.generateMLWorkoutPlan(
                        profile,
                        recommendation
                );


        // =====================================================
        // 8. Return complete recommendation
        // =====================================================

        return new MLRecommendationResponse(
                profile.getId(),
                Math.round(bmi * 100.0) / 100.0,
                recommendation,
                explanation,
                model,
                status,
                workoutPlan
        );
    }


    // =========================================================
    // Convert activity level into number for ML model
    // =========================================================

    private int convertActivityLevel(
            String activityLevel
    ) {

        if (activityLevel == null) {
            return 2;
        }

        return switch (activityLevel.toLowerCase()) {

            case "sedentary" -> 1;

            case "lightly active" -> 2;

            case "moderately active" -> 2;

            case "very active" -> 3;

            default -> 2;
        };
    }
}