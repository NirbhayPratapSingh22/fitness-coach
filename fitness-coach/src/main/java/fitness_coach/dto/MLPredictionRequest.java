package fitness_coach.dto;

public class MLPredictionRequest {

    private int age;
    private double weight_kg;
    private double bmi;
    private int activity_level;
    private int workout_duration;
    private int steps;
    private int calories_consumed;
    private int training_frequency;
    private int experience_level;

    public MLPredictionRequest() {
    }

    public MLPredictionRequest(
            int age,
            double weight_kg,
            double bmi,
            int activity_level,
            int workout_duration,
            int steps,
            int calories_consumed,
            int training_frequency,
            int experience_level
    ) {
        this.age = age;
        this.weight_kg = weight_kg;
        this.bmi = bmi;
        this.activity_level = activity_level;
        this.workout_duration = workout_duration;
        this.steps = steps;
        this.calories_consumed = calories_consumed;
        this.training_frequency = training_frequency;
        this.experience_level = experience_level;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight_kg() {
        return weight_kg;
    }

    public void setWeight_kg(double weight_kg) {
        this.weight_kg = weight_kg;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public int getActivity_level() {
        return activity_level;
    }

    public void setActivity_level(int activity_level) {
        this.activity_level = activity_level;
    }

    public int getWorkout_duration() {
        return workout_duration;
    }

    public void setWorkout_duration(int workout_duration) {
        this.workout_duration = workout_duration;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public int getCalories_consumed() {
        return calories_consumed;
    }

    public void setCalories_consumed(int calories_consumed) {
        this.calories_consumed = calories_consumed;
    }

    public int getTraining_frequency() {
        return training_frequency;
    }

    public void setTraining_frequency(int training_frequency) {
        this.training_frequency = training_frequency;
    }

    public int getExperience_level() {
        return experience_level;
    }

    public void setExperience_level(int experience_level) {
        this.experience_level = experience_level;
    }
}