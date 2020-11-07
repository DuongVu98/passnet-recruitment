package com.iucse.passnet.recruitment.domain.aggregate.job.entities;

import com.iucse.passnet.recruitment.domain.aggregate.job.vos.*;
import com.iucse.passnet.recruitment.domain.annotation.DomainFunction;
import com.iucse.passnet.recruitment.domain.exceptions.JobApplicationNotFound;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "jobs")
@NamedQuery(name = "Job.findByIdWithJobApplications", query = "SELECT j from Job j LEFT JOIN FETCH j.jobApplications ja WHERE j.id = :id")
public class Job {

    @EmbeddedId
    private JobId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "job_owner"))
    private UserId jobOwner;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "job_name"))
    private JobName jobName;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "course_name"))
    private CourseName courseName;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "semester"))
    private Semester semester;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "job_requirement"))
    private JobRequirement jobRequirement;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "job_content"))
    private Content content;

    @Builder.Default
    @OneToMany(mappedBy = "job", cascade = {CascadeType.MERGE, CascadeType.REMOVE}, fetch=FetchType.LAZY)
    private List<JobApplication> jobApplications = new ArrayList<>();

    @DomainFunction
    public void receiveJobApplication(JobApplication application){
        application.applyJob(this);
        this.jobApplications.add(application);
    }

    @DomainFunction
    public void acceptJobApplication(JobApplication application){
        if(this.jobApplications.contains(application)){
            this.jobApplications = this.jobApplications.stream().map(currentApplication -> {
                if(currentApplication.getId().equal(application.getId())){
                    currentApplication.accepted();
                }
                return currentApplication;
            }).collect(Collectors.toList());
        } else {
            throw new JobApplicationNotFound(String.format("job application with id: %s not found in this job", application.getId()));
        }
    }
}
