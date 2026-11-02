
package jobscraperagent.jobagent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DailyJobWorkflow {

    private static final Logger log =
            LoggerFactory.getLogger(DailyJobWorkflow.class);

    private final JobCollectionService collectionService;
    private final JobBatchScoringService scoringService;

    public DailyJobWorkflow(
            JobCollectionService collectionService,
            JobBatchScoringService scoringService
    ) {
        this.collectionService = collectionService;
        this.scoringService = scoringService;
    }

    public void runNow(){
       runDailyWorkflow();
    }

    @Scheduled(
            cron = "${job-agent.schedule.cron:0 0 8 * * *}",
            zone = "${job-agent.schedule.zone:Europe/Vilnius}"
    )
    public void runDailyWorkflow() {
        log.info("Starting daily job workflow");

        try {
            int collected = collectionService.collectJobs();
            log.info("Collected {} new jobs", collected);

            int scored = scoringService.scoreUnscoredJobs(10);
            log.info("Scored {} jobs", scored);

            log.info("Daily job workflow completed");
        } catch (Exception e) {
            log.error("Daily job workflow failed", e);
        }
    }
}
