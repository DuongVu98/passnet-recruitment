package com.iucse.passnet.recruitment.adapter.rest;

import com.iucse.passnet.recruitment.adapter.controllers.ApplicatorController;
import com.iucse.passnet.recruitment.domain.dto.Job;
import com.iucse.passnet.recruitment.domain.dto.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/applicator")
public class ApplicatorRestController {

    private final ApplicatorController applicatorController;

    @Autowired
    public ApplicatorRestController(ApplicatorController applicatorController) {
        this.applicatorController = applicatorController;
    }

    @GetMapping(value = "/apply-job")
    public void studentApplyJob(){

        // Draft data
        String studentId = "student-id";
        String jobId = "2";

        this.applicatorController.studentApplyJob(studentId, jobId);
    }

    @GetMapping(value = "/teacher-profile/{teacherId}")
    public Teacher viewTeacherProfile(@PathVariable("teacherId") String teacherId){
        return this.applicatorController.viewTeacherProfile(teacherId);
    }

    @GetMapping(value = "/job/{jobId}")
    public Job viewPostedJob(@PathVariable("jobId") String jobId){
        return this.applicatorController.viewPostedJob(jobId);
    }
}
