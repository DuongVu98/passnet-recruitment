package com.iucse.passnet.recruitment.domain.viewrepos;

import com.iucse.passnet.recruitment.domain.views.OwnedJobListView;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnJobListViewRepository extends CrudRepository<OwnedJobListView, String> {
	OwnedJobListView findByTeacherId(String uid);
}
