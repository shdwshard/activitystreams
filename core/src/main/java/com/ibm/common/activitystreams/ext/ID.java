package com.ibm.common.activitystreams.ext;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.google.common.base.Function;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({METHOD,TYPE})
public @interface ID {
  String value();
  boolean single() default false;
  boolean functional() default false;
  Class<? extends Function<?,?>> transform() default NonOpFunction.class;
}
