package com.ibm.common.activitystreams.impl;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.StringWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.ibm.common.activitystreams.ASObject;
import com.ibm.common.activitystreams.Activity;
import com.ibm.common.activitystreams.Base;
import com.ibm.common.activitystreams.Collection;
import com.ibm.common.activitystreams.IO;
import com.ibm.common.activitystreams.Link;
import com.ibm.common.activitystreams.Writable;
import com.ibm.common.activitystreams.ext.Handler;
import com.ibm.common.activitystreams.ext.InterfaceHandler;

public final class RootHandler 
  implements InvocationHandler {

  private final IO io;
  private final ImmutableMap<String,Object> map;
  
  private final static ImmutableMap<Object,InterfaceHandler> knownHandlers = 
    ImmutableMap.<Object,InterfaceHandler>builder()
    .put(Object.class, new ObjectHandler())
    .put(Base.class, new BaseHandler())
    .put(ASObject.class, new ASObjectHandler())
    .put(Link.class, new LinkHandler())
    .put(Collection.class, new CollectionHandler())
    .put(Writable.class, new WritableHandler())
    .put(Activity.class, new ActivityHandler())
    .build();
  
  public RootHandler(IO io, Map<String,Object> map) {
    this.io = io;
    this.map = ImmutableMap.copyOf(map);
  }
  
  @Override
  public Object invoke(
    Object proxy, 
    Method method, 
    Object[] args)
      throws Throwable {
    
    Class<?> declaringClass = method.getDeclaringClass();
    InterfaceHandler handler = knownHandlers.get(declaringClass);
    if (handler == null) {
      if (declaringClass.isAnnotationPresent(Handler.class)) {
        try {
          Handler _handler = declaringClass.getAnnotation(Handler.class);
          Class<? extends InterfaceHandler> _class = _handler.value();
          handler = _class.getConstructor().newInstance();
        } catch (Throwable t) {
          t.printStackTrace();
          throw new UnsupportedOperationException();
        }
      } else
        throw new UnsupportedOperationException();
    }
    if ("toString".equals(method.getName())) {
      return doToString(proxy);
    } else if ("extendWith".equals(method.getName()) && declaringClass.equals(Base.class)) {
      return doExtendWith(proxy,args);
    }
    return handler.invoke(io, map, method, args);
  }

  private Object doExtendWith(Object proxy, Object[] args) {
    checkArgument(args.length == 1);
    Class<?> _new = (Class<?>) args[0];
    ImmutableSet.Builder<Class<?>> _classes = 
      ImmutableSet.<Class<?>>builder()
        .add(proxy.getClass().getInterfaces());
    _classes.add(_new);
    ImmutableSet<Class<?>> set = _classes.build();
    InvocationHandler ih = Proxy.getInvocationHandler(proxy);
    ClassLoader cl = Base.class.getClassLoader();
    return Proxy.newProxyInstance(cl,set.toArray(new Class[set.size()]), ih);
  }
  
  private String doToString(Object proxy) {
    Writable writable = (Writable) proxy;
    StringWriter sw = new StringWriter();
    writable.writeTo(sw);
    return sw.toString();
  }
}
