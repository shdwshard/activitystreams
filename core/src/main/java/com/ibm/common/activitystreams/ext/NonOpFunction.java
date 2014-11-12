package com.ibm.common.activitystreams.ext;

import com.google.common.base.Function;

public final class NonOpFunction 
  implements Function<Object, Object> {
  
  @Override
  public Object apply(Object arg) {
    return arg;
  }
}
