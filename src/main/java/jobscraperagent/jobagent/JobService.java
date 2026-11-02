package jobscraperagent.jobagent;

import jobscraperagent.jobagent.repository.JobRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
public class JobService {
    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }

    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    }

    @Transactional
    public Job createJob(JobRequest request){
        String url = request.postingUrl();

        // Normalizing empty URLs to null
        if(url != null){
            url = url.trim();

            if(url.isEmpty()){
                url = null;
            }
        }

        if(url != null && jobRepository.existsByPostingUrl(url)){
            return jobRepository.findByPostingUrl(url).orElseThrow();
        }

        Job job = new Job(
                request.source(),
                request.title(),
                request.company(),
                request.companyUrl(),
                request.location(),
                request.remoteType(),
                url,
                request.description(),
                request.relevanceScore(),
                request.scoredAt(),
                request.publishedDate(),
                null
        );

        return jobRepository.save(job);
    }

    public List<Job> searchJobs(
            String title,
            String location,
            String company,
            java.time.LocalDate publishedAfter
    ){
        return jobRepository.findAll().stream()
                .filter(job -> title == null ||
                    (job.getTitle() != null && job.getTitle().toLowerCase().contains(title.toLowerCase())))
                .filter(job -> location == null ||
                        (job.getLocation() != null && job.getLocation().toLowerCase().contains(location.toLowerCase())))
                .filter(job -> company == null ||
                        (job.getCompany() != null && job.getCompany().toLowerCase().contains(company.toLowerCase())))
                .filter(job -> publishedAfter == null ||
                        (job.getPublishedDate() != null && !job.getPublishedDate().isBefore(publishedAfter)))
                .toList();
    }

}
