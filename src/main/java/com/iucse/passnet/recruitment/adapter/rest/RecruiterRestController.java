package com.iucse.passnet.recruitment.adapter.rest;

import com.iucse.passnet.recruitment.adapter.controllers.RecruiterController;
import com.iucse.passnet.recruitment.domain.dto.Job;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/recruiter")
@Slf4j(topic = "[RecruiterRestController]")
public class RecruiterRestController {

    private final RecruiterController recruiterController;

    @Autowired
    public RecruiterRestController(RecruiterController recruiterController) {
        this.recruiterController = recruiterController;
    }

    @ResponseBody
    @GetMapping(value = "/posted-jobs")
    public List<Job> getAllPostedJobs() {
        return this.recruiterController.getAllPostedJobs();
    }

    @ResponseBody
    @GetMapping(value = "/posted-jobs-test")
    public ResponseEntity<?> getAllPostedJobsTest() {
        try {
            Optional<List<Job>> opt = Optional.ofNullable(this.recruiterController.getAllPostedJobs());
            //                return notFound();
            if(opt.isPresent()){
                return ResponseEntity.ok(opt.get());
            }else {
                return null;
            }
        } catch (Exception exception) {
//            return badRequest()
            return null;
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
