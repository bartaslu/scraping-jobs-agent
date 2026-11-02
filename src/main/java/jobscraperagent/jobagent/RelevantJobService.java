package jobscraperagent.jobagent;

import jobscraperagent.jobagent.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelevantJobService {

    private final JobRepository jobRepository;
    private final JobSearchPreferences preferences;

    public RelevantJobService(JobRepository jobRepository, JobSearchPreferences preferences) {
        this.jobRepository = jobRepository;
        this.preferences = preferences;
    }

    public List<Job> getRelevantJobs(){
        return jobRepository.findByRelevanceScoreGreaterThanEqualOrderByRelevanceScoreDesc(preferences.minimumRelevanceScore());
    }
}
