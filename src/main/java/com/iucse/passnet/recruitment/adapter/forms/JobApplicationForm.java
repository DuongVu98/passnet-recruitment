package com.iucse.passnet.recruitment.adapter.forms;

import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationForm {
	@Min(value = 10, message = "letter should not be less than 10")
	private String letter;

	@Min(value = 20, message = "content should not less than 20")
	private String content;
}
