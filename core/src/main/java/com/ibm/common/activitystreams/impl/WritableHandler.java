package com.ibm.common.activitystreams.impl;

import java.lang.reflect.Method;
import java.util.Map;

import com.google.common.base.Throwables;
import com.ibm.common.activitystreams.IO;
import com.ibm.common.activitystreams.Writable;
import com.ibm.common.activitystreams.ext.InterfaceHandler;

public final class WritableHandler implements InterfaceHandler {
  @Override
  public Object invoke(
    IO io, 
    Map<String, Object> map, 
    Method method,
    Object[] args) {
    Class<?> declaringClass = method.getDeclaringClass();
    String name = method.getName();
    if (!declaringClass.equals(Writable.class))
      throw new UnsupportedOperationException();
    if ("writeTo".equals(name)) {
      try {
        WritableImpl wi = new WritableImpl(map,io);
        return method.invoke(wi, args);
      } catch (Throwable t) {
        throw Throwables.propagate(t);
      }
    } else if ("map".equals(name)) {
      return map;
    } else 
      throw new UnsupportedOperationException();
  }

}
