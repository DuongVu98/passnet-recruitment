package com.iucse.passnet.recruitment.adapter.rest;

import com.iucse.passnet.recruitment.adapter.controllers.ApplicatorController;
import com.iucse.passnet.recruitment.adapter.forms.JobApplicationForm;
import com.iucse.passnet.recruitment.domain.dto.Job;
import com.iucse.passnet.recruitment.domain.dto.Teacher;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/applicator")
@CrossOrigin(origins = "*")
@Tag(name = "Applicator API")
public class ApplicatorRestController extends BaseController {

    private final ApplicatorController applicatorController;

    @Autowired
    public ApplicatorRestController(ApplicatorController applicatorController) {
        this.applicatorController = applicatorController;
    }

    @GetMapping(value = "/apply-job")
    public void studentApplyJob(@RequestBody JobApplicationForm form, @RequestParam("studentId") String studentId, @RequestParam("jobId") String jobId) {
        this.applicatorController.studentApplyJob(form, studentId, jobId);
    }

    @GetMapping(value = "/teacher-profile/{teacherId}")
    public ResponseEntity<?> viewTeacherProfile(@PathVariable("teacherId") String teacherId) {
        try {
            Optional<Teacher> opt = Optional.ofNullable(this.applicatorController.viewTeacherProfile(teacherId));
            if (opt.isPresent()) {
                return ResponseEntity.ok(opt.get());
            } else {
                return notFound();
            }
        } catch (Exception e) {
            return badRequest(e);
        }
    }

    @GetMapping(value = "/job/{jobId}")
    public ResponseEntity<?> viewPostedJob(@PathVariable("jobId") String jobId) {
        try {
            Optional<Job> opt = Optional.ofNullable(this.applicatorController.viewPostedJob(jobId));
            if (opt.isPresent()) {
                return ResponseEntity.ok(opt.get());
            } else {
                return notFound();
            }
        } catch (Exception e) {
            return badRequest(e);
        }
    }
}
