package com.ibm.common.activitystreams.geo;

import com.ibm.common.activitystreams.IO.Builder;
import com.ibm.common.activitystreams.ext.Module;

public final class BasicGeoModule implements Module {

  public static final BasicGeoModule instance = 
    new BasicGeoModule();
  
  @Override
  public void config(Builder io) {
    io.register(BasicGeoPoint.class);    
  }

}
