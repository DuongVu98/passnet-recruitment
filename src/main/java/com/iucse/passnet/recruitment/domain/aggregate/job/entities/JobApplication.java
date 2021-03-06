package com.iucse.passnet.recruitment.domain.aggregate.job.entities;

import com.iucse.passnet.recruitment.domain.aggregate.job.vos.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "job_applications")
@Slf4j(topic = "[JobApplication]")
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

    public void applyJob(Job job){
        this.job = job;
    }

    public void accepted() {
        if (this.applicationState.getValue().equals(ApplicationStates.PENDING)) {
            this.applicationState = new ApplicationState(ApplicationStates.ACCEPTED);
        }
    }
}
