package com.ibm.common.activitystreams.impl;

import java.lang.reflect.Method;
import java.util.Map;

import com.google.common.base.Throwables;
import com.ibm.common.activitystreams.IO;
import com.ibm.common.activitystreams.ext.InterfaceHandler;
import com.sun.org.apache.xalan.internal.utils.Objects;

public final class ObjectHandler 
  implements InterfaceHandler {

  @Override
  public Object invoke(
    IO io,
    Map<String, Object> map, 
    Method method, 
    Object[] args) {
    
    Class<?> declaringClass = method.getDeclaringClass();
    String name = method.getName();
    if (declaringClass != Object.class)
      throw new java.lang.UnsupportedOperationException();
    
    if ("equals".equals(name))
      return doEquals(map, args[0]);
    else if ("hashCode".equals(name))
      return doHashCode(map);
    else {
      try {
        method.invoke(map, args);
      } catch (Throwable t) {
        throw Throwables.propagate(t);
      }
    }
    
    return null;
  }

  private static boolean doEquals(Map<String,Object> map, Object other) {
    return false;
  }
  
  private static int doHashCode(Map<String,Object> map) {
    return Objects.hashCode(map);
  }
  
}
