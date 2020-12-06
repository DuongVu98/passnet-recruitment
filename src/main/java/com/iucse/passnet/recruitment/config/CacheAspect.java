package com.iucse.passnet.recruitment.config;

import com.iucse.passnet.recruitment.domain.annotation.Cached;
import com.iucse.passnet.recruitment.domain.viewrepos.JobApplicationViewRepository;
import com.iucse.passnet.recruitment.domain.viewrepos.JobViewRepository;
import com.iucse.passnet.recruitment.domain.viewrepos.PostedJobsViewRepository;
import com.iucse.passnet.recruitment.domain.views.JobApplicationView;
import com.iucse.passnet.recruitment.domain.views.JobView;
import com.iucse.passnet.recruitment.domain.views.PostedJobsView;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CacheAspect {
	private final JobViewRepository jobViewRepository;
	private final JobApplicationViewRepository jobApplicationViewRepository;
	private final PostedJobsViewRepository postedJobsViewRepository;

	@Autowired
	public CacheAspect(
		JobViewRepository jobViewRepository,
		JobApplicationViewRepository jobApplicationViewRepository,
		PostedJobsViewRepository postedJobsViewRepository
	) {
		this.jobViewRepository = jobViewRepository;
		this.jobApplicationViewRepository = jobApplicationViewRepository;
		this.postedJobsViewRepository = postedJobsViewRepository;
	}

	@Pointcut("@annotation(com.iucse.passnet.recruitment.domain.annotation.Cached)")
	public void getToCacheAnnotation() {}

	@Around("getToCacheAnnotation()")
	public Object toCache(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Cached cachedAnnotation = methodSignature.getMethod().getAnnotation(Cached.class);

		Object proceed = joinPoint.proceed();

		switch (cachedAnnotation.value()) {
			case JOB_VIEW:
				this.jobViewRepository.save((JobView) proceed);
				break;
			case JOB_APPLICATION_VIEW:
				this.jobApplicationViewRepository.save((JobApplicationView) proceed);
				break;
			case POSTED_JOBS_VIEW:
				this.postedJobsViewRepository.save((PostedJobsView) proceed);
				break;
		}

		return proceed;
	}
}
