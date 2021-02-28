package com.iucse.passnet.recruitment.domain.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TeacherRemoveStudentJobApplicationCommand extends BaseCommand {
	private String jobId;
	private String jobApplicationId;
}
