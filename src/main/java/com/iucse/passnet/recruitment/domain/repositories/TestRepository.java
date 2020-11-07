package com.iucse.passnet.recruitment.domain.repositories;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Job, JobId>, CrudRepository<Job, JobId> {

    Job findAllById(JobId id);
    Job findByIdWithJobApplications(@Param("id") JobId id);
}
