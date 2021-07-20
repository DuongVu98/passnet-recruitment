package com.iucse.passnet.recruitment.adapter.grpc;

import com.cseiu.passnet.saga.recruitmentsaga.CompensatingExecutorGrpc;
import com.cseiu.passnet.saga.recruitmentsaga.ConsumeEvents;
import com.iucse.passnet.recruitment.domain.compensating.AcceptJobApplicationCompensating;
import com.iucse.passnet.recruitment.domain.compensating.CompensatingCommand;
import com.iucse.passnet.recruitment.domain.compensating.RemoveJobApplicationCompensating;
import com.iucse.passnet.recruitment.domain.exceptions.CompensatingHandlerNorFoundException;
import com.iucse.passnet.recruitment.usecase.executors.CompensatingHandler;
import com.iucse.passnet.recruitment.usecase.factories.CompensatingHandlerFactory;
import com.iucse.passnet.recruitment.usecase.services.CompensatingCommandBackupService;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
@Slf4j(topic = "[CompensatingGateway]")
public class CompensatingGrpcController extends CompensatingExecutorGrpc.CompensatingExecutorImplBase {
	private final CompensatingCommandBackupService compensatingCommandBackupService;
	private final CompensatingHandlerFactory compensatingHandlerFactory;

	@Autowired
	public CompensatingGrpcController(
		CompensatingCommandBackupService compensatingCommandBackupService,
		CompensatingHandlerFactory compensatingHandlerFactory
	) {
		this.compensatingCommandBackupService = compensatingCommandBackupService;
		this.compensatingHandlerFactory = compensatingHandlerFactory;
	}

	@Override
	public void rollback(ConsumeEvents.EventId request, StreamObserver<ConsumeEvents.ServiceResponse> responseObserver) {
		log.info("Receive compensating request with eventId [{}]", request.getEventId());

		var compensatingCommand = compensatingCommandBackupService.getFromStore(request.getEventId());
		try {
			var compensatingHandler = buildHandle(compensatingCommand);
			compensatingHandler.reverse(compensatingCommand);

			responseObserver.onNext(ConsumeEvents.ServiceResponse.newBuilder().setMessage("SUCCESS").build());
		} catch (CompensatingHandlerNorFoundException exception) {
			log.error("CompensatingHandlerNorFound: {}", exception.getMessage());
		} finally {
			compensatingCommandBackupService.removeFromStore(request.getEventId());
			responseObserver.onCompleted();
		}
	}

	private CompensatingHandler buildHandle(CompensatingCommand command) {
		if (command instanceof AcceptJobApplicationCompensating) {
			return compensatingHandlerFactory.produce((AcceptJobApplicationCompensating) command);
		} else if (command instanceof RemoveJobApplicationCompensating) {
			return compensatingHandlerFactory.produce((RemoveJobApplicationCompensating) command);
		} else {
			throw new CompensatingHandlerNorFoundException("Compensating type not found");
		}
	}
}
