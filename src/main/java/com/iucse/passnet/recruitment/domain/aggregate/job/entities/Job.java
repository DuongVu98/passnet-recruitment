package com.iucse.passnet.recruitment.domain.aggregate.job.entities;

import com.iucse.passnet.recruitment.domain.aggregate.job.vos.*;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "jobs")
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

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<JobApplication> jobApplications;
}
