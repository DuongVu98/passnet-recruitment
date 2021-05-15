package com.iucse.passnet.recruitment.adapter.grpc;

import com.cseiu.passnet.saga.recruitmentsaga.CompensatingExecutorGrpc;
import com.cseiu.passnet.saga.recruitmentsaga.ConsumeEvents;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
@Slf4j(topic = "[CompensatingGateway]")
public class CompensatingGateway extends CompensatingExecutorGrpc.CompensatingExecutorImplBase {

    // TODO: implement rollback for eventId
    @Override
    public void rollback(ConsumeEvents.EventId request, StreamObserver<ConsumeEvents.ServiceResponse> responseObserver) {
        log.info("Receive compensating request with eventId [{}]", request.getEventId());
        super.rollback(request, responseObserver);
    }
}
