package fitness_coach.service;

import fitness_coach.entity.UserProfile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DietPlanService {

    public Map<String, Object> generateDietPlan(
            UserProfile profile,
            double targetCalories
    ) {

        String dietaryPreference = profile.getDietaryPreference();

        if (dietaryPreference == null) {
            dietaryPreference = "Vegetarian";
        }

        List<Map<String, Object>> meals;

        if (dietaryPreference.equalsIgnoreCase("non-vegetarian")) {
            meals = nonVegetarianDietPlan();
        } else {
            meals = vegetarianDietPlan();
        }

        Map<String, Object> result = new HashMap<>();

        result.put("name", profile.getName());
        result.put("fitnessGoal", profile.getFitnessGoal());
        result.put("dietaryPreference", dietaryPreference);
        result.put("targetCalories", targetCalories);
        result.put("meals", meals);

        return result;
    }

    private List<Map<String, Object>> vegetarianDietPlan() {

        List<Map<String, Object>> meals = new ArrayList<>();

        meals.add(createMeal(
                "Breakfast",
                "Oats, milk, banana, and peanut butter",
                650,
                25
        ));

        meals.add(createMeal(
                "Mid-Morning Snack",
                "Curd and seasonal fruit",
                250,
                10
        ));

        meals.add(createMeal(
                "Lunch",
                "Roti, rice, dal, paneer, and vegetables",
                750,
                35
        ));

        meals.add(createMeal(
                "Evening Snack",
                "Roasted chana, peanut butter sandwich, and milk",
                450,
                20
        ));

        meals.add(createMeal(
                "Dinner",
                "Roti, soy chunks or paneer, dal, and vegetables",
                600,
                35
        ));

        meals.add(createMeal(
                "Before Bed",
                "A glass of milk",
                180,
                8
        ));

        return meals;
    }

    private List<Map<String, Object>> nonVegetarianDietPlan() {

        List<Map<String, Object>> meals = new ArrayList<>();

        meals.add(createMeal(
                "Breakfast",
                "Oats, milk, banana, and eggs",
                650,
                35
        ));

        meals.add(createMeal(
                "Mid-Morning Snack",
                "Curd and seasonal fruit",
                250,
                10
        ));

        meals.add(createMeal(
                "Lunch",
                "Roti, rice, dal, chicken or fish, and vegetables",
                750,
                45
        ));

        meals.add(createMeal(
                "Evening Snack",
                "Roasted chana, peanut butter sandwich, and milk",
                450,
                20
        ));

        meals.add(createMeal(
                "Dinner",
                "Roti, chicken or eggs, dal, and vegetables",
                600,
                40
        ));

        meals.add(createMeal(
                "Before Bed",
                "A glass of milk",
                180,
                8
        ));

        return meals;
    }

    private Map<String, Object> createMeal(
            String mealName,
            String foods,
            int calories,
            int protein
    ) {

        Map<String, Object> meal = new HashMap<>();

        meal.put("mealName", mealName);
        meal.put("foods", foods);
        meal.put("estimatedCalories", calories);
        meal.put("estimatedProteinGrams", protein);

        return meal;
    }
}