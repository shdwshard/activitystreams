package com.ibm.common.activitystreams.builder;

import java.util.Map;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.ibm.common.activitystreams.Constants;

public final class PrimitiveToValueObject 
  implements Function<Object[],Object>, Constants {

  public static final Function<Object[],Object> INSTANCE = 
    new PrimitiveToValueObject();
  
  @Override
  public Object apply(Object[] args) {
    if (args.length == 0 || args[0] == null) return null;
    Object primitive = args[0];
    Map<String,Object> map = Maps.newLinkedHashMap();
    if (primitive instanceof String) {
      map.put("@value", primitive);
      map.put("@type", XSD_STRING);
    } else if (primitive instanceof Integer || 
               primitive instanceof Long ||
               primitive instanceof Short) {
      map.put("@value", primitive);
      long v = ((Number)primitive).longValue();
      map.put("@type", v >= 0 ? XSD_NONNEGATIVEINTEGER : XSD_INTEGER);
    } else if (primitive instanceof Float) {
      map.put("@value", primitive);
      map.put("@type", XSD_FLOAT);
    } else if (primitive instanceof Double) {
      map.put("@value", primitive);
      map.put("@type", XSD_FLOAT);
    } else {
      map.put("@value", primitive);
    }
    return map;
  }
  
}
