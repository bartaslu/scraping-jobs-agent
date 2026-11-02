package jobscraperagent.jobagent;

import jobscraperagent.jobagent.repository.JobRepository;
import org.springframework.stereotype.Service;

@Service
public class JobBatchScoringService {
    private final JobRepository jobRepository;
    private final ClaudeJobScoringService scoringService;

    public JobBatchScoringService(JobRepository jobRepository, ClaudeJobScoringService scoringService) {
        this.jobRepository = jobRepository;
        this.scoringService = scoringService;
    }

    public int scoreUnscoredJobs(int limit){
        if(limit < 1 || limit > 100){
            throw new IllegalArgumentException("Limit must be between 1 and 100");
        }

        var jobs = jobRepository.findByRelevanceScoreIsNull()
                .stream()
                .limit(limit)
                .toList();

        int scoredCount = 0;

        for(Job job : jobs){
            scoringService.scoreJob(job.getId());
            scoredCount++;
        }

        return scoredCount;
    }
}
