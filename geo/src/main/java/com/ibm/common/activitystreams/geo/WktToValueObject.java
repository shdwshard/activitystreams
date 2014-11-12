package com.ibm.common.activitystreams.geo;

import java.util.Map;

import com.google.common.base.Function;
import com.google.common.collect.Maps;

public class WktToValueObject
  implements Function<Object[],Object>, Constants {

  @Override
  public Object apply(Object[] args) {
    if (args == null || args.length == 0 || args[0] == null)
      return null;
    Map<String,Object> map = Maps.newLinkedHashMap();
    map.put("@value", args[0].toString());
    map.put("@type", Constants.GS_WKTLITERAL);
    return map;
  }

}
