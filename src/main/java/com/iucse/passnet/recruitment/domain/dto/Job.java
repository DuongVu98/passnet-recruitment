package com.iucse.passnet.recruitment.domain.dto;

import com.iucse.passnet.recruitment.domain.models.JobModel;
import com.iucse.passnet.recruitment.domain.models.UserModel;
import com.iucse.passnet.recruitment.domain.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public void addStudentApplication(String studentId) {
        this.getStudentApplicationsId().add(studentId);
    }
}
