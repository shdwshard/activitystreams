package com.ibm.common.activitystreams.builder;

import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.ibm.common.activitystreams.Constants;

public class DateTimeToValueObject
  implements Function<Object[],Object>, Constants {

  @Override
  public Object apply(Object[] args) {
    ReadableInstant dt = DateTime.now();
    if (args != null && args.length > 0 && args[0] instanceof ReadableInstant) {
      dt = (ReadableInstant) args[0];
    }
    Map<String,Object> map = Maps.newLinkedHashMap();
    map.put("@value", dt.toString());
    map.put("@type", XSD_DATETIME);
    return map;
  }

}
