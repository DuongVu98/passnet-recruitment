package com.iucse.passnet.recruitment.domain.repositories;

import com.iucse.passnet.recruitment.domain.models.JobModel;
import com.iucse.passnet.recruitment.domain.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("job-repository")
public interface JobRepository extends JpaRepository<JobModel, String>, CrudRepository<JobModel, String>{
    List<JobModel> findAllByRecruiter(UserModel recruiter);
    JobModel findFirstById(String jobId);

    @Modifying
    @Query(value = "INSERT into JobModel (title, department, description) values (:title, :department, :description)", nativeQuery = true)
    JobModel insertJob(@Param("title") String title, @Param("department") String department, @Param("description") String description);
}
