package jobscraperagent.jobagent;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jobs")
public class JobBatchScoringController {
    private final JobBatchScoringService batchScoringService ;

    public JobBatchScoringController(JobBatchScoringService batchScoringService) {
        this.batchScoringService = batchScoringService;
    }

    @PostMapping("/score-unscored")
    public int scoreUnscoredJobs( @RequestParam(defaultValue = "10") int limit){
        return batchScoringService.scoreUnscoredJobs(limit);
    }
}
