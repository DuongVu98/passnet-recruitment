package com.iucse.passnet.recruitment.usecase.grpc;

import com.cseiu.passnet.saga.recruitmentsaga.EventProducerGrpc;
import com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents;
import com.iucse.passnet.recruitment.domain.events.produce.AcceptStudentApplicationEvent;
import com.iucse.passnet.recruitment.domain.events.produce.DeleteJobEvent;
import com.iucse.passnet.recruitment.domain.events.produce.PostNewJobEvent;
import com.iucse.passnet.recruitment.domain.events.produce.RemoveStudentApplicationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "[CommandGateway]")
public class RecruitmentSagaGateway {
	private EventProducerGrpc.EventProducerBlockingStub eventProducerBlockingStub;

	@Autowired
	public RecruitmentSagaGateway(EventProducerGrpc.EventProducerBlockingStub eventProducerBlockingStub) {
		this.eventProducerBlockingStub = eventProducerBlockingStub;
	}

	public void producePostNewJobEvent(PostNewJobEvent postNewJobEvent) {
		ProduceEvents.SagaResponse response = eventProducerBlockingStub.producePostNewJobEvent(
			ProduceEvents
				.PostNewJobEvent.newBuilder()
				.setJobId(postNewJobEvent.getJobId())
				.setOwnerId(postNewJobEvent.getOwnerId())
				.build()
		);

		log.info(response.getMessage());
	}

	public void produceAcceptStudentApplicationEvent(AcceptStudentApplicationEvent acceptStudentApplicationEvent) {
		ProduceEvents.SagaResponse response = eventProducerBlockingStub.produceAcceptStudentApplicationEvent(
			ProduceEvents
				.AcceptStudentApplicationEvent.newBuilder()
				.setJobId(acceptStudentApplicationEvent.getJobId())
				.setTaId(acceptStudentApplicationEvent.getTaId())
				.build()
		);

		log.info(response.getMessage());
	}

	public void produceRemoveStudentApplicationEvent(RemoveStudentApplicationEvent removeStudentApplicationEvent) {
		ProduceEvents.SagaResponse response = eventProducerBlockingStub.produceRemoveStudentApplicationEvent(
			ProduceEvents
				.RemoveStudentApplicationEvent.newBuilder()
				.setJobId(removeStudentApplicationEvent.getJobId())
				.setTaId(removeStudentApplicationEvent.getTaId())
				.build()
		);

		log.info(response.getMessage());
	}

	public void produceDeleteJobEvent(DeleteJobEvent deleteJobEvent) {
		ProduceEvents.SagaResponse response = eventProducerBlockingStub.produceDeleteJobEvent(
			ProduceEvents.DeleteJobEvent.newBuilder().setJobId(deleteJobEvent.getJobId()).build()
		);

		log.info(response.getMessage());
	}
}
