package jobscraperagent.jobagent.source;

import jobscraperagent.jobagent.Job;

import java.util.List;

public interface JobSource {

    String getSourceName();

    List<Job> fetchJobs();

}

