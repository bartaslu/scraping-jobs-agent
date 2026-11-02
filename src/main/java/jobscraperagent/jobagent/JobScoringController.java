package jobscraperagent.jobagent;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobs")
public class JobScoringController {
    private final ClaudeJobScoringService scoringService;

    public JobScoringController(ClaudeJobScoringService scoringService) {
        this.scoringService = scoringService;
    }

    @PostMapping("/{id}/score")
    public JobScoringResult scoreJob(@PathVariable Long id){
        return scoringService.scoreJob(id);
    }
}
