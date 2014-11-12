package com.ibm.common.activitystreams.builder;

import java.util.Map;

import org.joda.time.ReadableDuration;
import org.joda.time.ReadablePeriod;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.ibm.common.activitystreams.Constants;

public final class DurationToValueObject 
  implements Function<Object[],Object>, Constants {

  public static final Function<Object[],Object> INSTANCE = 
    new DurationToValueObject();
  
  @Override
  public Object apply(Object[] args) {
    if (args.length == 0 || args[0] == null) return null;
    Object primitive = args[0];
    Map<String,Object> map = Maps.newLinkedHashMap();
    if (primitive instanceof ReadableDuration || 
        primitive instanceof ReadablePeriod) {
      map.put("@value", primitive.toString());
    } else if (primitive instanceof Number) {
      map.put("@value", primitive);
    } else throw new IllegalArgumentException();
    return map;
  }
  
}
