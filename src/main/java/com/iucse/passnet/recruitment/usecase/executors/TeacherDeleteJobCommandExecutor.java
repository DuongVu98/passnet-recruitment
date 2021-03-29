package com.iucse.passnet.recruitment.usecase.executors;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobId;
import com.iucse.passnet.recruitment.domain.commands.TeacherDeleteJobCommand;
import com.iucse.passnet.recruitment.domain.events.produce.DeleteJobEvent;
import com.iucse.passnet.recruitment.domain.exceptions.JobNotFoundException;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.usecase.grpc.RecruitmentSagaGateway;
import java.util.Optional;
import lombok.Builder;
import org.greenrobot.eventbus.EventBus;

public class TeacherDeleteJobCommandExecutor extends AbstractCommandExecutor<TeacherDeleteJobCommand, Job> {
	private final RecruitmentSagaGateway recruitmentSagaGateway;

	@Builder
	public TeacherDeleteJobCommandExecutor(
		JobAggregateRepository aggregateRepository,
		RecruitmentSagaGateway recruitmentSagaGateway
	) {
		super(aggregateRepository);
		this.recruitmentSagaGateway = recruitmentSagaGateway;
	}

	@Override
	public Job execute(TeacherDeleteJobCommand command) throws Throwable {
		Optional<Job> jobOptional = aggregateRepository.findById(new JobId(command.getJobId()));

		if (jobOptional.isPresent()) {
			Job aggregate = jobOptional.get();

			aggregateRepository.delete(aggregate);
//			recruitmentSagaGateway.produceDeleteJobEvent(
//				DeleteJobEvent.builder().jobId(aggregate.getId().getValue()).build()
//			);
			EventBus.getDefault().post(
					DeleteJobEvent.builder().jobId(aggregate.getId().getValue()).build()
			);

			return aggregate;
		} else {
			throw new JobNotFoundException("job not found");
		}
	}
}
