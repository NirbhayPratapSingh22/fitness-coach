package fitness_coach.dto;

import java.util.List;

public class WorkoutPlan {

    private String name;
    private int duration;
    private String intensity;
    private List<WorkoutExercise> exercises;

    public WorkoutPlan(
            String name,
            int duration,
            String intensity,
            List<WorkoutExercise> exercises
    ) {
        this.name = name;
        this.duration = duration;
        this.intensity = intensity;
        this.exercises = exercises;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public String getIntensity() {
        return intensity;
    }

    public List<WorkoutExercise> getExercises() {
        return exercises;
    }
}