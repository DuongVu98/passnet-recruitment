package com.iucse.passnet.recruitment.usecase.interactors.commands;

import com.iucse.passnet.recruitment.domain.dto.Job;
import com.iucse.passnet.recruitment.domain.dto.Teacher;
import com.iucse.passnet.recruitment.domain.models.JobModel;
import com.iucse.passnet.recruitment.domain.models.UserModel;
import com.iucse.passnet.recruitment.domain.repositories.JobRepository;
import com.iucse.passnet.recruitment.domain.repositories.UserRepository;
import com.iucse.passnet.recruitment.domain.mappers.ModelMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Builder
@AllArgsConstructor
@Slf4j(topic = "[TeacherPostJobCommand]")
public class TeacherPostJobCommand implements ActionCommand {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    private final ModelMapper<UserModel, Teacher, String> teacherModelMapper;
    private final ModelMapper<JobModel, Job, String> jobModelMapper;

    private final String teacherId;
    private final Job newJob;

    @Override
    public void execute() {

        UserModel userModel = userRepository.findFirstById(teacherId);
        Teacher teacher = teacherModelMapper.mapToDto(userModel);

        newJob.setRecruiterId(teacherId);
        teacher.addNewPostJob(newJob);
        /**
         * When saving one-to-many or many-to-one, we just save only the owning side (the "many" side)
         */
        jobRepository.save(jobModelMapper.mapToEntity(newJob));
    }
}
