package com.iucse.passnet.recruitment.adapter.rest;

import com.iucse.passnet.recruitment.adapter.controllers.RecruiterController;
import com.iucse.passnet.recruitment.domain.dto.Job;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/recruiter")
@Tag(name = "Recruiter API")
@Slf4j(topic = "[RecruiterRestController]")
public class RecruiterRestController extends BaseController {

    private final RecruiterController recruiterController;

    @Autowired
    public RecruiterRestController(RecruiterController recruiterController) {
        this.recruiterController = recruiterController;
    }

    @ResponseBody
    @GetMapping(value = "/posted-jobs")
    public ResponseEntity<?> getAllPostedJobsTest() {
        try {
            Optional<List<Job>> opt = Optional.ofNullable(this.recruiterController.getAllPostedJobs());
            if (opt.isPresent()) {
                return ResponseEntity.ok(opt.get());
            } else {
                return notFound();
            }
        } catch (Exception exception) {
            return badRequest(exception);
        }
    }

    @PostMapping(value = "/post-job/{teacherId}")
    public void postJob(@PathVariable("teacherId") String teacherId) {

        log.info("Teacher Id: {}", teacherId);
        // draft data
        Job job = Job.builder()
           .department("CSE")
           .title("Physics 4")
           .description("blah blah")
           .build();
        this.recruiterController.postJob(teacherId, job);
    }

    @GetMapping(value = "/accept")
    public void acceptApplicants(@RequestParam("studentId") String studentId, @RequestParam("jobId") String jobId) {
        this.recruiterController.acceptApplicants(studentId, jobId);
    }
}
