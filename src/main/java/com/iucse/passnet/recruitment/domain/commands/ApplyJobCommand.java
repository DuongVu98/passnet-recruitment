package com.iucse.passnet.recruitment.domain.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyJobCommand extends BaseCommand {
    private String studentId;
    private String jobId;
    private String letter;
    private String content;
}
