package jobscraperagent.jobagent;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public List<Job> getJobs() {
        return jobService.getAllJobs();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Job createJob(@Valid @RequestBody JobRequest request) {
        return jobService.createJob(request);
    }

    @GetMapping("/search")
    public List<Job> searchJobs(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String company,
            @RequestParam(required = false)
            @org.springframework.format.annotation.DateTimeFormat(
                    iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE
            )
           LocalDate publishedAfter
    ){
        return jobService.searchJobs(
                title, location, company, publishedAfter
        );
    }
}