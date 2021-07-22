package com.iucse.passnet.recruitment.domain.views;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobLiteView {
    @JsonProperty("id")
    private String id;

    @JsonProperty("courseName")
    private String courseName;

    @JsonProperty("jobTitle")
    private String jobTitle;

    @JsonProperty("semester")
    private String semester;

    @JsonProperty("appliedAmount")
    private Integer appliedAmount;

    @JsonProperty("postedDate")
    @JsonFormat(pattern = "dd-MM-yyy")
    private LocalDate postedDate;

    @JsonProperty("daysAgo")
    private Long daysAgo;
}
