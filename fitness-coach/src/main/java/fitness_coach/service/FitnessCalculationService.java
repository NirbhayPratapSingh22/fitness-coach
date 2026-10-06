package fitness_coach.service;

import fitness_coach.entity.UserProfile;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FitnessCalculationService {

    public Map<String, Object> calculateFitnessData(UserProfile profile) {

        double heightInMeters = profile.getHeightCm() / 100;

        double bmi = profile.getWeightKg()
                / (heightInMeters * heightInMeters);

        String bmiCategory = getBmiCategory(bmi);

        double bmr = calculateBmr(profile);

        double activityMultiplier =
                getActivityMultiplier(profile.getActivityLevel());

        double maintenanceCalories = bmr * activityMultiplier;

        double targetCalories =
                calculateTargetCalories(
                        maintenanceCalories,
                        profile.getFitnessGoal()
                );

        Map<String, Object> result = new HashMap<>();

        result.put("name", profile.getName());
        result.put("bmi", roundToTwoDecimals(bmi));
        result.put("bmiCategory", bmiCategory);
        result.put("bmr", roundToTwoDecimals(bmr));
        result.put(
                "maintenanceCalories",
                roundToTwoDecimals(maintenanceCalories)
        );
        result.put(
                "targetCalories",
                roundToTwoDecimals(targetCalories)
        );

        return result;
    }

    private String getBmiCategory(double bmi) {

        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal weight";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    private double calculateBmr(UserProfile profile) {

        double weight = profile.getWeightKg();
        double height = profile.getHeightCm();
        int age = profile.getAge();

        if (profile.getGender().equalsIgnoreCase("Male")) {
            return (10 * weight)
                    + (6.25 * height)
                    - (5 * age)
                    + 5;
        } else {
            return (10 * weight)
                    + (6.25 * height)
                    - (5 * age)
                    - 161;
        }
    }

    private double getActivityMultiplier(String activityLevel) {

        if (activityLevel == null) {
            return 1.2;
        }

        return switch (activityLevel.toLowerCase()) {
            case "sedentary" -> 1.2;
            case "lightly active" -> 1.375;
            case "moderately active" -> 1.55;
            case "very active" -> 1.725;
            case "extra active" -> 1.9;
            default -> 1.2;
        };
    }

    private double calculateTargetCalories(
            double maintenanceCalories,
            String fitnessGoal
    ) {

        if (fitnessGoal == null) {
            return maintenanceCalories;
        }

        return switch (fitnessGoal.toLowerCase()) {
            case "weight loss" ->
                    maintenanceCalories - 400;

            case "muscle gain" ->
                    maintenanceCalories + 300;

            case "weight gain" ->
                    maintenanceCalories + 300;

            case "fat loss" ->
                    maintenanceCalories - 400;

            default ->
                    maintenanceCalories;
        };
    }

    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}