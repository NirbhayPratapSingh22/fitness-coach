package fitness_coach.dto;

public class WorkoutExercise {

    private String name;
    private int sets;
    private String reps;
    private int restSeconds;

    public WorkoutExercise(
            String name,
            int sets,
            String reps,
            int restSeconds
    ) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.restSeconds = restSeconds;
    }

    public String getName() {
        return name;
    }

    public int getSets() {
        return sets;
    }

    public String getReps() {
        return reps;
    }

    public int getRestSeconds() {
        return restSeconds;
    }
}