package com.ibm.common.activitystreams.impl;

import java.lang.reflect.Method;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.ibm.common.activitystreams.Constants;
import com.ibm.common.activitystreams.IO;
import com.ibm.common.activitystreams.Link;
import com.ibm.common.activitystreams.ext.InterfaceHandler;

public final class LinkHandler 
  extends AbstractInterfaceHandler
  implements InterfaceHandler, Constants {

  @Override
  public Object invoke(
    IO io, 
    Map<String, Object> map, 
    Method method,
    Object[] args) {
    Class<?> declaringClass = method.getDeclaringClass();
    String name = method.getName();
    if (!declaringClass.equals(Link.class))
      throw new UnsupportedOperationException();
    InterfaceHandler handler = methods.get(name);
    if (handler == null)
      throw new UnsupportedOperationException();
    return handler.invoke(io, map, method, args);
  }

  private static final ImmutableMap<String,InterfaceHandler> methods = 
    ImmutableMap.<String,InterfaceHandler>builder()
    .put("href", idPrimitiveHandler(HREF))
    .put("hreflang", stringPrimitiveHandler(HREFLANG))
    .put("rel", stringPrimitiveHandler(REL))
    .put("width", intPrimitiveHandler(WIDTH))
    .put("height", intPrimitiveHandler(HEIGHT))
    .put("title", nlvHandler(TITLE))
    .put("displayName", nlvHandler(DISPLAYNAME))
    .put("duration", durationHandler(DURATION))
    .put("mediaType", mediaTypeHandler(MEDIATYPE))
    .build();
}
