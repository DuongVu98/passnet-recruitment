package com.iucse.passnet.recruitment.configuration;

import com.iucse.passnet.recruitment.adapter.channel.EventBus;
import com.iucse.passnet.recruitment.domain.annotation.Subscriber;
import com.iucse.passnet.recruitment.domain.events.Event;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import rx.Observer;

@Aspect
@Component
public class SubscribingAspect {
    @Autowired
    private ApplicationContext context;
    EventBus eventBus;

    @Pointcut("@annotation(com.iucse.passnet.recruitment.domain.annotation.Subscriber)")
    public void getSubscriberFromAnnotation() {
    }

    @Around("getSubscriberFromAnnotation()")
    public Object subscribe(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Subscriber subscriberAnnotation = methodSignature.getMethod().getAnnotation(Subscriber.class);

        this.eventBus = (EventBus) context.getBean(subscriberAnnotation.topic());

        Object proceed = joinPoint.proceed();
        if (proceed instanceof Observer) {
            this.eventBus.subscribe((Observer<Event>) proceed);
        }
        return proceed;
    }
}
