package com.camacho.simpleannotation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Builder {
    String[] fields() default {};
    String[] fieldsToIgnore() default {};
    boolean useFluentBuilder() default true;
    boolean useSingletonBuilder() default true;
}
