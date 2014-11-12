package com.ibm.common.activitystreams.ext;

import java.lang.reflect.Method;
import java.util.Map;

import com.ibm.common.activitystreams.IO;

public interface InterfaceHandler {

  Object invoke(IO io, Map<String,Object> map, Method method, Object[] args);
  
}
