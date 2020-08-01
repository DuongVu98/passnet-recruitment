package com.iucse.passnet.recruitment.domain.mappers;

import com.iucse.passnet.recruitment.domain.dto.Job;
import com.iucse.passnet.recruitment.domain.models.JobModel;
import com.iucse.passnet.recruitment.domain.models.UserModel;
import com.iucse.passnet.recruitment.domain.repositories.JobRepository;
import com.iucse.passnet.recruitment.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Primary
@Service("job-modelmapper")
public class JobModelMapper implements ModelMapper<JobModel, Job, String>{

    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    @Autowired
    public JobModelMapper(UserRepository userRepository, JobRepository jobRepository) {
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
    }

    @Override
    public JobModel mapToEntity(Job job) {
        return JobModel.builder()
                .id(job.getId())
                .title(job.getTitle())
                .department(job.getDepartment())
                .description(job.getDescription())
                .recruiter(userRepository.findFirstById(job.getRecruiterId()))
                .studentApplications(Optional.ofNullable(job.getStudentApplicationsId()).orElse(Collections.emptyList()).stream().map(userRepository::findFirstById).collect(Collectors.toList()))
                .acceptedStudents(Optional.ofNullable(job.getAcceptedStudentApplicationsId()).orElse(Collections.emptyList()).stream().map(userRepository::findFirstById).collect(Collectors.toList()))
                .build();
    }

    @Override
    public List<JobModel> mapToEntityList(List<Job> jobs) {
        return jobs.stream().map(this::mapToEntity).collect(Collectors.toList());
    }

    @Override
    public Job mapToDto(JobModel jobModel) {
        return Job.builder()
                .id(jobModel.getId())
                .title(jobModel.getTitle())
                .department(jobModel.getDepartment())
                .description(jobModel.getDescription())
                .recruiterId(jobModel.getRecruiter().getId())
                .studentApplicationsId(Optional.ofNullable(jobModel.getStudentApplications()).orElse(Collections.emptyList()).stream().map(UserModel::getId).collect(Collectors.toList()))
                .acceptedStudentApplicationsId(Optional.ofNullable(jobModel.getAcceptedStudents()).orElse(Collections.emptyList()).stream().map(UserModel::getId).collect(Collectors.toList()))
                .build();
    }

    @Override
    public List<Job> mapToDtoList(List<JobModel> jobModelList) {
        return Optional.ofNullable(jobModelList).orElse(Collections.emptyList()).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public JobModel mapIdToEntity(String jobId) {
        return jobRepository.findFirstById(jobId);
    }

    @Override
    public List<JobModel> mapIdListToEntityList(List<String> idList) {
        return Optional.ofNullable(idList).orElse(Collections.emptyList()).stream().map(this::mapIdToEntity).collect(Collectors.toList());
    }

    @Override
    public String mapToId(JobModel jobModel) {
        return jobModel.getId();
    }

    @Override
    public List<String> mapToIdList(List<JobModel> jobModelList) {
        return Optional.ofNullable(jobModelList).orElse(Collections.emptyList()).stream().map(this::mapToId).collect(Collectors.toList());
    }
}
