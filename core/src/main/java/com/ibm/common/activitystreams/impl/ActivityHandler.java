package com.ibm.common.activitystreams.impl;

import java.lang.reflect.Method;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.ibm.common.activitystreams.Activity;
import com.ibm.common.activitystreams.Activity.Status;
import com.ibm.common.activitystreams.ext.InterfaceHandler;
import com.ibm.common.activitystreams.IO;

public final class ActivityHandler 
  extends AbstractInterfaceHandler {

  @Override
  public Object invoke(
    IO io, 
    Map<String, Object> map, 
    Method method,
    Object[] args) {
    Class<?> declaringClass = method.getDeclaringClass();
    String name = method.getName();
    if (!declaringClass.equals(Activity.class))
      throw new UnsupportedOperationException();
    InterfaceHandler handler = methods.get(name);
    if (handler == null)
      throw new UnsupportedOperationException();
    return handler.invoke(io, map, method, args);
  }

  private static final ImmutableMap<String,InterfaceHandler> methods = 
    ImmutableMap.<String,InterfaceHandler>builder()
    .put("actor", wrapHandler(ACTOR))
    .put("bcc", wrapHandler(BCC))
    .put("bto", wrapHandler(BTO))
    .put("cc", wrapHandler(CC))
    .put("instrument", wrapHandler(INSTRUMENT))
    .put("object", wrapHandler(OBJECT))
    .put("participant", wrapHandler(PARTICIPANT))
    .put("result", wrapHandler(RESULT))
    .put("target", wrapHandler(TARGET))
    .put("priority", doublePrimitiveHandler(PRIORITY))
    .put("status", enumHandler(STATUS, Status.class))
    .build();
}
