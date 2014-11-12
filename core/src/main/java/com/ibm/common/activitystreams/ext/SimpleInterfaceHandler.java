package com.ibm.common.activitystreams.ext;

import java.lang.reflect.Method;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.ibm.common.activitystreams.IO;
import com.ibm.common.activitystreams.impl.AbstractInterfaceHandler;

public abstract class SimpleInterfaceHandler 
  extends AbstractInterfaceHandler {

  private final ImmutableMap<String,InterfaceHandler> methods;
  private final Class<?> _class;
  
  protected SimpleInterfaceHandler(Class<?> _class) {
    this.methods = init();
    this._class = _class;
  }
  
  protected abstract ImmutableMap<String,InterfaceHandler> init();
  
  @Override
  public final Object invoke(
    IO io, 
    Map<String, Object> map, 
    Method method,
    Object[] args) {
    Class<?> declaringClass = method.getDeclaringClass();
    String name = method.getName();
    if (!declaringClass.equals(_class))
      throw new UnsupportedOperationException();
    InterfaceHandler handler = methods.get(name);
    if (handler == null)
      throw new UnsupportedOperationException();
    return handler.invoke(io, map, method, args);
  }

}
