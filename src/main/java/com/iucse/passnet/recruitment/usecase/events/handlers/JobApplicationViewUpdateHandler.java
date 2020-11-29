package com.iucse.passnet.recruitment.usecase.events.handlers;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.viewrepos.JobApplicationViewRepository;
import com.iucse.passnet.recruitment.usecase.events.events.DomainEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j(topic = "[JobApplicationViewUpdateHandler]")
public class JobApplicationViewUpdateHandler implements IEventHandler {

    private final JobApplicationViewRepository jobApplicationViewRepository;

    @Autowired
    public JobApplicationViewUpdateHandler(JobApplicationViewRepository jobApplicationViewRepository) {
        this.jobApplicationViewRepository = jobApplicationViewRepository;
    }

    @Override
    public void handle(DomainEvent event) {
        switch (event.getEventTypes()) {
            case TeacherPostedJob:
                break;
            case StudentAppliedJob:
                this.updateFromAggregate(event.getAggregate());
                break;
            case TeacherAcceptedJob:
                break;
        }
    }

    private void updateFromAggregate(Job aggregate) {

    }
}
