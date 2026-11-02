
package jobscraperagent.jobagent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workflow")
public class DailyJobWorkflowController {

    private static final Logger log = LoggerFactory.getLogger(DailyJobWorkflowController.class);

    private final DailyJobWorkflow workflow;
    private final JobDigestService digestService;

    public DailyJobWorkflowController(DailyJobWorkflow workflow, JobDigestService digestService) {
        this.workflow = workflow;
        this.digestService = digestService;
    }
}
