package com.iucse.passnet.recruitment;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.entities.JobApplication;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.*;
import com.iucse.passnet.recruitment.domain.models.JobModel;
import com.iucse.passnet.recruitment.domain.models.UserModel;
import com.iucse.passnet.recruitment.domain.repositories.JobRepository;
import com.iucse.passnet.recruitment.domain.repositories.TestRepository;
import com.iucse.passnet.recruitment.domain.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@Slf4j(topic = "[PassnetRecruitmentApplication]")
@SpringBootApplication
public class PassnetRecruitmentApplication implements CommandLineRunner {

    String jobId = "newAggregateRoot";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private TestRepository testRepository;

    public static void main(String[] args) {
        SpringApplication.run(PassnetRecruitmentApplication.class, args);
    }

    public void testAggregateRepository() {
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
    }

    public void testReceiveApplication() {
        log.info("get job");
        Job job = this.testRepository.findAllById(new JobId(jobId));
        try {
            log.info("get job --> {}", job.toString());
            JobApplication studentApplication = JobApplication.builder()
               .id(new JobApplicationId("hello"))
               .applicationOwner(new UserId("student 1"))
               .applicationState(new ApplicationState(ApplicationStates.PENDING))
               .content(new Content("hello teacher, my name is ..."))
               .letter(new ApplicationLetter("hello teacher again"))
               .build();
            job.receiveJobApplication(studentApplication);
            this.testRepository.save(job);
        } catch (Exception e) {
            log.error("error in testReceiveApplication --> {}", e.getMessage());
        }
    }

    public void testRepositoryFind() {
        log.info("get job");
        Job job = this.testRepository.findByIdWithJobApplications(new JobId(jobId));
        try {
            List<JobApplication> applications = job.getJobApplications();
            applications.forEach(application -> {
                log.info("applicationId --> {} and state --> {}", application.getId().getValue(), application.getApplicationState().getValue());
            });
        } catch (Exception e) {
            log.error("error in testRepositoryFind --> {}", e.getMessage());
        }
    }

    public void testAcceptJobApplication(){
        Job job = this.testRepository.findByIdWithJobApplications(new JobId(jobId));
        job.acceptJobApplication(job.getJobApplications().get(0));
        this.testRepository.save(job);
    }

    @Override
    public void run(String... args) throws Exception {

        UserModel student = UserModel.builder()
           .id("student-id")
           .jobApplications(new ArrayList<>())
           .build();
        this.userRepository.save(student);

        UserModel user = UserModel.builder()
           .id("unique-id")
           .build();
        JobModel job1 = JobModel.builder()
           .title("Physics 2")
           .department("Biotechnology")
           .description("blah blah")
           .recruiter(user)
           .build();

        JobModel job2 = JobModel.builder()
           .title("Physics 3")
           .department("Civil engineer")
           .description("blah blah")
           .recruiter(user)
           .build();

        /**
         * save reference side before saving owning side
         */
        this.userRepository.save(user);
        this.jobRepository.save(job1);
        this.jobRepository.save(job2);

        /**
         * set owning side as data of reference side, then save reference side
         */
//        List<JobModel> jobs = List.of(job1, job2);
//        user.setPostedJob(jobs);
//        this.userRepository.save(user);

        /**
         * save new job aggregate
         */
        testAggregateRepository();
        testReceiveApplication();
        testRepositoryFind();
        testAcceptJobApplication();
        testRepositoryFind();
    }
}
