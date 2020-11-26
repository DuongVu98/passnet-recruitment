package com.iucse.passnet.recruitment.adapter.controllers;

import com.iucse.passnet.recruitment.domain.viewrepos.JobViewRepository;
import com.iucse.passnet.recruitment.domain.views.JobView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Component
public class QueryController {

    @Autowired
    private JobViewRepository jobViewRepository;

    public JobView getJobView(String id) {
        if (this.jobViewRepository.findById(id).isPresent()) {
            return this.jobViewRepository.findById(id).get();
        }else {
            return null;
        }
    }
}
