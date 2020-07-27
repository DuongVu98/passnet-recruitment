package com.iucse.passnet.recruitment.domain.repositories;

import com.iucse.passnet.recruitment.domain.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("user-repository")
public interface UserRepository extends JpaRepository<UserModel, String>, CrudRepository<UserModel, String> {
    UserModel findFirstById(String userId);
}
