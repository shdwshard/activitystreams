package com.ibm.common.activitystreams.builder;

import java.util.Map;

import com.google.common.base.Function;
import com.google.common.base.Supplier;
import com.google.common.collect.Maps;
import com.ibm.common.activitystreams.Base;
import com.ibm.common.activitystreams.Constants;

public final class BaseToObject
  implements Function<Object[],Object>, Constants {

  @Override
  public Object apply(Object[] args) {
    if (args == null || args.length == 0)
      return null;
    Object obj = args[0];
    Map<String,Object> map = null;
    if (obj instanceof String) {
      map = Maps.newLinkedHashMap();
      map.put("@id", obj.toString());
    } else if (obj instanceof Supplier) {
      return apply(new Object[] {((Supplier<?>)obj).get()});
    } else if (obj instanceof Base) {
      Base base = (Base)obj;
      map = base.map();
    } else throw new IllegalArgumentException();
    return map;
  }

}
