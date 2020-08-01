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
import lombok.extern.slf4j.Slf4j;

@Builder
@AllArgsConstructor
@Slf4j(topic = "[TeacherAcceptApplicationCommand]")
public class TeacherAcceptApplicationCommand implements ActionCommand {

    private final String studentId;
    private final String jobId;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final ModelMapper<JobModel, Job, String> jobModelMapper;
    private final ModelMapper<UserModel, Student, String> studentModelMapper;

    private void teacherAcceptStudentApplication() {

        log.info("teacher accept command!!!");

        UserModel userModel = userRepository.findFirstById(studentId);
        JobModel jobModel = jobRepository.findFirstById(jobId);

        Student student = studentModelMapper.mapToDto(userModel);
        Job job = jobModelMapper.mapToDto(jobModel);

        if (job.getStudentApplicationsId().contains(studentId)) {
            student.addAcceptedJobApplicationsId(job.getId());
            job.addAcceptedStudentApplicationId(student.getId());

            student.getAcceptedJobApplicationsId().remove(jobId);
            job.getStudentApplicationsId().remove(studentId);

            userRepository.save(studentModelMapper.mapToEntity(student));
            jobRepository.save(jobModelMapper.mapToEntity(job));
        }
    }

    @Override
    public void execute() {
        this.teacherAcceptStudentApplication();
    }
}
