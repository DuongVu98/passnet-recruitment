package com.iucse.passnet.recruitment.mock;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.entities.JobApplication;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.*;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MockTest {
    private JobAggregateRepository jobAggregateRepository;

    public MockTest(JobAggregateRepository jobAggregateRepository) {
        this.jobAggregateRepository = jobAggregateRepository;
    }

    @Test
    public void testEagerFetch() {
        String jobId = "newAggregateRoot";
        Job newJob = Job.builder()
           .id(new JobId(jobId))
           .courseName(new CourseName("Object-oriented programming"))
           .jobName(new JobName("Find teacher assistance for OOP course"))
           .jobOwner(new UserId("userFromOutside"))
           .semester(new Semester())
           .content(new Content("need a student who have passed OOP with score > 90"))
           .jobRequirement(new JobRequirement("score > 90"))
           .build();
        this.jobAggregateRepository.save(newJob);

        // receive application
        Job currentJob = this.jobAggregateRepository.findByIdWithJobApplications(new JobId(jobId));
        try {
            JobApplication studentApplication = JobApplication.builder()
               .id(new JobApplicationId("hello"))
               .applicationOwner(new UserId("student 1"))
               .applicationState(new ApplicationState(ApplicationStates.PENDING))
               .content(new Content("hello teacher, my name is ..."))
               .letter(new ApplicationLetter("hello teacher again"))
               .build();
            currentJob.receiveJobApplication(studentApplication);
            this.jobAggregateRepository.save(currentJob);
        } catch (Exception e) {
//            log.error("error in testReceiveApplication --> {}", e.getMessage());
        }

        // get applications
        Job jobThatAddedApplication = this.jobAggregateRepository.findByIdWithJobApplications(new JobId("newAggregateRoot"));
        Assert.assertFalse(jobThatAddedApplication.getJobApplications().isEmpty());
    }
}
