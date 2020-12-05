package com.iucse.passnet.recruitment.mock;

import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Sql({ "/sql/single-job.view.sql" })
public class MockTest {
	private final JobAggregateRepository jobAggregateRepository;

	@Autowired
	public MockTest(JobAggregateRepository jobAggregateRepository) {
		this.jobAggregateRepository = jobAggregateRepository;
	}
}
