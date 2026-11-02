package jobscraperagent.jobagent.source;

import jobscraperagent.jobagent.Job;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.JsonNode;

import java.util.List;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

@Component
public class ArbeitnowJobSource implements JobSource {

    private final RestClient restClient;

    public ArbeitnowJobSource(RestClient.Builder builder){
        this.restClient = builder.baseUrl("https://www.arbeitnow.com").build();
    }

    @Override
    public String getSourceName() {
        return "Arbeitnow";
    }

    @Override
    public List<Job> fetchJobs() {
        List<Job> jobs = new ArrayList<>();

        int page = 1;
        int maxPages = 100;

        while(page <= maxPages){
            JsonNode response = restClient.get()
                    .uri("/api/job-board-api?page=1")
                    .retrieve()
                    .body(JsonNode.class);

            if(response == null || !response.path("data").isArray()){
                throw new IllegalStateException(
                        "Invalid response from Arbeitnow API"
                );
            };

            for(JsonNode item : response.path("data")){
                String title = item.path("title").asString("");
                String company = item.path("company_name").asString();
                String url = item.path("url").asString();

                // skip incomplete listings
                if(title.isBlank() || company.isBlank() || url.isBlank()){
                    continue;
                }

                String location = item.path("location").asString();

                if(item.path("remote").asBoolean(false)){
                    location = location.isBlank() ? "Remote" : "Remote - " + location;
                }

                String description = item.path("description").asString("");

                LocalDate publishedDate = parsePublishedDate(
                        item.path("created_at")
                );

                String remoteType = item.path("remote").asBoolean(false) ? "Remote" : null;

                Job job = new Job(
                        getSourceName(),
                        null,
                        title,
                        company,
                        url,
                        location,
                        remoteType,
                        url,
                        description,
                        null,
                        null,
                        publishedDate,
                        LocalDateTime.now()
                );

                jobs.add(job);
            }

            int lastPage = response.path("meta")
                    .path("last_page")
                    .asInt(page);

            if(page >= lastPage){
                break;
            }

            page++;
        }

        return jobs;
    }

    private LocalDate parsePublishedDate(JsonNode node){
        if(node == null || node.isNull() || node.isMissingNode()){
            return null;
        }

        if(node.isNumber()){
            return Instant.ofEpochSecond(node.asLong())
                    .atZone(ZoneOffset.UTC)
                    .toLocalDate();
        }

        if(node.isString()){
            String value = node.asString();

            try{
                return Instant.parse(value)
                        .atZone(ZoneOffset.UTC)
                        .toLocalDate();
            }catch (DateTimeParseException ignored){
                // Other date formats?
            }

            try {
                return LocalDateTime.parse(value).toLocalDate();
            }catch(DateTimeParseException ignored){
                // Try a date-only value
            }

            try {
                return LocalDate.parse(value);
            } catch (DateTimeParseException ignored) {
                return null;
            }
        }

        return null;
    }
}
