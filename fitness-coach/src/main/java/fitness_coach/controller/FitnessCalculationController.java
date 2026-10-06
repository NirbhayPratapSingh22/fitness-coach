package fitness_coach.controller;

import fitness_coach.entity.UserProfile;
import fitness_coach.repository.UserProfileRepository;
import fitness_coach.service.FitnessCalculationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/fitness")
public class FitnessCalculationController {

    private final UserProfileRepository userProfileRepository;
    private final FitnessCalculationService fitnessCalculationService;

    public FitnessCalculationController(
            UserProfileRepository userProfileRepository,
            FitnessCalculationService fitnessCalculationService
    ) {
        this.userProfileRepository = userProfileRepository;
        this.fitnessCalculationService = fitnessCalculationService;
    }

    @GetMapping("/{id}/calculate")
    public ResponseEntity<Map<String, Object>> calculateFitness(
            @PathVariable Long id
    ) {

        UserProfile profile =
                userProfileRepository.findById(id).orElse(null);

        if (profile == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> result =
                fitnessCalculationService.calculateFitnessData(profile);

        return ResponseEntity.ok(result);
    }
}