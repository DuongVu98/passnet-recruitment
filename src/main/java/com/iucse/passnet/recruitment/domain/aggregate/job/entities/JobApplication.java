package com.iucse.passnet.recruitment.domain.aggregate.job.entities;

import com.iucse.passnet.recruitment.domain.aggregate.job.vos.*;

import javax.persistence.*;

@Entity
@Table(name = "job_applications")
public class JobApplication {

    @EmbeddedId
    private JobApplicationId id;

    @ManyToOne
    private Job job;

    @Embedded
    @Column(name = "application_content")
    private Content content;

    @Embedded
    @Column(name = "application_letter")
    private ApplicationLetter letter;

    @Embedded
    @Column(name = "application_state")
    private ApplicationState applicationState;

    @Embedded
    @Column(name = "application_owner")
    private UserId applicationOwner;
}
