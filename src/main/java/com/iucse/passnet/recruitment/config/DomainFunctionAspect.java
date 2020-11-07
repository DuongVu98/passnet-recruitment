package com.iucse.passnet.recruitment.config;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.repositories.TestRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j(topic = "[DomainFunctionAspect]")
@Aspect
@Component
public class DomainFunctionAspect {

    private TestRepository testRepository;

    @Autowired
    public DomainFunctionAspect(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Pointcut("@annotation(com.iucse.passnet.recruitment.domain.annotation.DomainFunction)")
    public void getDomainFunctionAnnotation(){
    }

    @Around("getDomainFunctionAnnotation()")
    public Object updateAggregate(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("execute aspect");
        Job object = (Job) joinPoint.getTarget();
        log.info("get object from aspect --> {}", object.getId());
        return joinPoint.proceed();
    }
}
