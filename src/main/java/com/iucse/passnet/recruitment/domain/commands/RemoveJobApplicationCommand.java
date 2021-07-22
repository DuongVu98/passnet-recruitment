package com.iucse.passnet.recruitment.domain.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RemoveJobApplicationCommand extends BaseCommand {
    private String jobId;
    private String jobApplicationId;
}
