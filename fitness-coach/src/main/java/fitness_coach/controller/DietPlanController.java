package fitness_coach.controller;

import fitness_coach.entity.UserProfile;
import fitness_coach.repository.UserProfileRepository;
import fitness_coach.service.DietPlanService;
import fitness_coach.service.FitnessCalculationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/diet")
public class DietPlanController {

    private final UserProfileRepository userProfileRepository;
    private final DietPlanService dietPlanService;
    private final FitnessCalculationService fitnessCalculationService;

    public DietPlanController(
            UserProfileRepository userProfileRepository,
            DietPlanService dietPlanService,
            FitnessCalculationService fitnessCalculationService
    ) {
        this.userProfileRepository = userProfileRepository;
        this.dietPlanService = dietPlanService;
        this.fitnessCalculationService = fitnessCalculationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getDietPlan(
            @PathVariable Long id
    ) {

        UserProfile profile =
                userProfileRepository.findById(id).orElse(null);

        if (profile == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> fitnessData =
                fitnessCalculationService.calculateFitnessData(profile);

        double targetCalories =
                ((Number) fitnessData.get("targetCalories")).doubleValue();

        Map<String, Object> dietPlan =
                dietPlanService.generateDietPlan(
                        profile,
                        targetCalories
                );

        return ResponseEntity.ok(dietPlan);
    }
}