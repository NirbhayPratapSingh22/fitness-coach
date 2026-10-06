package fitness_coach.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Age is required")
    @Min(value = 13, message = "Age must be at least 13")
    private Integer age;

    private String gender;

    @NotNull(message = "Height is required")
    @Positive(message = "Height must be positive")
    private Double heightCm;

    @NotNull(message = "Weight is required")
    @Positive(message = "Weight must be positive")
    private Double weightKg;

    private String fitnessGoal;
    private String activityLevel;
    private String dietaryPreference;

    // ML-related user data
    private Integer trainingFrequency;
    private Integer experienceLevel;
    private Integer averageWorkoutDuration;
    private Integer averageSteps;
    private Integer averageCaloriesConsumed;

    public UserProfile() {
    }

    public UserProfile(
            String name,
            Integer age,
            String gender,
            Double heightCm,
            Double weightKg,
            String fitnessGoal,
            String activityLevel,
            String dietaryPreference,
            Integer trainingFrequency,
            Integer experienceLevel,
            Integer averageWorkoutDuration,
            Integer averageSteps,
            Integer averageCaloriesConsumed
    ) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.heightCm = heightCm;
        this.weightKg = weightKg;
        this.fitnessGoal = fitnessGoal;
        this.activityLevel = activityLevel;
        this.dietaryPreference = dietaryPreference;
        this.trainingFrequency = trainingFrequency;
        this.experienceLevel = experienceLevel;
        this.averageWorkoutDuration = averageWorkoutDuration;
        this.averageSteps = averageSteps;
        this.averageCaloriesConsumed = averageCaloriesConsumed;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getHeightCm() {
        return heightCm;
    }

    public void setHeightCm(Double heightCm) {
        this.heightCm = heightCm;
    }

    public Double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(Double weightKg) {
        this.weightKg = weightKg;
    }

    public String getFitnessGoal() {
        return fitnessGoal;
    }

    public void setFitnessGoal(String fitnessGoal) {
        this.fitnessGoal = fitnessGoal;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public String getDietaryPreference() {
        return dietaryPreference;
    }

    public void setDietaryPreference(String dietaryPreference) {
        this.dietaryPreference = dietaryPreference;
    }

    public Integer getTrainingFrequency() {
        return trainingFrequency;
    }

    public void setTrainingFrequency(Integer trainingFrequency) {
        this.trainingFrequency = trainingFrequency;
    }

    public Integer getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(Integer experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public Integer getAverageWorkoutDuration() {
        return averageWorkoutDuration;
    }

    public void setAverageWorkoutDuration(Integer averageWorkoutDuration) {
        this.averageWorkoutDuration = averageWorkoutDuration;
    }

    public Integer getAverageSteps() {
        return averageSteps;
    }

    public void setAverageSteps(Integer averageSteps) {
        this.averageSteps = averageSteps;
    }

    public Integer getAverageCaloriesConsumed() {
        return averageCaloriesConsumed;
    }

    public void setAverageCaloriesConsumed(Integer averageCaloriesConsumed) {
        this.averageCaloriesConsumed = averageCaloriesConsumed;
    }
}