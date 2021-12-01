package com.iucse.passnet.recruitment.domain.repositories;

import com.iucse.passnet.recruitment.domain.aggregate.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.vos.JobId;
import com.iucse.passnet.recruitment.domain.aggregate.vos.OrganizationId;
import com.iucse.passnet.recruitment.domain.aggregate.vos.ProfileId;
import com.iucse.passnet.recruitment.domain.aggregate.vos.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobAggregateRepository extends JpaRepository<Job, JobId> {
    List<Job> findAllByJobOwner(ProfileId id);

    List<Job> findAllByOrganizationId(OrganizationId organizationId);

    @Query("select j from Job j where j.jobOwner = :value order by j.createdAt asc")
    List<Job> findByOwner(@Param("value") ProfileId value);

    @Query("SELECT j from Job j LEFT JOIN FETCH j.jobApplications ja WHERE j.id = :id")
    Job findByIdWithJobApplications(@Param("id") JobId id);

    @Query("select j from Job j where upper(j.courseName.value) like upper(:value) and j.organizationId = :organizationId order by j.createdAt DESC")
    List<Job> searchByCourse(@Param("value") String value, @Param("organizationId") OrganizationId organizationId);

    @Query("select j from Job j where j.semester = :semester and j.organizationId = :organizationId order by j.createdAt DESC")
    List<Job> searchBySemester(@Param("semester") Semester semester, @Param("organizationId") OrganizationId organizationId);


}
