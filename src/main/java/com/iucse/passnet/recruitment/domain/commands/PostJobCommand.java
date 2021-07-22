package com.iucse.passnet.recruitment.domain.commands;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostJobCommand extends BaseCommand {
    String courseName;
    String jobName;
    String jobOwnerId;
    String requirement;
    String semester;
    String content;
    String organizationId;
}
