package com.iucse.passnet.recruitment.mock;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.entities.JobApplication;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.*;
import com.iucse.passnet.recruitment.domain.models.JobModel;
import com.iucse.passnet.recruitment.domain.models.UserModel;
import com.iucse.passnet.recruitment.domain.repositories.JobRepository;
import com.iucse.passnet.recruitment.domain.repositories.TestRepository;
import com.iucse.passnet.recruitment.domain.repositories.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MockTest {

    private UserRepository userRepository;
    private JobRepository jobRepository;
    private TestRepository testRepository;

    @Autowired
    public MockTest(UserRepository userRepository, JobRepository jobRepository, TestRepository testRepository) {
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
        this.testRepository = testRepository;
    }

    @Test
    public void testRepository(){
        UserModel userModel = userRepository.findFirstById("unique-id");

        Assert.assertNotNull(userModel.getId());
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
        this.testRepository.save(newJob);

        // receive application
        Job currentJob = this.testRepository.findByIdWithJobApplications(new JobId(jobId));
        try {
            JobApplication studentApplication = JobApplication.builder()
               .id(new JobApplicationId("hello"))
               .applicationOwner(new UserId("student 1"))
               .applicationState(new ApplicationState(ApplicationStates.PENDING))
               .content(new Content("hello teacher, my name is ..."))
               .letter(new ApplicationLetter("hello teacher again"))
               .build();
            currentJob.receiveJobApplication(studentApplication);
            this.testRepository.save(currentJob);
        } catch (Exception e) {
//            log.error("error in testReceiveApplication --> {}", e.getMessage());
        }

        // get applications
        Job jobThatAddedApplication = this.testRepository.findByIdWithJobApplications(new JobId("newAggregateRoot"));
        Assert.assertFalse(jobThatAddedApplication.getJobApplications().isEmpty());
    }
}
