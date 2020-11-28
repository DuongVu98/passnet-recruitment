package com.iucse.passnet.recruitment.domain.views;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class LiteJobView {
    String courseName;
    String jobTitle;
    String semester;
    String department;
}
