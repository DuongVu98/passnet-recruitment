package com.iucse.passnet.recruitment.domain.views;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LiteJobApplicationView {
    private String studentId;
    private String applicationState;
}
