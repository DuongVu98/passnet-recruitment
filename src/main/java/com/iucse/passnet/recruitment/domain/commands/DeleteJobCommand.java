package com.iucse.passnet.recruitment.domain.commands;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DeleteJobCommand extends BaseCommand {
    private String jobId;
}
