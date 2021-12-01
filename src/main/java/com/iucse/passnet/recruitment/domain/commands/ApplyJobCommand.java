package com.iucse.passnet.recruitment.domain.commands;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ApplyJobCommand extends BaseCommand {
    private String studentId;
    private String jobId;
    private String letter;
    private String content;
}
