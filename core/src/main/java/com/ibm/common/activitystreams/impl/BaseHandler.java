package com.ibm.common.activitystreams.impl;

import java.lang.reflect.Method;
import java.util.Map;

import com.google.common.collect.ImmutableSet;
import com.ibm.common.activitystreams.Base;
import com.ibm.common.activitystreams.IO;
import com.ibm.common.activitystreams.ext.InterfaceHandler;

public final class BaseHandler implements InterfaceHandler {

  @Override
  public Object invoke(
    IO io, 
    Map<String, Object> map, 
    Method method,
    Object[] args) {
    Class<?> declaringClass = method.getDeclaringClass();
    String name = method.getName();
    if (!declaringClass.equals(Base.class))
      throw new UnsupportedOperationException();
    if ("has".equals(name))
      return doHas(map, (String)args[0]);
    else if ("id".equals(name))
      return map.get("@id");
    else if ("type".equals(name))
      return doType(map);
    return null;
  }

  private static boolean doHas(Map<String,Object> map, String id) {
    return map.containsKey(id);
  }
  
  @SuppressWarnings("unchecked")
  private static Iterable<String> doType(Map<String,Object> map) {
    Object obj = map.get("@type");
    if (obj == null)
      return ImmutableSet.<String>of();
    if (obj instanceof Iterable)
      return ImmutableSet.copyOf((Iterable<String>)obj);
    else
      throw new IllegalArgumentException(); // Invalid Input Exception?
  }
}
