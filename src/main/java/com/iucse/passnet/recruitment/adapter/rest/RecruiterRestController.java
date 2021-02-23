package com.iucse.passnet.recruitment.adapter.rest;

import com.iucse.passnet.recruitment.adapter.controllers.RecruiterController;
import com.iucse.passnet.recruitment.adapter.forms.JobCreationForm;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Recruiter API")
@Slf4j(topic = "[RecruiterRestController]")
@RequestMapping(value = "/command/recruiter")
public class RecruiterRestController extends BaseController {
	private final RecruiterController recruiterController;

	@Autowired
	public RecruiterRestController(RecruiterController recruiterController) {
		this.recruiterController = recruiterController;
	}

	@PostMapping(value = "/post-job")
	public ResponseEntity<?> postNewJob(@RequestBody JobCreationForm form, @RequestParam("teacherId") String teacherId) {
		try {
			this.recruiterController.postJob(form, teacherId);
			return ok();
		} catch (Throwable throwable) {
			return badRequest(throwable);
		}
	}

	@PostMapping(value = "/accept-application")
	public ResponseEntity<?> acceptJobApplication(
		@RequestParam("jobApplicationId") String jobApplicationId,
		@RequestParam("jobId") String jobId
	) {
		try {
			this.recruiterController.acceptJobApplication(jobApplicationId, jobId);
		} catch (Throwable throwable) {
			return badRequest(throwable);
		}
		return ok();
	}

	@PostMapping(value = "/create-classroom")
	public ResponseEntity<?> createClassroom(@RequestParam("jobId") String jobId) {
		this.recruiterController.createClassroom(jobId);
		return ok();
	}
}
