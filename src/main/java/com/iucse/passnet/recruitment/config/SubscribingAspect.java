package com.iucse.passnet.recruitment.config;

import com.iucse.passnet.recruitment.adapter.channel.EventBus;
import com.iucse.passnet.recruitment.domain.annotation.Subscriber;
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
    private ApplicationContext context;
    private EventBus eventBus;

    @Autowired
    public SubscribingAspect(ApplicationContext context) {
        this.context = context;
    }

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
            this.eventBus.subscribe((Observer) proceed);
        }
        return proceed;
    }
}
