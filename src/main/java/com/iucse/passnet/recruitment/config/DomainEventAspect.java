package com.iucse.passnet.recruitment.config;

import com.iucse.passnet.recruitment.adapter.channel.DomainEventBus;
import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.annotation.ApplyDomainEvent;
import com.iucse.passnet.recruitment.domain.annotation.Cached;
import com.iucse.passnet.recruitment.usecase.events.events.DomainEvent;
import com.iucse.passnet.recruitment.usecase.events.events.EventTypes;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j(topic = "[DomainEventAspect]")
public class DomainEventAspect {

    private final DomainEventBus domainEventBus;

    @Autowired
    public DomainEventAspect(DomainEventBus domainEventBus) {
        this.domainEventBus = domainEventBus;
    }

    @Pointcut("@annotation(com.iucse.passnet.recruitment.domain.annotation.ApplyDomainEvent)")
    public void getToApplyDomainEventAnnotation() {
    }

    @Around("getToApplyDomainEventAnnotation()")
    public Object apply(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        ApplyDomainEvent annotation = methodSignature.getMethod().getAnnotation(ApplyDomainEvent.class);

        Job aggregate = (Job) joinPoint.proceed();

        switch (annotation.value()){
            case TeacherPostedJob:
                log.info("TeacherPostedJob");
                this.domainEventBus.send(new DomainEvent(EventTypes.TeacherPostedJob, aggregate));
                break;
            case StudentAppliedJob:
                log.info("StudentAppliedJob");
                this.domainEventBus.send(new DomainEvent(EventTypes.StudentAppliedJob, aggregate));
                break;
        }

        return aggregate;
    }
}
