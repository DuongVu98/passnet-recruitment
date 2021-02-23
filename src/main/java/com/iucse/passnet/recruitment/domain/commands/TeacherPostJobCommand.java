package com.iucse.passnet.recruitment.domain.commands;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeacherPostJobCommand extends BaseCommand {
	private String courseName;
	private String jobName;
	private String jobOwnerId;
	private String requirement;
	private String semester;
	private String content;
}
