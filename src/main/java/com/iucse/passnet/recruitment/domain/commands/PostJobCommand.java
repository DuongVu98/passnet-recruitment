package com.iucse.passnet.recruitment.domain.commands;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PostJobCommand extends BaseCommand {
    private String courseName;
    private String jobName;
    private String jobOwnerId;
    private String requirement;
    private String semester;
    private String content;
    private String organizationId;
}
