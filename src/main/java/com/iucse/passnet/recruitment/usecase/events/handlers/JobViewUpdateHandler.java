package com.iucse.passnet.recruitment.usecase.events.handlers;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.views.JobView;
import com.iucse.passnet.recruitment.domain.views.LiteJobApplicationView;
import com.iucse.passnet.recruitment.usecase.events.events.DomainEvent;
import com.iucse.passnet.recruitment.usecase.events.events.TeacherPostedJobEventPayload;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class JobViewUpdateHandler extends AbstractEventHandler {

    private DomainEvent event;

    @Override
    public void handle() {
        switch (event.getEventTypes()) {
            case TeacherPostedJob:
                this.updateFromJob(((TeacherPostedJobEventPayload) event.getEventPayload()).getJob());
            case StudentAppliedJob:
            case TeacherAcceptedJob:
        }
    }

    private void updateFromJob(Job job) {

        List<LiteJobApplicationView> liteJobApplicationViews = job.getJobApplications().stream().map(
           application ->
              LiteJobApplicationView.builder()
                 .studentId(application.getApplicationOwner().getValue())
                 .applicationState(application.getApplicationState().getValue().name())
                 .build()
        ).collect(Collectors.toList());

        JobView newJobView = JobView.builder()
           .id(job.getId().getValue())
           .teacherId(job.getJobOwner().getValue())
           .courseName(job.getCourseName().getValue())
           .jobTitle(job.getJobName().getValue())
           .requirement(job.getJobRequirement().getValue())
           .content(job.getContent().getValue())
           .semester(job.getSemester().getValue())
           .jobApplicationsView(liteJobApplicationViews)
           .build();

        this.jobViewRepository.save(newJobView);
    }
}
