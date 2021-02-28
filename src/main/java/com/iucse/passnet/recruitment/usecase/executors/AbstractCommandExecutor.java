package com.iucse.passnet.recruitment.usecase.executors;

import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
public abstract class AbstractCommandExecutor<COMMAND, RETURN> {
	@Setter
	protected JobAggregateRepository aggregateRepository;

	@Transactional
	public abstract RETURN execute(COMMAND command) throws Throwable;
}
