package com.iucse.passnet.recruitment.adapter.rest;

import com.iucse.passnet.recruitment.adapter.controllers.ApplicatorController;
import com.iucse.passnet.recruitment.adapter.forms.JobApplicationForm;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin
@RestController
@RequestMapping(value = "/command/applicator")
@Tag(name = "Applicator API")
public class ApplicatorRestController extends BaseController {
	private final ApplicatorController applicatorController;

	@Autowired
	public ApplicatorRestController(ApplicatorController applicatorController) {
		this.applicatorController = applicatorController;
	}

	@GetMapping(value = "/apply-job")
	public void studentApplyJob(
		@RequestBody JobApplicationForm form,
		@RequestParam("studentId") String studentId,
		@RequestParam("jobId") String jobId
	) {
		this.applicatorController.studentApplyJob(form, studentId, jobId);
	}
}
