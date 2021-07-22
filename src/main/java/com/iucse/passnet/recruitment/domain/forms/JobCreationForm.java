package com.iucse.passnet.recruitment.domain.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobCreationForm {
    @NotBlank(message = "jobTitle should not be empty")
    private String jobTitle;

    @NotBlank(message = "courseName should not be empty")
    private String courseName;

    private String requirement;

    private String content;

    @NotBlank(message = "semester should not be empty")
    private String semester;

    private String organizationId;
}
