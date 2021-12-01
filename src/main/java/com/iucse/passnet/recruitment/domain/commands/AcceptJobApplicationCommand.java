package com.iucse.passnet.recruitment.domain.commands;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AcceptJobApplicationCommand extends BaseCommand {
    private String jobId;
    private String jobApplicationId;
}
