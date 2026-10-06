package fitness_coach.controller;

import fitness_coach.dto.MLPredictionRequest;
import fitness_coach.dto.MLPredictionResponse;
import fitness_coach.service.MLPredictionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class MLPredictionController {

    private final MLPredictionService mlPredictionService;

    public MLPredictionController(
            MLPredictionService mlPredictionService
    ) {
        this.mlPredictionService = mlPredictionService;
    }


    // Manual prediction endpoint
    @PostMapping("/predict-workout")
    public ResponseEntity<MLPredictionResponse> predictWorkout(
            @RequestBody MLPredictionRequest request
    ) {

        MLPredictionResponse response =
                mlPredictionService.predictWorkout(request);

        return ResponseEntity.ok(response);
    }


    // Automatic prediction using user data from MySQL
    @GetMapping("/recommendation/{userId}")
    public ResponseEntity<MLPredictionResponse> getRecommendation(
            @PathVariable Long userId
    ) {

        MLPredictionResponse response =
                mlPredictionService.getAutomaticRecommendation(userId);

        return ResponseEntity.ok(response);
    }
}