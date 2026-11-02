package jobscraperagent.jobagent;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/jobs")
public class JobCollectionController {
    private final JobCollectionService collectionService;

    public JobCollectionController(JobCollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @PostMapping("/collect")
    public CollectionResult collectJobs(){
        int savedCount = collectionService.collectJobs();

        return new CollectionResult(savedCount);
    }

    public record CollectionResult(int savedCount) {}
}
