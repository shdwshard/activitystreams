package com.ibm.common.activitystreams.builder;

import java.util.Map;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.ibm.common.activitystreams.Constants;

public final class PrimitiveToIDObject 
  implements Function<Object[],Object>, Constants {

  public static final Function<Object[],Object> INSTANCE = 
    new PrimitiveToIDObject();
  
  @Override
  public Object apply(Object[] args) {
    if (args.length == 0 || args[0] == null) return null;
    Object primitive = args[0];
    if (primitive instanceof String) {
      Map<String,Object> map = Maps.newLinkedHashMap();
      map.put("@id", primitive.toString());
      return map;
    } else throw new IllegalArgumentException();
  }
  
}
