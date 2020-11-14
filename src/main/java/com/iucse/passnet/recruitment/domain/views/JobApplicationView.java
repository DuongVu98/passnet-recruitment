package com.iucse.passnet.recruitment.domain.views;

import lombok.Builder;
import lombok.AllArgsConstructor;

@Builder
@AllArgsConstructor
public class JobApplicationView {
    private String id;
    private String studentId;
    private String letter;
    private String content;
    private String state;
}
