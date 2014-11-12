package com.ibm.common.activitystreams.ext;

import java.util.Map;

import com.google.common.base.Function;

public interface Preprocessor 
  extends Function<Map<String,Object>, Map<String,Object>>{
  
}
