package com.ibm.common.activitystreams.geo;

import com.ibm.common.activitystreams.IO.Builder;
import com.ibm.common.activitystreams.Makers;
import com.ibm.common.activitystreams.ext.Module;

public final class GeometryModule implements Module {

  public static final GeometryModule instance = 
    new GeometryModule();
  
  @Override
  public void config(Builder io) {
    io.register(Geometry.class);    
  }

  public static Geometry.Builder geometry(Makers makers) {
    return makers.object(Geometry.Builder.class);
  }
}
