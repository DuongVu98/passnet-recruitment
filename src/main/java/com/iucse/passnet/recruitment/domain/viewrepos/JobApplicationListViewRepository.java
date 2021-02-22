package com.iucse.passnet.recruitment.domain.viewrepos;

import com.iucse.passnet.recruitment.domain.views.JobApplicationListView;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobApplicationListViewRepository extends CrudRepository<JobApplicationListView, String> {}
