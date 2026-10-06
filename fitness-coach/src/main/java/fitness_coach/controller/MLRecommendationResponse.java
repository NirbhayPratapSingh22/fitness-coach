package fitness_coach.controller;

import java.util.Map;

public class MLRecommendationResponse {

    private Long userId;
    private double bmi;
    private String recommendedWorkout;
    private String explanation;
    private String model;
    private String status;

    private Map<String, Object> workoutPlan;

    public MLRecommendationResponse(
            Long userId,
            double bmi,
            String recommendedWorkout,
            String explanation,
            String model,
            String status,
            Map<String, Object> workoutPlan
    ) {
        this.userId = userId;
        this.bmi = bmi;
        this.recommendedWorkout = recommendedWorkout;
        this.explanation = explanation;
        this.model = model;
        this.status = status;
        this.workoutPlan = workoutPlan;
    }

    public Long getUserId() {
        return userId;
    }

    public double getBmi() {
        return bmi;
    }

    public String getRecommendedWorkout() {
        return recommendedWorkout;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getModel() {
        return model;
    }

    public String getStatus() {
        return status;
    }

    public Map<String, Object> getWorkoutPlan() {
        return workoutPlan;
    }
}