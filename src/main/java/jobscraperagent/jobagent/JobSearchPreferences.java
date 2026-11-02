package jobscraperagent.jobagent;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix="job-search")
public record JobSearchPreferences(
    List<String> targetRoles,
    List<String> technologies,
    List<String> locations,
    List<String> seniority,
    int minimumRelevanceScore) {}
