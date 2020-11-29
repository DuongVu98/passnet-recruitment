package com.iucse.passnet.recruitment.mock;

import com.iucse.passnet.recruitment.adapter.channel.DomainEventBus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EventBusTest {

    @Autowired
    private DomainEventBus domainEventBus;

    @Test
    public void multipleMessagesObservableTest(){
    }
}
