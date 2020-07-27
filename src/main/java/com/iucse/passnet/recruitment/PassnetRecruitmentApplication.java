package com.iucse.passnet.recruitment;

import com.iucse.passnet.recruitment.domain.models.JobModel;
import com.iucse.passnet.recruitment.domain.models.UserModel;
import com.iucse.passnet.recruitment.domain.repositories.JobRepository;
import com.iucse.passnet.recruitment.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class PassnetRecruitmentApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JobRepository jobRepository;

    public static void main(String[] args) {
        SpringApplication.run(PassnetRecruitmentApplication.class, args);
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

    }
}
