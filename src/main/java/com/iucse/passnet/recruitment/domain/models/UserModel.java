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
@Table(name = "user")
public class UserModel {

    @Id
    private String id;

    @OneToMany(mappedBy = "recruiter", cascade = CascadeType.ALL)
    private List<JobModel> postedJob;

    @ManyToMany(mappedBy = "studentApplications")
    private List<JobModel> jobApplications = new ArrayList<>();

    @ManyToMany(mappedBy = "acceptedStudents")
    private List<JobModel> acceptedJobApplications = new ArrayList<>();

}
