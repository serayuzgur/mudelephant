package io.mudelephant.athlete.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotating an Athlete resource method, wraps the method in a JPA session.
 */
@Target(METHOD)
@Retention(RUNTIME)
@Documented
public @interface JPAOperation {

    /**
     * If {@code true}, a transaction will be automatically started before the resource method is
     * invoked, committed if the method returned, and rolled back if an exception was thrown.
     */
    boolean transactional() default true;

}
