package com.iucse.passnet.recruitment.adapter.rest;

import com.iucse.passnet.recruitment.adapter.controllers.QueryController;
import com.iucse.passnet.recruitment.domain.views.JobView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/query")
@Slf4j(topic = "[QueryRestController]")
public class QueryRestController extends BaseController {

    private QueryController queryController;

    @Autowired
    public QueryRestController(QueryController queryController) {
        this.queryController = queryController;
    }

    @GetMapping(value = "/job-view")
    public JobView getJobView(@RequestParam("jobId") String id) {
        log.info("jobId: {}", id);
        return this.queryController.getJobView(id);
    }

}