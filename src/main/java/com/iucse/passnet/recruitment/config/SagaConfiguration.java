package com.iucse.passnet.recruitment.config;

import com.cseiu.passnet.saga.recruitmentsaga.GreetingGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SagaConfiguration {
	@Value("${saga.grpc.host}")
	private String serviceHost;

	@Value("${saga.grpc.port}")
	private int servicePort;

	@Bean
	public GreetingGrpc.GreetingBlockingStub getStub() {
		ManagedChannel channel = ManagedChannelBuilder.forAddress(serviceHost, servicePort).usePlaintext().build();
		GreetingGrpc.GreetingBlockingStub greetingStub = GreetingGrpc.newBlockingStub(channel);
		return greetingStub;
	}
}
