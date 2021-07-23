package com.iucse.passnet.recruitment.domain.forms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobCreationForm {
    @JsonProperty("jobTitle")
    @NotBlank(message = "jobTitle should not be empty")
    private String jobTitle;

    @JsonProperty("courseName")
    @NotBlank(message = "courseName should not be empty")
    private String courseName;

    @JsonProperty("requirement")
    private String requirement;

    @JsonProperty("content")
    private String content;

    @JsonProperty("semester")
    @NotBlank(message = "semester should not be empty")
    private String semester;

    @JsonProperty("organizationId")
    private String organizationId;
}
