package com.iucse.passnet.recruitment.usecase.interactors.commands;

import com.iucse.passnet.recruitment.domain.dto.Job;
import com.iucse.passnet.recruitment.domain.dto.Student;
import com.iucse.passnet.recruitment.domain.mappers.ModelMapper;
import com.iucse.passnet.recruitment.domain.models.JobModel;
import com.iucse.passnet.recruitment.domain.models.UserModel;
import com.iucse.passnet.recruitment.domain.repositories.JobRepository;
import com.iucse.passnet.recruitment.domain.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class TeacherAcceptApplicationCommand implements ActionCommand {

    private final String studentId;
    private String jobId;
    private UserRepository userRepository;
    private JobRepository jobRepository;
    private ModelMapper<JobModel, Job, String> jobModelMapper;
    private ModelMapper<UserModel, Student, String> studentModelMapper;

    private void teacherAcceptStudentApplication(){
        UserModel userModel = userRepository.findFirstById(studentId);
        JobModel jobModel = jobRepository.findFirstById(jobId);

        Student student = studentModelMapper.mapToDto(userModel);
        Job job = jobModelMapper.mapToDto(jobModel);

        student.addAcceptedJobApplicationsId(job.getId());
        job.addAcceptedStudentApplicationId(student.getId());

        userRepository.save(studentModelMapper.mapToEntity(student));
        jobRepository.save(jobModelMapper.mapToEntity(job));
    }

    @Override
    public void execute() {
        this.teacherAcceptStudentApplication();
    }
}
