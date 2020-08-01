package com.iucse.passnet.recruitment.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job")
public class JobModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String title;
    private String department;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruiter_id")
    private UserModel recruiter;

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "jobs_students",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "student_application_id")
    )
    private List<UserModel> studentApplications = new ArrayList<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "accepted_jobs_students",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "student_application_id")
    )
    private List<UserModel> acceptedStudents = new ArrayList<>();
}
