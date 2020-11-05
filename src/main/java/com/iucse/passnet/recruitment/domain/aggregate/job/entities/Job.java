package com.iucse.passnet.recruitment.domain.aggregate.job.entities;

import com.iucse.passnet.recruitment.domain.aggregate.job.vos.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "jobs")
public class Job {

    @EmbeddedId
    private JobId id;

    @Embedded
    @Column(name = "job_owner")
    private UserId jobOwner;

    @Embedded
    @Column(name = "job_name")
    private JobName jobName;

    @Embedded
    @Column(name = "course_name")
    private CourseName courseName;

    @Embedded
    @Column(name = "semester")
    private Semester semester;

    @Embedded
    @Column(name = "job_requirement")
    private JobRequirement jobRequirement;

    @Embedded
    @Column(name = "job_content")
    private Content content;

    @OneToMany
    private List<JobApplication> jobApplications;
}
