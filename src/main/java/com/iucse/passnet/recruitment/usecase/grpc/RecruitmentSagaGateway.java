package com.iucse.passnet.recruitment.usecase.grpc;

import com.cseiu.passnet.saga.recruitmentsaga.EventProducerGrpc;
import com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents;
import com.iucse.passnet.recruitment.domain.events.produce.AcceptStudentApplicationEvent;
import com.iucse.passnet.recruitment.domain.events.produce.DeleteJobEvent;
import com.iucse.passnet.recruitment.domain.events.produce.PostNewJobEvent;
import com.iucse.passnet.recruitment.domain.events.produce.RemoveStudentApplicationEvent;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "[CommandGateway]")
public class RecruitmentSagaGateway {
	@GrpcClient("saga-recruitment")
	private EventProducerGrpc.EventProducerBlockingStub eventProducerBlockingStub;

	@Autowired
	public RecruitmentSagaGateway() {
		EventBus.getDefault().register(this);
	}

	@Subscribe
	public void on(PostNewJobEvent postNewJobEvent) {
		log.info("post new job");
		ProduceEvents.SagaResponse response = eventProducerBlockingStub.producePostNewJobEvent(
			ProduceEvents
				.PostNewJobEvent.newBuilder()
				.setEventId(postNewJobEvent.getEventId())
				.setJobId(postNewJobEvent.getJobId())
				.setOwnerId(postNewJobEvent.getOwnerId())
				.build()
		);

		log.info(response.getMessage());
	}

	@Subscribe
	public void on(AcceptStudentApplicationEvent acceptStudentApplicationEvent) {
		ProduceEvents.SagaResponse response = eventProducerBlockingStub.produceAcceptStudentApplicationEvent(
			ProduceEvents
				.AcceptStudentApplicationEvent.newBuilder()
				.setEventId(acceptStudentApplicationEvent.getEventId())
				.setJobId(acceptStudentApplicationEvent.getJobId())
				.setTaId(acceptStudentApplicationEvent.getTaId())
				.build()
		);

		log.info(response.getMessage());
	}

	@Subscribe
	public void on(RemoveStudentApplicationEvent removeStudentApplicationEvent) {
		var response = eventProducerBlockingStub.produceRemoveStudentApplicationEvent(
			ProduceEvents
				.RemoveStudentApplicationEvent.newBuilder()
				.setEventId(removeStudentApplicationEvent.getEventId())
				.setJobId(removeStudentApplicationEvent.getJobId())
				.setTaId(removeStudentApplicationEvent.getTaId())
				.build()
		);

		log.info(response.getMessage());
	}

	@Subscribe
	public void on(DeleteJobEvent deleteJobEvent) {
		var response = eventProducerBlockingStub.produceDeleteJobEvent(
			ProduceEvents.DeleteJobEvent.newBuilder().setEventId(deleteJobEvent.getEventId()).setJobId(deleteJobEvent.getJobId()).build()
		);

		log.info(response.getMessage());
	}
}
