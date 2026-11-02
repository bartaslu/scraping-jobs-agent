package jobscraperagent.jobagent;

import jobscraperagent.jobagent.repository.JobRepository;
import jobscraperagent.jobagent.source.JobSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JobCollectionService {
    private final List<JobSource> jobSources;
    private final JobRepository jobRepository;

    public JobCollectionService(
            List<JobSource> jobSources,
            JobRepository jobRepository
    ){
        this.jobSources = jobSources;
        this.jobRepository = jobRepository;
    }

    @Transactional
    public int collectJobs(){
        int savedCount = 0;

        for(JobSource jobSource : jobSources){
            List<Job> jobs = jobSource.fetchJobs();

            for(Job job : jobs){
                String source = job.getSource();
                String externalId = job.getExternalId();
                String url = job.getPostingUrl();

                if(source != null && externalId != null && !externalId.isBlank() && jobRepository.existsBySourceAndExternalId(source, externalId)){
                    continue;
                }

                if(url != null && !url.isBlank() && jobRepository.existsByPostingUrl(url))
                    continue;

                jobRepository.save(job);
                savedCount++;
            }
        }

        return savedCount;
    }
}
