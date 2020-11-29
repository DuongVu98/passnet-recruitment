package com.iucse.passnet.recruitment.config;

import com.iucse.passnet.recruitment.adapter.channel.DomainEventBus;
import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.annotation.PrepareDomainEvent;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.usecase.commands.handlers.AbstractJobAggregateCommandHandler;
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
@Slf4j(topic = "[PrepareCommandHandlerAspect]")
public class PrepareCommandHandlerAspect {
	private final DomainEventBus eventBus;
	private final JobAggregateRepository aggregateRepository;

	@Autowired
	public PrepareCommandHandlerAspect(DomainEventBus eventBus, JobAggregateRepository aggregateRepository) {
		this.eventBus = eventBus;
		this.aggregateRepository = aggregateRepository;
	}

	@Pointcut("@annotation(com.iucse.passnet.recruitment.domain.annotation.PrepareDomainEvent)")
	public void getPrepareDomainEventAnnotation() {}

	@Pointcut("@annotation(com.iucse.passnet.recruitment.domain.annotation.PrepareCommandHandler)")
	public void getPrepareCommandHandler() {}

	@Around("getPrepareDomainEventAnnotation()")
	public Object prepareEvent(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("prepareEvent");

		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		PrepareDomainEvent annotation = methodSignature.getMethod().getAnnotation(PrepareDomainEvent.class);

		AbstractJobAggregateCommandHandler<Job> commandHandler = (AbstractJobAggregateCommandHandler<Job>) joinPoint.proceed();
		commandHandler.setEventToApply(annotation.value());
		commandHandler.setEventBus(this.eventBus);

		return commandHandler;
	}

	@Around("getPrepareCommandHandler()")
	public Object prepareCommandHandler(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("prepareCommandHandler");

		AbstractJobAggregateCommandHandler<Job> commandHandler = (AbstractJobAggregateCommandHandler<Job>) joinPoint.proceed();
		commandHandler.setAggregateRepository(this.aggregateRepository);

		return commandHandler;
	}
}
