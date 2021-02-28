package com.iucse.passnet.recruitment.domain.commands;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeacherDeleteJobCommand extends BaseCommand {
	private String jobId;
}
