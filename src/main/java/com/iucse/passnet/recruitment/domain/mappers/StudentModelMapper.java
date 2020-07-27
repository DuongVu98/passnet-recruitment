package com.iucse.passnet.recruitment.domain.mappers;

import com.iucse.passnet.recruitment.domain.dto.Student;
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

@Service
@Primary
public class StudentModelMapper implements ModelMapper<UserModel, Student, String> {

    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    @Autowired
    public StudentModelMapper(UserRepository userRepository, JobRepository jobRepository) {
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
    }

    @Override
    public UserModel mapToEntity(Student student) {
        return UserModel.builder()
                .id(student.getId())
                .jobApplications(Optional.ofNullable(student.getJobApplicationIds()).orElse(Collections.emptyList()).stream().map(jobRepository::findFirstById).collect(Collectors.toList()))
                .build();
    }

    @Override
    public List<UserModel> mapToEntityList(List<Student> students) {
        return Optional.ofNullable(students).orElse(Collections.emptyList()).stream().map(this::mapToEntity).collect(Collectors.toList());
    }

    @Override
    public Student mapToDto(UserModel userModel) {
        return Student.builder()
                .id(userModel.getId())
                .jobApplicationIds(Optional.ofNullable(userModel.getJobApplications()).orElse(Collections.emptyList()).stream().map(JobModel::getId).collect(Collectors.toList()))
                .build();
    }

    @Override
    public List<Student> mapToDtoList(List<UserModel> userModelList) {
        return Optional.ofNullable(userModelList).orElse(Collections.emptyList()).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public UserModel mapIdToEntity(String studentId) {
        return userRepository.findFirstById(studentId);
    }

    @Override
    public List<UserModel> mapIdListToEntityList(List<String> idList) {
        return Optional.ofNullable(idList).orElse(Collections.emptyList()).stream().map(this::mapIdToEntity).collect(Collectors.toList());
    }

    @Override
    public String mapToId(UserModel userModel) {
        return userModel.getId();
    }

    @Override
    public List<String> mapToIdList(List<UserModel> userModelList) {
        return Optional.ofNullable(userModelList).orElse(Collections.emptyList()).stream().map(this::mapToId).collect(Collectors.toList());
    }
}
