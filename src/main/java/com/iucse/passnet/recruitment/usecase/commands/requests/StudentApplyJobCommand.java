package com.iucse.passnet.recruitment.usecase.commands.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
public class StudentApplyJobCommand extends BaseCommand{
    private String studentId;
    private String jobId;
    private String letter;
    private String content;
}
