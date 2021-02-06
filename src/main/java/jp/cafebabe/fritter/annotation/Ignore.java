package jp.cafebabe.fritter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Ignoring analysis of fritter.
 */
@Target({ElementType.TYPE, ElementType.LOCAL_VARIABLE, ElementType.FIELD,
        ElementType.CONSTRUCTOR, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Ignore {
}
