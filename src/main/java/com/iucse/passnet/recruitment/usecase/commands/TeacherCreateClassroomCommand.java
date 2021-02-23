package com.iucse.passnet.recruitment.usecase.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TeacherCreateClassroomCommand extends BaseCommand {
    private String jobId;
}
