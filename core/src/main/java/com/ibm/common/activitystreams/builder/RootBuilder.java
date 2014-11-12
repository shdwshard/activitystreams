package com.ibm.common.activitystreams.builder;

import static com.google.common.base.Preconditions.checkArgument;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.ReadableDuration;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePeriod;

import com.google.common.base.Converter;
import com.google.common.base.Function;
import com.google.common.base.Supplier;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ibm.common.activitystreams.Constants;
import com.ibm.common.activitystreams.IO;
import com.ibm.common.activitystreams.ext.ID;
import com.ibm.common.activitystreams.ext.NonOpFunction;

public final class RootBuilder 
  implements InvocationHandler, Constants {

  private static final Method get = cacheSupplierGet();
  
  private final IO io;
  private final boolean isALink;
  private final Map<String,Object> map = 
    Maps.newLinkedHashMap();
  
  public RootBuilder(IO io, boolean isALink) {
    this.io = io;
    this.isALink = isALink;
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public Object invoke(
    Object proxy, 
    Method method, 
    Object[] args)
      throws Throwable {
    
    if (method.equals(get)) {
      return io.wrap(ImmutableMap.copyOf(map), isALink);
    } else {
      Class<?> declaringClass = method.getDeclaringClass();
      if (declaringClass == Object.class)
        return method.invoke(map, args);
      else {
        if (method.isAnnotationPresent(ID.class)) {
          ID id = method.getAnnotation(ID.class);
          String identifier = id.value();
          Function<Object,Object> transform = getTransform(id);
          Object val = 
            transform != null ? transform.apply(args) : 
              args.length > 0 ? args[0] : null;
          if (val != null) {
            if (id.single()) 
              map.put(identifier, val);
            else {
              List<Object> list = (List<Object>) map.get(identifier);
              if (list == null || id.functional()) {
                list = Lists.newArrayList();
                map.put(identifier, list);
              }
              list.add(val);
            }
          }
        }
        // step 1: determine which property is being set
        // step 2: determine what the value is
        // step 3: set the value
      }
    }
    return proxy;
  }
  
  @SuppressWarnings("unchecked")
  private Function<Object,Object> getTransform(ID id) {
    Class<? extends Function<?,?>> _class = id.transform();
    if (_class != null && !_class.equals(NonOpFunction.class)) {
      Function<Object,Object> ret = null;
      try {
        ret = (Function<Object, Object>) _class.getField("INSTANCE").get(_class);
      } catch (Throwable t) {}
      if (ret == null) {
        try {
          ret = (Function<Object, Object>) _class.getConstructor().newInstance();
        } catch (Throwable t) {}
      }
      return ret;
    }
    return null;
  }
  
  private static Method cacheSupplierGet() {
    try {
      return Supplier.class.getMethod("get");
    } catch (Throwable t) {
      throw Throwables.propagate(t);
    }
  }

}
