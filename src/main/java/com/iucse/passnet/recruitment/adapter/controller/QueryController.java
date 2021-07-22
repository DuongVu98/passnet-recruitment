package com.iucse.passnet.recruitment.adapter.controller;

import com.iucse.passnet.recruitment.adapter.gateway.QueryGateway;
import com.iucse.passnet.recruitment.domain.forms.GetJobListQuery;
import com.iucse.passnet.recruitment.domain.views.JobApplicationListView;
import com.iucse.passnet.recruitment.domain.views.JobApplicationView;
import com.iucse.passnet.recruitment.domain.views.JobLiteView;
import com.iucse.passnet.recruitment.domain.views.JobView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/query")
@Slf4j(topic = "[QueryRestController]")
public class QueryController extends BaseController {
    private final QueryGateway queryGateway;

    @Autowired
    public QueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping(value = "/job-view")
    public ResponseEntity<JobView> getJobView(@RequestParam("jobId") String id) {
        return ok(this.queryGateway.getJobView(id));
    }

    @GetMapping(value = "/job-list")
    public ResponseEntity<List<JobLiteView>> getJobList(@Valid @RequestBody GetJobListQuery query) {
        return ok(this.queryGateway.getJobList(query.getJobIds()));
    }

    @GetMapping(value = "/job-application-view")
    public ResponseEntity<JobApplicationView> getJobApplicationView(@RequestParam("jobApplicationId") String id) {
        return ok(this.queryGateway.getJobApplicationView(id));
    }

    @GetMapping(value = "/posted-jobs")
    public ResponseEntity<?> getPostedJobsView() {
        return ok(this.queryGateway.getPostedJobsView().getLitePostedJobs());
    }

    @GetMapping(value = "/owned-jobs")
    public ResponseEntity<List<JobLiteView>> getOwnPostedJobs(@RequestParam("teacherId") String teacherId) {
        var ownedJobListView = this.queryGateway.getPostedJobsByUserView(teacherId);
        return ok(ownedJobListView.getLitePostedJobs());
    }

    @GetMapping(value = "/job-application-list-view")
    public ResponseEntity<JobApplicationListView> getJobApplicationListView(@RequestParam("jobId") String jobId) {
        var jobApplicationListView = this.queryGateway.getJobApplicationListView(jobId);
        return ok(jobApplicationListView);
    }
}
