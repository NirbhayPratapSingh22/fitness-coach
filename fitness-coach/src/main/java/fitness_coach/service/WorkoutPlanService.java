package fitness_coach.service;

import fitness_coach.entity.UserProfile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WorkoutPlanService {

    // =========================================================
    // EXISTING WORKOUT PLAN
    // Uses the user's fitness goal
    // =========================================================

    public Map<String, Object> generateWorkoutPlan(UserProfile profile) {

        String fitnessGoal = profile.getFitnessGoal();

        List<Map<String, Object>> workouts =
                new ArrayList<>();

        if (fitnessGoal == null) {
            fitnessGoal = "General Fitness";
        }

        switch (fitnessGoal.toLowerCase()) {

            case "muscle gain":
                workouts = muscleGainPlan();
                break;

            case "weight loss":
            case "fat loss":
                workouts = fatLossPlan();
                break;

            default:
                workouts = generalFitnessPlan();
                break;
        }

        Map<String, Object> result = new HashMap<>();

        result.put("name", profile.getName());
        result.put("fitnessGoal", profile.getFitnessGoal());
        result.put("activityLevel", profile.getActivityLevel());
        result.put("workouts", workouts);

        return result;
    }


    // =========================================================
    // ML-BASED WORKOUT PLAN
    // Uses the recommendation returned by the ML model
    // =========================================================

    public Map<String, Object> generateMLWorkoutPlan(
            UserProfile profile,
            String recommendation
    ) {

        List<Map<String, Object>> workouts;

        if (recommendation == null) {

            workouts = generalFitnessPlan();

        } else {

            switch (recommendation.toLowerCase()) {

                case "strength_training":
                    workouts = muscleGainPlan();
                    break;

                case "weight_loss":
                case "fat_loss":
                    workouts = fatLossPlan();
                    break;

                default:
                    workouts = generalFitnessPlan();
                    break;
            }
        }

        Map<String, Object> result = new HashMap<>();

        result.put("name", profile.getName());
        result.put("fitnessGoal", profile.getFitnessGoal());
        result.put("activityLevel", profile.getActivityLevel());
        result.put("recommendation", recommendation);
        result.put("workouts", workouts);

        return result;
    }


    // =========================================================
    // MUSCLE GAIN / STRENGTH TRAINING PLAN
    // =========================================================

    private List<Map<String, Object>> muscleGainPlan() {

        List<Map<String, Object>> workouts =
                new ArrayList<>();

        workouts.add(createWorkout(
                "Monday",
                "Chest and Triceps",
                List.of(
                        "Bench Press - 3 sets x 8-12 reps",
                        "Incline Dumbbell Press - 3 sets x 10 reps",
                        "Chest Fly - 3 sets x 12 reps",
                        "Triceps Pushdown - 3 sets x 12 reps",
                        "Overhead Triceps Extension - 3 sets x 12 reps"
                )
        ));

        workouts.add(createWorkout(
                "Tuesday",
                "Back and Biceps",
                List.of(
                        "Lat Pulldown - 3 sets x 10 reps",
                        "Seated Cable Row - 3 sets x 10 reps",
                        "One Arm Dumbbell Row - 3 sets x 10 reps",
                        "Biceps Curl - 3 sets x 12 reps",
                        "Hammer Curl - 3 sets x 12 reps"
                )
        ));

        workouts.add(createWorkout(
                "Wednesday",
                "Rest or Light Cardio",
                List.of(
                        "Walking - 20 to 30 minutes",
                        "Light stretching"
                )
        ));

        workouts.add(createWorkout(
                "Thursday",
                "Legs",
                List.of(
                        "Squats - 3 sets x 8-12 reps",
                        "Leg Press - 3 sets x 10 reps",
                        "Romanian Deadlift - 3 sets x 10 reps",
                        "Leg Curl - 3 sets x 12 reps",
                        "Standing Calf Raise - 3 sets x 15 reps"
                )
        ));

        workouts.add(createWorkout(
                "Friday",
                "Shoulders and Core",
                List.of(
                        "Shoulder Press - 3 sets x 10 reps",
                        "Lateral Raise - 3 sets x 12 reps",
                        "Rear Delt Fly - 3 sets x 12 reps",
                        "Plank - 3 sets x 30-60 seconds",
                        "Leg Raises - 3 sets x 12 reps"
                )
        ));

        workouts.add(createWorkout(
                "Saturday",
                "Optional Full Body",
                List.of(
                        "Push-ups - 3 sets x 12 reps",
                        "Bodyweight Squats - 3 sets x 15 reps",
                        "Pull-ups or Assisted Pull-ups - 3 sets",
                        "Light Cardio - 15 to 20 minutes"
                )
        ));

        workouts.add(createWorkout(
                "Sunday",
                "Rest",
                List.of(
                        "Complete rest",
                        "Light stretching if required"
                )
        ));

        return workouts;
    }


    // =========================================================
    // FAT LOSS PLAN
    // =========================================================

    private List<Map<String, Object>> fatLossPlan() {

        List<Map<String, Object>> workouts =
                new ArrayList<>();

        workouts.add(createWorkout(
                "Monday",
                "Full Body Strength",
                List.of(
                        "Squats - 3 sets x 12 reps",
                        "Push-ups - 3 sets x 12 reps",
                        "Rows - 3 sets x 12 reps",
                        "Plank - 3 sets x 30 seconds"
                )
        ));

        workouts.add(createWorkout(
                "Tuesday",
                "Cardio",
                List.of(
                        "Brisk Walking or Jogging - 30 minutes",
                        "Stretching - 10 minutes"
                )
        ));

        workouts.add(createWorkout(
                "Wednesday",
                "Lower Body",
                List.of(
                        "Lunges - 3 sets x 12 reps",
                        "Squats - 3 sets x 12 reps",
                        "Glute Bridges - 3 sets x 15 reps",
                        "Calf Raises - 3 sets x 15 reps"
                )
        ));

        workouts.add(createWorkout(
                "Thursday",
                "Cardio and Core",
                List.of(
                        "Cycling or Jogging - 25 minutes",
                        "Crunches - 3 sets x 15 reps",
                        "Plank - 3 sets x 30-60 seconds"
                )
        ));

        workouts.add(createWorkout(
                "Friday",
                "Upper Body",
                List.of(
                        "Push-ups - 3 sets x 12 reps",
                        "Dumbbell Rows - 3 sets x 12 reps",
                        "Shoulder Press - 3 sets x 12 reps",
                        "Biceps Curls - 3 sets x 12 reps"
                )
        ));

        workouts.add(createWorkout(
                "Saturday",
                "Light Cardio",
                List.of(
                        "Walking - 30 to 40 minutes",
                        "Mobility exercises"
                )
        ));

        workouts.add(createWorkout(
                "Sunday",
                "Rest",
                List.of(
                        "Complete rest",
                        "Light stretching"
                )
        ));

        return workouts;
    }


    // =========================================================
    // GENERAL FITNESS PLAN
    // =========================================================

    private List<Map<String, Object>> generalFitnessPlan() {

        List<Map<String, Object>> workouts =
                new ArrayList<>();

        workouts.add(createWorkout(
                "Monday",
                "Full Body Workout",
                List.of(
                        "Squats - 3 sets x 12 reps",
                        "Push-ups - 3 sets x 10 reps",
                        "Rows - 3 sets x 12 reps",
                        "Plank - 3 sets x 30 seconds"
                )
        ));

        workouts.add(createWorkout(
                "Wednesday",
                "Cardio",
                List.of(
                        "Walking, jogging, or cycling - 30 minutes"
                )
        ));

        workouts.add(createWorkout(
                "Friday",
                "Full Body Workout",
                List.of(
                        "Lunges - 3 sets x 12 reps",
                        "Shoulder Press - 3 sets x 10 reps",
                        "Glute Bridges - 3 sets x 15 reps",
                        "Plank - 3 sets x 30 seconds"
                )
        ));

        workouts.add(createWorkout(
                "Saturday",
                "Mobility and Stretching",
                List.of(
                        "Full-body stretching - 20 minutes",
                        "Light walking"
                )
        ));

        return workouts;
    }


    // =========================================================
    // HELPER METHOD
    // Creates one workout entry
    // =========================================================

    private Map<String, Object> createWorkout(
            String day,
            String focus,
            List<String> exercises
    ) {

        Map<String, Object> workout =
                new HashMap<>();

        workout.put("day", day);
        workout.put("focus", focus);
        workout.put("exercises", exercises);

        return workout;
    }
}