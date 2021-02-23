package com.iucse.passnet.recruitment.domain.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class StudentApplyJobCommand extends BaseCommand {
	private String studentId;
	private String jobId;
	private String letter;
	private String content;
}