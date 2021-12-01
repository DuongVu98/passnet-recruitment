package com.iucse.passnet.recruitment.usecase.executors;

import com.iucse.passnet.recruitment.domain.aggregate.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.vos.*;
import com.iucse.passnet.recruitment.domain.commands.BaseCommand;
import com.iucse.passnet.recruitment.domain.commands.PostJobCommand;
import com.iucse.passnet.recruitment.domain.exceptions.WrongCommandTypeException;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.usecase.services.UUIDGeneratorService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;


@Builder
@RequiredArgsConstructor
public class PostJobExecutor implements CommandExecutor {
    private final JobAggregateRepository jobRepository;
    private final UUIDGeneratorService uuidGeneratorService;

    @Override
    public Job execute(BaseCommand baseCommand) {
        if (baseCommand instanceof PostJobCommand) {
            PostJobCommand command = (PostJobCommand) baseCommand;

            Job newJob = Job
               .builder()
               .id(new JobId(uuidGeneratorService.generate().toString()))
               .jobName(new JobName(command.getJobName()))
               .courseName(new CourseName(command.getCourseName()))
               .content(new Content(command.getContent()))
               .jobRequirement(new JobRequirement(command.getRequirement()))
               .semester(new Semester(command.getSemester()))
               .jobOwner(new ProfileId(command.getJobOwnerId()))
               .organizationId(new OrganizationId(command.getOrganizationId()))
               .build();

            return this.jobRepository.save(newJob);
        } else {
            throw new WrongCommandTypeException("command must be TeacherPostJobCommand");
        }
    }
}
