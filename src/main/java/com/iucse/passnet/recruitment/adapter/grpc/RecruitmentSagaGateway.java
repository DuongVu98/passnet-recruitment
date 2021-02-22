package com.iucse.passnet.recruitment.adapter.grpc;

import com.cseiu.passnet.saga.recruitmentsaga.EventProducerGrpc;
import com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents;

import com.iucse.passnet.recruitment.usecase.events.produce.CreateClassEvent;

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

	public void produceCreateClassEvent(CreateClassEvent createClassEvent) {
		ProduceEvents.SagaResponse response = eventProducerBlockingStub.produceCreateClassEvent(
			ProduceEvents.CreateClassEvent.newBuilder().setTeacherId(createClassEvent.getTeacherId()).addAllTaIds(createClassEvent.getTaIdList()).build()
		);
		log.info(response.getMessage());
	}
}
