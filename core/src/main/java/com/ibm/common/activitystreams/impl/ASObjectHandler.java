package com.ibm.common.activitystreams.impl;

import java.lang.reflect.Method;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.ibm.common.activitystreams.ASObject;
import com.ibm.common.activitystreams.Constants;
import com.ibm.common.activitystreams.IO;
import com.ibm.common.activitystreams.ext.InterfaceHandler;

public final class ASObjectHandler 
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
    if (!declaringClass.equals(ASObject.class))
      throw new UnsupportedOperationException();
    InterfaceHandler handler = methods.get(name);
    if (handler == null)
      throw new UnsupportedOperationException();
    return handler.invoke(io, map, method, args);
  }

  private static final ImmutableMap<String,InterfaceHandler> methods = 
    ImmutableMap.<String,InterfaceHandler>builder()
    .put("alias", stringPrimitiveHandler(ALIAS))
    .put("width", intPrimitiveHandler(WIDTH))
    .put("height", intPrimitiveHandler(HEIGHT))
    .put("rating", doublePrimitiveHandler(RATING))
    .put("endTime", dateTimePrimitiveHandler(ENDTIME))
    .put("published", dateTimePrimitiveHandler(PUBLISHED))
    .put("startTime", dateTimePrimitiveHandler(STARTTIME))
    .put("updated", dateTimePrimitiveHandler(UPDATED))
    .put("validAfter", dateTimePrimitiveHandler(VALIDAFTER))
    .put("validBefore", dateTimePrimitiveHandler(VALIDBEFORE))
    .put("validFrom", dateTimePrimitiveHandler(VALIDFROM))
    .put("validUntil", dateTimePrimitiveHandler(VALIDUNTIL))
    .put("title", nlvHandler(TITLE))
    .put("displayName", nlvHandler(DISPLAYNAME))
    .put("summary", nlvHandler(SUMMARY))
    .put("content", nlvHandler(CONTENT))
    .put("attachment", wrapHandler(ATTACHMENT))
    .put("author", wrapHandler(AUTHOR))
    .put("generator", wrapHandler(GENERATOR))
    .put("icon", wrapHandler(ICON))
    .put("image", wrapHandler(IMAGE))
    .put("inReplyTo", wrapHandler(INREPLYTO))
    .put("location", wrapHandler(LOCATION))
    .put("provider", wrapHandler(PROVIDER))
    .put("scope", wrapHandler(SCOPE))
    .put("tag", wrapHandler(TAG))
    .put("url", wrapHandler(URL, true))
    .put("duration", durationHandler(DURATION))
    .put("replies", collectionHandler(REPLIES))
    .build();
}
