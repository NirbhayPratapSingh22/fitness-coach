package fitness_coach.controller;

import fitness_coach.entity.ProgressRecord;
import fitness_coach.service.ProgressRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
public class ProgressRecordController {

    private final ProgressRecordService progressRecordService;

    public ProgressRecordController(
            ProgressRecordService progressRecordService
    ) {
        this.progressRecordService = progressRecordService;
    }

    @PostMapping
    public ProgressRecord createRecord(
            @RequestBody ProgressRecord progressRecord
    ) {
        return progressRecordService.createRecord(progressRecord);
    }

    @GetMapping
    public List<ProgressRecord> getAllRecords() {
        return progressRecordService.getAllRecords();
    }

    @GetMapping("/user/{userProfileId}")
    public List<ProgressRecord> getRecordsByUserProfileId(
            @PathVariable Long userProfileId
    ) {
        return progressRecordService
                .getRecordsByUserProfileId(userProfileId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgressRecord> getRecordById(
            @PathVariable Long id
    ) {
        return progressRecordService.getRecordById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgressRecord> updateRecord(
            @PathVariable Long id,
            @RequestBody ProgressRecord updatedRecord
    ) {
        ProgressRecord record =
                progressRecordService.updateRecord(id, updatedRecord);

        if (record == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(record);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(
            @PathVariable Long id
    ) {
        progressRecordService.deleteRecord(id);
        return ResponseEntity.noContent().build();
    }
}