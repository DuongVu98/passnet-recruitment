package com.iucse.passnet.recruitment.adapter.rest;

import com.iucse.passnet.recruitment.adapter.controllers.ApplicatorController;
import com.iucse.passnet.recruitment.domain.forms.JobApplicationForm;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Applicator API")
@RequestMapping(value = "/command/applicator")
public class ApplicatorRestController extends BaseController {
	private final ApplicatorController applicatorController;

	@Autowired
	public ApplicatorRestController(ApplicatorController applicatorController) {
		this.applicatorController = applicatorController;
	}

	@PostMapping(value = "/apply-job")
	public void studentApplyJob(
		@Valid @RequestBody JobApplicationForm form,
		@RequestParam("studentId") String studentId,
		@RequestParam("jobId") String jobId
	) {
		try {
			this.applicatorController.studentApplyJob(form, studentId, jobId);
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}
}
