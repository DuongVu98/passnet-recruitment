package com.iucse.passnet.recruitment.adapter.forms;

import lombok.Builder;
import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
public class JobApplicationForm {
    private String letter;
    private String content;
}
