package fitness_coach.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class MLRecommendationService {

    private final RestClient restClient;

    public MLRecommendationService(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("http://localhost:5000")
                .build();
    }

    public Map<String, Object> getWorkoutRecommendation(
            int age,
            double weightKg,
            double bmi,
            int activityLevel,
            int workoutDuration,
            int steps,
            int caloriesConsumed,
            int trainingFrequency,
            int experienceLevel
    ) {

        Map<String, Object> requestBody = Map.of(
                "age", age,
                "weight_kg", weightKg,
                "bmi", bmi,
                "activity_level", activityLevel,
                "workout_duration", workoutDuration,
                "steps", steps,
                "calories_consumed", caloriesConsumed,
                "training_frequency", trainingFrequency,
                "experience_level", experienceLevel
        );

        Map<String, Object> response = restClient.post()
                .uri("/predict")
                .body(requestBody)
                .retrieve()
                .body(Map.class);

        return response;
    }
}