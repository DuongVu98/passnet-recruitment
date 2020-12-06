package com.iucse.passnet.recruitment.adapter.grpc;

import com.cseiu.passnet.saga.recruitmentsaga.Example.*;
import com.cseiu.passnet.saga.recruitmentsaga.GreetingGrpc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "[CommandGateway]")
public class CommandGateway {
	private GreetingGrpc.GreetingBlockingStub greetingStub;

	@Autowired
	public CommandGateway(GreetingGrpc.GreetingBlockingStub greetingStub) {
		this.greetingStub = greetingStub;
	}

	public void sendRequest() {
		HelloReply helloReply = greetingStub.sayHello(HelloRequest.newBuilder().setName("Tony").build());
		log.info(helloReply.getMessage());
	}
}
