package com.iucse.passnet.recruitment.config;

import com.cseiu.passnet.saga.recruitmentsaga.EventProducerGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SagaCommunicationConfiguration {
	@Value("${saga.grpc.host}")
	private String sagaHost;

	@Value("${saga.grpc.port}")
	private int sagaPort;

	@Bean
	public EventProducerGrpc.EventProducerBlockingStub eventProducerBlockingStub() {
		ManagedChannel channel = ManagedChannelBuilder.forAddress(sagaHost, sagaPort).usePlaintext().build();
		return EventProducerGrpc.newBlockingStub(channel);
	}
}
