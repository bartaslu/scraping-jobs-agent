package jobscraperagent.jobagent;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Column;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String source;
    private String externalId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String company;
    @Column(nullable = false)
    private String companyUrl;
    private String location;
    private String remoteType;
    @Column(nullable = false)
    private String postingUrl;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Integer relevanceScore;
    @Column(columnDefinition = "TEXT")
    private String relevanceExplanation;
    @Column(columnDefinition = "TEXT")
    private String matchedTechnologies;
    private LocalDate scoredAt;
    @Column private Instant notificationSentAt;
    private LocalDate publishedDate;
    private LocalDateTime createdAt;


    public Job() {
    }

    public Job(String source, String title, String company, String companyUrl, String location, String remoteType, String postingUrl, String description, Integer relevanceScore, LocalDate scoredAt, LocalDate publishedDate, LocalDateTime createdAt) {
        this.source = source;
        this.title = title;
        this.company = company;
        this.companyUrl = companyUrl;
        this.location = location;
        this.remoteType = remoteType;
        this.postingUrl = postingUrl;
        this.description = description;
        this.relevanceScore = relevanceScore;
        this.scoredAt = scoredAt;
        this.publishedDate = publishedDate;
        this.createdAt = createdAt;
    }

    public Job(String source, String externalId, String title, String company, String companyUrl, String location, String remoteType, String postingUrl, String description, Integer relevanceScore, LocalDate scoredAt, LocalDate publishedDate, LocalDateTime createdAt) {
        this.source = source;
        this.externalId = externalId;
        this.title = title;
        this.company = company;
        this.companyUrl = companyUrl;
        this.location = location;
        this.remoteType = remoteType;
        this.postingUrl = postingUrl;
        this.description = description;
        this.relevanceScore = relevanceScore;
        this.scoredAt = scoredAt;
        this.publishedDate = publishedDate;
        this.createdAt = createdAt;
    }

    @PrePersist
    public void beforeInsert(){
        if(createdAt == null)
            createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getSource() {
        return source;
    }
    public String getExternalId() {
        return externalId;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public String getLocation() {
        return location;
    }

    public String getRemoteType() {
        return remoteType;
    }

    public String getPostingUrl() {
        return postingUrl;
    }

    public String getDescription() {
        return description;
    }

    public Integer getRelevanceScore() {
        return relevanceScore;
    }

    public LocalDate getScoredAt() {
        return scoredAt;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getMatchedTechnologies() {
        return matchedTechnologies;
    }

    public void setMatchedTechnologies(String matchedTechnologies) {
        this.matchedTechnologies = matchedTechnologies;
    }

    public String getRelevanceExplanation() {
        return relevanceExplanation;
    }

    public void setRelevanceExplanation(String relevanceExplanation) {
        this.relevanceExplanation = relevanceExplanation;
    }

    public void setRelevanceScore(Integer relevanceScore) {
        this.relevanceScore = relevanceScore;
    }

    public Instant getNotificationSentAt() {
        return notificationSentAt;
    }

    public void setNotificationSentAt(Instant notificationSentAt) {
        this.notificationSentAt = notificationSentAt;
    }
}