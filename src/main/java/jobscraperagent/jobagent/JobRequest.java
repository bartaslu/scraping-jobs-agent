package jobscraperagent.jobagent;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record JobRequest(
        @NotBlank String source,
        @NotBlank String title,
        @NotBlank String company,
        @NotBlank String companyUrl,
        String location,
        String remoteType,
        @NotBlank String postingUrl,
        String description,
        Integer relevanceScore,
        LocalDate scoredAt,
        LocalDate publishedDate) {}
