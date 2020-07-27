package com.iucse.passnet.recruitment.usecase.interactors.commands;

import com.iucse.passnet.recruitment.domain.dto.Job;
import com.iucse.passnet.recruitment.domain.dto.Student;
import com.iucse.passnet.recruitment.domain.models.JobModel;
import com.iucse.passnet.recruitment.domain.models.UserModel;
import com.iucse.passnet.recruitment.domain.repositories.JobRepository;
import com.iucse.passnet.recruitment.domain.repositories.UserRepository;
import com.iucse.passnet.recruitment.domain.mappers.ModelMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class StudentApplyJobCommand implements ActionCommand {

    private final String studentId;
    private final String jobId;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final ModelMapper<UserModel, Student, String> studentModelMapper;
    private final ModelMapper<JobModel, Job, String> jobModelMapper;

    private void studentApplyJob(){
        UserModel userModel = userRepository.findFirstById(studentId);
        JobModel jobModel = jobRepository.findFirstById(jobId);

        Student student = studentModelMapper.mapToDto(userModel);
        Job job = jobModelMapper.mapToDto(jobModel);

        student.addJobApplication(job.getId());
        job.addStudentApplication(student.getId());

        /**
         * When saving many-to-many data pairs, we must save both sides
         */
        userRepository.save(studentModelMapper.mapToEntity(student));
        jobRepository.save(jobModelMapper.mapToEntity(job));
    }

    @Override
    public void execute() {
        this.studentApplyJob();
    }
}
