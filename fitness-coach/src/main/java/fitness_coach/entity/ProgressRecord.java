package fitness_coach.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "progress_records")
public class ProgressRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userProfileId;

    private LocalDate recordDate;

    private Double weightKg;

    private Integer caloriesConsumed;

    private Integer steps;

    private Integer workoutDurationMinutes;

    private String notes;

    public ProgressRecord() {
    }

    public ProgressRecord(
            Long userProfileId,
            LocalDate recordDate,
            Double weightKg,
            Integer caloriesConsumed,
            Integer steps,
            Integer workoutDurationMinutes,
            String notes
    ) {
        this.userProfileId = userProfileId;
        this.recordDate = recordDate;
        this.weightKg = weightKg;
        this.caloriesConsumed = caloriesConsumed;
        this.steps = steps;
        this.workoutDurationMinutes = workoutDurationMinutes;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(Long userProfileId) {
        this.userProfileId = userProfileId;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    public Double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(Double weightKg) {
        this.weightKg = weightKg;
    }

    public Integer getCaloriesConsumed() {
        return caloriesConsumed;
    }

    public void setCaloriesConsumed(Integer caloriesConsumed) {
        this.caloriesConsumed = caloriesConsumed;
    }

    public Integer getSteps() {
        return steps;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    public Integer getWorkoutDurationMinutes() {
        return workoutDurationMinutes;
    }

    public void setWorkoutDurationMinutes(Integer workoutDurationMinutes) {
        this.workoutDurationMinutes = workoutDurationMinutes;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}