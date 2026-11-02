package jobscraperagent.jobagent;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/preferences")
public class JobPreferencesController {

    private final JobSearchPreferences preferences;

    public JobPreferencesController(JobSearchPreferences preferences){
        this.preferences = preferences;
    }

    @GetMapping
    public JobSearchPreferences getPreferences(){
        return preferences;
    }
}
