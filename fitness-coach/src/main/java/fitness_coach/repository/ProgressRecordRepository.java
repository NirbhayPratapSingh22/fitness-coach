package fitness_coach.repository;

import fitness_coach.entity.ProgressRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgressRecordRepository
        extends JpaRepository<ProgressRecord, Long> {

    List<ProgressRecord> findByUserProfileId(Long userProfileId);

    Optional<ProgressRecord> findTopByUserProfileIdOrderByRecordDateDesc(Long userProfileId);
}