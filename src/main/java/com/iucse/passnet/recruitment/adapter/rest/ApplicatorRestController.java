package com.iucse.passnet.recruitment.adapter.rest;

import com.iucse.passnet.recruitment.adapter.controllers.ApplicatorController;
import com.iucse.passnet.recruitment.domain.dto.Job;
import com.iucse.passnet.recruitment.domain.dto.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/applicator")
@CrossOrigin(origins = "*")
public class ApplicatorRestController extends HomeRestController{

    private final ApplicatorController applicatorController;

    @Autowired
    public ApplicatorRestController(ApplicatorController applicatorController) {
        this.applicatorController = applicatorController;
    }

    @GetMapping(value = "/apply-job")
    public void studentApplyJob() {

        // Draft data
        String studentId = "student-id";
        String jobId = "2";

        this.applicatorController.studentApplyJob(studentId, jobId);
    }

    @GetMapping(value = "/teacher-profile/{teacherId}")
    public ResponseEntity<?> viewTeacherProfile(@PathVariable("teacherId") String teacherId) {
        try{
            Optional<Teacher> opt = Optional.ofNullable(this.applicatorController.viewTeacherProfile(teacherId));
            if(opt.isPresent()){
                return ResponseEntity.ok(opt.get());
            }else {
                return notFound();
            }
        } catch (Exception e){
            return badRequest();
        }
    }

    @GetMapping(value = "/job/{jobId}")
    public ResponseEntity<?> viewPostedJob(@PathVariable("jobId") String jobId) {
        try {
            Optional<Job> opt = Optional.ofNullable(this.applicatorController.viewPostedJob(jobId));
            if(opt.isPresent()){
                return ResponseEntity.ok(opt.get());
            }else {
                return notFound();
            }
        } catch (Exception e){
            return badRequest();
        }
    }
}
