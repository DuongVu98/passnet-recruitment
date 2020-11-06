package com.iucse.passnet.recruitment.domain.aggregate.job.entities;

import com.iucse.passnet.recruitment.domain.aggregate.job.vos.*;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "job_applications")
public class JobApplication {

    @EmbeddedId
    private JobApplicationId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "application_content"))
    private Content content;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "application_letter"))
    private ApplicationLetter letter;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "application_state"))
    private ApplicationState applicationState;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "application_owner"))
    private UserId applicationOwner;
}
