package com.iucse.passnet.recruitment.usecase.test.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TeacherAcceptStudentJobApplicationCommand extends BaseCommand {
	private String jobId;
	private String jobApplicationId;
}
