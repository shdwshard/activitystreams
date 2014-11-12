package com.ibm.common.activitystreams.impl;

import java.lang.reflect.Method;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.ibm.common.activitystreams.Collection;
import com.ibm.common.activitystreams.IO;
import com.ibm.common.activitystreams.ext.InterfaceHandler;

public final class CollectionHandler 
  extends AbstractInterfaceHandler {

  @Override
  public Object invoke(
    IO io, 
    Map<String, Object> map, 
    Method method,
    Object[] args) {
    Class<?> declaringClass = method.getDeclaringClass();
    String name = method.getName();
    if (!declaringClass.equals(Collection.class))
      throw new UnsupportedOperationException();
    InterfaceHandler handler = methods.get(name);
    if (handler == null)
      throw new UnsupportedOperationException();
    return handler.invoke(io, map, method, args);
  }

  private static final ImmutableMap<String,InterfaceHandler> methods = 
    ImmutableMap.<String,InterfaceHandler>builder()
    .put("totalItems", intPrimitiveHandler(TOTALITEMS))
    .put("startIndex", intPrimitiveHandler(STARTINDEX))
    .put("itemsPerPage", intPrimitiveHandler(ITEMSPERPAGE))
    .put("itemsAfter", dateTimePrimitiveHandler(ITEMSAFTER))
    .put("itemsBefore", dateTimePrimitiveHandler(ITEMSBEFORE))
    .put("items", wrapHandler(ITEMS))
    .put("current", linkHandler(CURRENT))
    .put("first", linkHandler(FIRST))
    .put("last", linkHandler(LAST))
    .put("next", linkHandler(NEXT))
    .put("prev", linkHandler(PREV))
    .put("self", linkHandler(SELF))
    .build();
}
