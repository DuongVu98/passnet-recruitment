package com.iucse.passnet.recruitment.domain.annotation;

import com.iucse.passnet.recruitment.domain.views.ViewTypes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cached {
    ViewTypes value();
}
