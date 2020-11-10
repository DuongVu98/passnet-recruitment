package com.iucse.passnet.recruitment.usecase.commands.requests;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentApplyJobCommand extends BaseCommand{
    private String studentId;
    private String jobId;
}
