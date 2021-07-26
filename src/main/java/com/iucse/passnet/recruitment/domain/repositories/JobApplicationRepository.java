package com.iucse.passnet.recruitment.domain.repositories;

import com.iucse.passnet.recruitment.domain.aggregate.entities.JobApplication;
import com.iucse.passnet.recruitment.domain.aggregate.vos.JobApplicationId;
import com.iucse.passnet.recruitment.domain.aggregate.vos.ProfileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, JobApplicationId> {
    @Query("select j from JobApplication j where j.applicationOwner = :applicationOwner order by j.createdAt DESC")
    List<JobApplication> findByOwner(@Param("applicationOwner") ProfileId applicationOwner);
}
