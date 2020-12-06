package com.iucse.passnet.recruitment.domain.viewrepos;

import com.iucse.passnet.recruitment.domain.views.PostedJobsView;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostedJobsViewRepository extends CrudRepository<PostedJobsView, String> {}
