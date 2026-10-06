package fitness_coach.dto;

public class MLPredictionResponse {

    private String recommended_workout;

    public MLPredictionResponse() {
    }

    public String getRecommended_workout() {
        return recommended_workout;
    }

    public void setRecommended_workout(String recommended_workout) {
        this.recommended_workout = recommended_workout;
    }
}