package com.iucse.passnet.recruitment.domain.repositories;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobId;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.UserId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JobAggregateRepository extends JpaRepository<Job, JobId>, CrudRepository<Job, JobId> {
	Job findAllById(JobId id);

	List<Job> findAllByJobOwner(UserId id);

	@Query("SELECT j from Job j LEFT JOIN FETCH j.jobApplications ja WHERE j.id = :id")
	Job findByIdWithJobApplications(@Param("id") JobId id);
}
