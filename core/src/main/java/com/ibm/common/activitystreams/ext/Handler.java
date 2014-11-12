package com.ibm.common.activitystreams.ext;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
public @interface Handler {

  Class<? extends InterfaceHandler> value();
  String id() default "";
  
}
