package jobscraperagent.jobagent;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/workflow")
public class WorkflowController {
    private final DailyJobWorkflow dailyJobWorkflow;

    public WorkflowController(DailyJobWorkflow dailyJobWorkflow) {
        this.dailyJobWorkflow = dailyJobWorkflow;
    }

    @PostMapping("/run")
    public String runWorkflow(){
        dailyJobWorkflow.runNow();
        return "Workflow Triggered";
    }
}
