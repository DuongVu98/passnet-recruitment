package com.iucse.passnet.recruitment.domain.mappers;

import com.iucse.passnet.recruitment.domain.dto.Teacher;
import com.iucse.passnet.recruitment.domain.models.JobModel;
import com.iucse.passnet.recruitment.domain.models.UserModel;
import com.iucse.passnet.recruitment.domain.repositories.JobRepository;
import com.iucse.passnet.recruitment.domain.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("teacher-modelmapper")
@Slf4j(topic = "[TeacherModelMapper]")
public class TeacherModelMapper implements ModelMapper<UserModel, Teacher, String> {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    @Autowired
    public TeacherModelMapper(JobRepository jobRepository, UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserModel mapToEntity(Teacher teacher) {
        return UserModel.builder()
                .id(teacher.getId())
                .postedJob(Optional.ofNullable(teacher.getPostedJobsId()).orElse(Collections.emptyList()).stream().map(jobRepository::findFirstById).collect(Collectors.toList()))
                .build();
    }

    @Override
    public List<UserModel> mapToEntityList(List<Teacher> teachers) {
        return teachers.stream().map(this::mapToEntity).collect(Collectors.toList());
    }

    @Override
    public Teacher mapToDto(UserModel userModel) {
        return Teacher.builder()
                .id(userModel.getId())
                .postedJobsId(Optional.ofNullable(userModel.getPostedJob()).orElse(Collections.emptyList()).stream().map(JobModel::getId).collect(Collectors.toList()))
                .build();
    }

    @Override
    public List<Teacher> mapToDtoList(List<UserModel> userModelList) {
        return Optional.ofNullable(userModelList).orElse(Collections.emptyList()).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public UserModel mapIdToEntity(String teacherId) {
        return userRepository.findFirstById(teacherId);
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
