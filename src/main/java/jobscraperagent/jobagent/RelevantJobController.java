package jobscraperagent.jobagent;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class RelevantJobController {
    private final RelevantJobService relevantJobService;

    public RelevantJobController(RelevantJobService relevantJobService) {
        this.relevantJobService = relevantJobService;
    }

    @GetMapping("/relevant")
    public List<Job> getRelevantJobs(){
        return relevantJobService.getRelevantJobs();
    }
}
