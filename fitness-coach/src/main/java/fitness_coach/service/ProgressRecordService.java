package fitness_coach.service;

import fitness_coach.entity.ProgressRecord;
import fitness_coach.repository.ProgressRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgressRecordService {

    private final ProgressRecordRepository progressRecordRepository;

    public ProgressRecordService(
            ProgressRecordRepository progressRecordRepository
    ) {
        this.progressRecordRepository = progressRecordRepository;
    }

    public ProgressRecord createRecord(ProgressRecord progressRecord) {
        return progressRecordRepository.save(progressRecord);
    }

    public List<ProgressRecord> getAllRecords() {
        return progressRecordRepository.findAll();
    }

    public List<ProgressRecord> getRecordsByUserProfileId(
            Long userProfileId
    ) {
        return progressRecordRepository
                .findByUserProfileId(userProfileId);
    }

    public Optional<ProgressRecord> getRecordById(Long id) {
        return progressRecordRepository.findById(id);
    }

    public ProgressRecord updateRecord(
            Long id,
            ProgressRecord updatedRecord
    ) {

        Optional<ProgressRecord> existingRecord =
                progressRecordRepository.findById(id);

        if (existingRecord.isPresent()) {

            ProgressRecord record = existingRecord.get();

            record.setUserProfileId(
                    updatedRecord.getUserProfileId()
            );

            record.setRecordDate(
                    updatedRecord.getRecordDate()
            );

            record.setWeightKg(
                    updatedRecord.getWeightKg()
            );

            record.setCaloriesConsumed(
                    updatedRecord.getCaloriesConsumed()
            );

            record.setSteps(
                    updatedRecord.getSteps()
            );

            record.setWorkoutDurationMinutes(
                    updatedRecord.getWorkoutDurationMinutes()
            );

            record.setNotes(
                    updatedRecord.getNotes()
            );

            return progressRecordRepository.save(record);
        }

        return null;
    }

    public void deleteRecord(Long id) {
        progressRecordRepository.deleteById(id);
    }
}