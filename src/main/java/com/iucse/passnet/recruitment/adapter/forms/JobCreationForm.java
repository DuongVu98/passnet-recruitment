package com.iucse.passnet.recruitment.adapter.forms;

import lombok.Builder;
import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
public class JobCreationForm {
    private String jobTitle;
    private String courseName;
    private String requirement;
    private String content;
    private String semester;
}
