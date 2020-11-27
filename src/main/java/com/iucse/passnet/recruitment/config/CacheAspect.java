package com.iucse.passnet.recruitment.config;

import com.iucse.passnet.recruitment.domain.annotation.Cached;
import com.iucse.passnet.recruitment.domain.viewrepos.JobApplicationViewRepository;
import com.iucse.passnet.recruitment.domain.viewrepos.JobViewRepository;
import com.iucse.passnet.recruitment.domain.views.JobApplicationView;
import com.iucse.passnet.recruitment.domain.views.JobView;
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

    @Autowired
    public CacheAspect(JobViewRepository jobViewRepository, JobApplicationViewRepository jobApplicationViewRepository) {
        this.jobViewRepository = jobViewRepository;
        this.jobApplicationViewRepository = jobApplicationViewRepository;
    }

    @Pointcut("@annotation(com.iucse.passnet.recruitment.domain.annotation.Cached)")
    public void getToCacheAnnotation() {
    }

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
        }
        return proceed;
    }
}
