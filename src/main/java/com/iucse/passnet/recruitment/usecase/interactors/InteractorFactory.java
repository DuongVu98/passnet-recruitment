package com.iucse.passnet.recruitment.usecase.interactors;

import com.iucse.passnet.recruitment.domain.dto.Job;
import com.iucse.passnet.recruitment.domain.dto.Student;
import com.iucse.passnet.recruitment.domain.dto.Teacher;
import com.iucse.passnet.recruitment.domain.models.JobModel;
import com.iucse.passnet.recruitment.domain.models.UserModel;
import com.iucse.passnet.recruitment.domain.repositories.JobRepository;
import com.iucse.passnet.recruitment.domain.repositories.UserRepository;
import com.iucse.passnet.recruitment.usecase.interactors.commands.ActionCommand;
import com.iucse.passnet.recruitment.usecase.interactors.commands.StudentApplyJobCommand;
import com.iucse.passnet.recruitment.usecase.interactors.commands.TeacherAcceptApplicationCommand;
import com.iucse.passnet.recruitment.usecase.interactors.commands.TeacherPostJobCommand;

import com.iucse.passnet.recruitment.domain.mappers.ModelMapper;
import com.iucse.passnet.recruitment.usecase.interactors.queries.ActionQuery;
import com.iucse.passnet.recruitment.usecase.interactors.queries.ViewPostedJob;
import com.iucse.passnet.recruitment.usecase.interactors.queries.ViewTeacherProfileQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InteractorFactory {

    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    private final ModelMapper<JobModel, Job, String> jobModelMapper;
    private final ModelMapper<UserModel, Teacher, String> teacherModelMapper;
    private final ModelMapper<UserModel, Student, String> studentModelMapper;

    @Autowired
    public InteractorFactory(UserRepository userRepository, JobRepository jobRepository, ModelMapper<JobModel, Job, String> jobModelMapper, ModelMapper<UserModel, Teacher, String> teacherModelMapper, ModelMapper<UserModel, Student, String> studentModelMapper) {
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
        this.jobModelMapper = jobModelMapper;
        this.teacherModelMapper = teacherModelMapper;
        this.studentModelMapper = studentModelMapper;
    }

    public ActionCommand getTeacherPostJobCommand(String teacherId, Job newJob){
        return TeacherPostJobCommand.builder()
                .teacherId(teacherId)
                .newJob(newJob)
                .jobRepository(jobRepository)
                .userRepository(userRepository)
                .jobModelMapper(jobModelMapper)
                .teacherModelMapper(teacherModelMapper)
                .build();
    }

    public ActionCommand getStudentApplyJobCommand(String studentId, String jobId){
        return StudentApplyJobCommand.builder()
                .jobId(jobId)
                .studentId(studentId)
                .jobModelMapper(jobModelMapper)
                .studentModelMapper(studentModelMapper)
                .jobRepository(jobRepository)
                .userRepository(userRepository)
                .build();
    }

    public ActionCommand getTeacherAcceptStudentApplicationCommand(String studentId, String jobId){
        return TeacherAcceptApplicationCommand.builder()
                .jobId(jobId)
                .studentId(studentId)
                .userRepository(userRepository)
                .jobRepository(jobRepository)
                .jobModelMapper(jobModelMapper)
                .studentModelMapper(studentModelMapper)
                .build();
    }

    public ActionQuery<Teacher> getViewTeacherProfile(String teacherId){
        return ViewTeacherProfileQuery.builder()
                .teacherId(teacherId)
                .userRepository(userRepository)
                .teacherModelMapper(teacherModelMapper)
                .build();
    }

    public ActionQuery<Job> getViewPostedJob(String jobId){
        return ViewPostedJob.builder()
                .jobId(jobId)
                .jobModelMapper(jobModelMapper)
                .jobRepository(jobRepository)
                .build();
    }
}
