package com.iucse.passnet.recruitment.mock;

import com.iucse.passnet.recruitment.domain.models.JobModel;
import com.iucse.passnet.recruitment.domain.models.UserModel;
import com.iucse.passnet.recruitment.domain.repositories.JobRepository;
import com.iucse.passnet.recruitment.domain.repositories.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MockTest {

    private UserRepository userRepository;
    private JobRepository jobRepository;

    @Autowired
    public MockTest(UserRepository userRepository, JobRepository jobRepository) {
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
    }

    @Test
    public void testRepository(){
        UserModel userModel = userRepository.findFirstById("unique-id");

        Assert.assertNotNull(userModel.getId());
    }
}
