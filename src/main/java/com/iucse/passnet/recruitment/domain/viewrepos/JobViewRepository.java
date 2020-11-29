package com.iucse.passnet.recruitment.domain.viewrepos;

import com.iucse.passnet.recruitment.domain.views.JobView;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobViewRepository extends CrudRepository<JobView, String> {}
