package jobscraperagent.jobagent.repository;

import jobscraperagent.jobagent.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.time.LocalDate;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    Optional<Job> findByPostingUrl(String postingUrl);

    boolean existsByPostingUrl(String postingUrl);
    boolean existsBySourceAndExternalId(
            String source,
            String externalId
    );


    List<Job> findByTitleContainingIgnoreCase(String title);
    List<Job> findByLocationContainingIgnoreCase(String location);
    List<Job> findByCompanyContainingIgnoreCase(String company);
    List<Job> findByPublishedDateGreaterThanEqual(LocalDate publishedDate);
    List<Job> findByRelevanceScoreIsNull();
    List<Job> findByRelevanceScoreGreaterThanEqualOrderByRelevanceScoreDesc(
            Integer minimumScore
    );
    List<Job> findByRelevanceScoreGreaterThanEqualAndNotificationSentAtIsNullOrderByRelevanceScoreDesc(
            Integer minimumScore
    );
}


