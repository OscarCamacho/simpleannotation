package com.camacho.simpleannotation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This Annotation denotes that classes tagged with it
 * will receive at runtime builder functionality through the
 * methods ....
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Builder {
    /** Fields from the bean to include in the builder. */
    String[] fields() default {};

    /** Fields from the bean to ignore in the builder. */
    String[] fieldsToIgnore() default {};
    /** Indicates that the resulting builder will be chainable. */
    boolean useFluentBuilder() default true;
    /** Indicates that the resulting builder will be singleton. */
    boolean useSingletonBuilder() default true;
}
