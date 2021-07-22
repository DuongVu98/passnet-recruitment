package com.iucse.passnet.recruitment.adapter.gateway;

import com.iucse.passnet.recruitment.domain.exceptions.JobNotFoundException;
import com.iucse.passnet.recruitment.domain.exceptions.NullIdentifierException;
import com.iucse.passnet.recruitment.domain.views.*;
import com.iucse.passnet.recruitment.usecase.queries.ViewQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QueryGateway {
    private final ViewQuery viewQuery;

    @Autowired
    public QueryGateway(ViewQuery viewQuery) {
        this.viewQuery = viewQuery;
    }

    public JobView getJobView(String id) throws NullIdentifierException, JobNotFoundException {
        return this.viewQuery.queryJobView(id);
    }

    public List<JobLiteView> getJobList(List<String> ids) {
        return this.viewQuery.queryJobList(ids);
    }

    public JobApplicationView getJobApplicationView(String id) {
        return this.viewQuery.queryJobApplicationView(id);
    }

    public PostedJobsView getPostedJobsView() {
        return this.viewQuery.queryPostedJobsView();
    }

    public OwnedJobListView getPostedJobsByUserView(String uid) {
        return this.viewQuery.queryUserOwnJob(uid);
    }

    public JobApplicationListView getJobApplicationListView(String jobId) {
        return this.viewQuery.queryJobApplicationListView(jobId);
    }
}
