package com.iucse.passnet.recruitment.config.aspects;

import com.iucse.passnet.recruitment.domain.annotation.Decorator;
import com.iucse.passnet.recruitment.usecase.executors.CommandExecutor;
import com.iucse.passnet.recruitment.usecase.factories.CommandExecutorDecoratorFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class CommandExecutorAspect {
    private final CommandExecutorDecoratorFactory decoratorFactory;

    @Autowired
    public CommandExecutorAspect(CommandExecutorDecoratorFactory decoratorFactory) {
        this.decoratorFactory = decoratorFactory;
    }

    @Pointcut("@annotation(com.iucse.passnet.recruitment.domain.annotation.Decorator)")
    public void decoratorPointcut() {
    }

    @Around("decoratorPointcut()")
    public Object advice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();

        Decorator decoratorAnnotation = AnnotationUtils.findAnnotation(method, Decorator.class);
        CommandExecutor commandExecutor = (CommandExecutor) proceedingJoinPoint.proceed();

        return this.decoratorFactory.produce(commandExecutor, decoratorAnnotation.decoratorType());
    }
}
