package com.iucse.passnet.recruitment.config;

import com.iucse.passnet.recruitment.domain.viewrepos.JobApplicationViewRepository;
import com.iucse.passnet.recruitment.domain.viewrepos.JobViewRepository;
import com.iucse.passnet.recruitment.domain.views.JobApplicationView;
import com.iucse.passnet.recruitment.domain.views.JobView;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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

    @Pointcut("@annotation(com.iucse.passnet.recruitment.domain.annotation.ToCache)")
    public void getToCacheAnnotation() {
    }

    @Around("getToCacheAnnotation()")
    public Object toCache(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        if (proceed instanceof JobView) {
            this.jobViewRepository.save((JobView) proceed);
        } else if (proceed instanceof JobApplicationView) {
            this.jobApplicationViewRepository.save((JobApplicationView) proceed);
        } else if (proceed == null){
            // Do nothing
        }
        return proceed;
    }
}
