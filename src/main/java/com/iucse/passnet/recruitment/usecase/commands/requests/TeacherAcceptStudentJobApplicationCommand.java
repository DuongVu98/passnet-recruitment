package com.iucse.passnet.recruitment.usecase.commands.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
public class TeacherAcceptStudentJobApplicationCommand extends BaseCommand {
    private String jobId;
    private String jobApplicationId;
}
