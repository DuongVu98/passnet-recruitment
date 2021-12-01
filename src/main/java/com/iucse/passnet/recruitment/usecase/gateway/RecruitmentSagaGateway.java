package com.iucse.passnet.recruitment.usecase.gateway;

import com.cseiu.passnet.saga.recruitmentsaga.EventProducerGrpc;
import com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents;
import com.iucse.passnet.recruitment.domain.events.AcceptStudentApplicationEvent;
import com.iucse.passnet.recruitment.domain.events.DeleteJobEvent;
import com.iucse.passnet.recruitment.domain.events.PostNewJobEvent;
import com.iucse.passnet.recruitment.domain.events.RemoveStudentApplicationEvent;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "[CommandGateway]")
public class RecruitmentSagaGateway {

    @GrpcClient("saga-recruitment")
    private EventProducerGrpc.EventProducerBlockingStub eventProducerBlockingStub;

    @EventListener
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

    @EventListener
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

    @EventListener
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

    @EventListener
    public void on(DeleteJobEvent deleteJobEvent) {
        var response = eventProducerBlockingStub.produceDeleteJobEvent(
           ProduceEvents.DeleteJobEvent.newBuilder().setEventId(deleteJobEvent.getEventId()).setJobId(deleteJobEvent.getJobId()).build()
        );

        log.info(response.getMessage());
    }
}
