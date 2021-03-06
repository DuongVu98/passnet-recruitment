package com.iucse.passnet.recruitment.domain.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationForm {
	@NotBlank(message = "letter should not be empty")
	@Size(min = 10, message = "letter should not be less than 10")
	private String letter;

	@NotBlank(message = "letter should not be empty")
	@Size(min = 20, message = "content should not less than 20")
	private String content;
}
