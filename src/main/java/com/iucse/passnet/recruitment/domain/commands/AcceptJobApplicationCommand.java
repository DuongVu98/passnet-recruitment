package com.iucse.passnet.recruitment.domain.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AcceptJobApplicationCommand extends BaseCommand {
	private String jobId;
	private String jobApplicationId;
}
