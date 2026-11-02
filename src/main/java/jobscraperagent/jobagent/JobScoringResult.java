package jobscraperagent.jobagent;

import java.util.List;

public record JobScoringResult(
        int score,
        String explanation,
        List<String> matchedTechnologies
){}
