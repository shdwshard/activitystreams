package com.ibm.common.activitystreams.builder;

import java.util.Map;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.ibm.common.activitystreams.Constants;

public final class MediaTypeToValueObject 
  implements Function<Object[],Object>, Constants {

  public static final Function<Object[],Object> INSTANCE = 
    new MediaTypeToValueObject();
  
  @Override
  public Object apply(Object[] args) {
    if (args.length == 0 || args[0] == null) return null;
    Object primitive = args[0];
    if (primitive == null) return null;
    Map<String,Object> map = Maps.newLinkedHashMap();
    map.put("@value", primitive.toString());
    return map;
  }
  
}
