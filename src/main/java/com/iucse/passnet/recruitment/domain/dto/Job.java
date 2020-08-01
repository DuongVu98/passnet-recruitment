package com.iucse.passnet.recruitment.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    private String id;
    private String title;
    private String description;
    private String department;
    private String recruiterId;
    private List<String> studentApplicationsId;
    private List<String> acceptedStudentApplicationsId;

    public void addStudentApplication(String studentId) {
        this.getStudentApplicationsId().add(studentId);
    }

    public void addAcceptedStudentApplicationId(String studentId) {
        this.acceptedStudentApplicationsId.add(studentId);
    }
}
