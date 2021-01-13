package com.iucse.passnet.recruitment.adapter.rest;

import com.iucse.passnet.recruitment.adapter.controllers.QueryController;
import com.iucse.passnet.recruitment.domain.exceptions.JobNotFoundException;
import com.iucse.passnet.recruitment.domain.exceptions.NullIdentifierException;
import com.iucse.passnet.recruitment.domain.views.JobApplicationListView;
import com.iucse.passnet.recruitment.domain.views.OwnedJobListView;
import com.iucse.passnet.recruitment.domain.views.PostedJobsView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/query")
@CrossOrigin("*")
@Slf4j(topic = "[QueryRestController]")
public class QueryRestController extends BaseController {
	private final QueryController queryController;

	@Autowired
	public QueryRestController(QueryController queryController) {
		this.queryController = queryController;
	}

	@GetMapping(value = "/job-view")
	public ResponseEntity<?> getJobView(@RequestParam("jobId") String id) {
		try {
			return ResponseEntity.ok(this.queryController.getJobView(id));
		} catch (NullIdentifierException e) {
			return badRequest(e);
		} catch (JobNotFoundException e) {
			return notFound();
		}
	}

	@GetMapping(value = "/job-application-view")
	public ResponseEntity<?> getJobApplicationView(@RequestParam("jobApplicationId") String id) {
		return ResponseEntity.ok(this.queryController.getJobApplicationView(id));
	}

	@GetMapping(value = "/posted-jobs")
	public PostedJobsView getPostedJobsView() {
		return this.queryController.getPostedJobsView();
	}

	@GetMapping(value = "/owned-jobs")
	public ResponseEntity<?> getOwnPostedJobs(@RequestParam("teacherId") String teacherId) {
		OwnedJobListView ownedJobListView = this.queryController.getPostedJobsByUserView(teacherId);
		return ResponseEntity.ok(ownedJobListView);
	}

	@GetMapping(value = "/job-application-list-view")
	public ResponseEntity<?> getJobApplicationListView(@RequestParam("jobId") String jobId) {
		JobApplicationListView jobApplicationListView = this.queryController.getJobApplicationListView(jobId);
		return ResponseEntity.ok(jobApplicationListView);
	}
}
