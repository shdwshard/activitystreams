package com.ibm.common.activitystreams.builder;

import java.util.Map;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.ibm.common.activitystreams.Constants;

public final class LanguageTaggedValueObject
  implements Function<Object[],Object>, Constants {

  @Override
  public Object apply(Object[] args) {
    if (args.length == 0 || args[0] == null) return null;
    Object val = args[0];
    Object lang = args.length > 1 ? args[1] : null;
    Map<String,Object> map = Maps.newLinkedHashMap();
    map.put("@value", val.toString());
    if (lang != null)
      map.put("@language", lang.toString());
    return map;
  }

}
