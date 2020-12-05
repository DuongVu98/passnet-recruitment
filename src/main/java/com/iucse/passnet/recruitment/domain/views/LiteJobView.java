package com.iucse.passnet.recruitment.domain.views;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class LiteJobView {
	String id;
	String courseName;
	String jobTitle;
	String semester;
	String department;
	int appliedAmount;
}
