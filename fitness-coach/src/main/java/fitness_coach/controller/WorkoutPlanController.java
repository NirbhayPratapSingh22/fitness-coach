package fitness_coach.controller;

import fitness_coach.entity.UserProfile;
import fitness_coach.repository.UserProfileRepository;
import fitness_coach.service.WorkoutPlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutPlanController {

    private final UserProfileRepository userProfileRepository;
    private final WorkoutPlanService workoutPlanService;

    public WorkoutPlanController(
            UserProfileRepository userProfileRepository,
            WorkoutPlanService workoutPlanService
    ) {
        this.userProfileRepository = userProfileRepository;
        this.workoutPlanService = workoutPlanService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getWorkoutPlan(
            @PathVariable Long id
    ) {

        UserProfile profile =
                userProfileRepository.findById(id).orElse(null);

        if (profile == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> workoutPlan =
                workoutPlanService.generateWorkoutPlan(profile);

        return ResponseEntity.ok(workoutPlan);
    }
}