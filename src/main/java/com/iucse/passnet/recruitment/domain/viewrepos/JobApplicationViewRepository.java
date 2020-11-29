package com.iucse.passnet.recruitment.domain.viewrepos;

import com.iucse.passnet.recruitment.domain.views.JobApplicationView;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobApplicationViewRepository extends CrudRepository<JobApplicationView, String> {}
