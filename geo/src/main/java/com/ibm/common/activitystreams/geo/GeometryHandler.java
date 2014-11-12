package com.ibm.common.activitystreams.geo;

import com.google.common.collect.ImmutableMap;
import com.ibm.common.activitystreams.ext.InterfaceHandler;
import com.ibm.common.activitystreams.ext.SimpleInterfaceHandler;

public class GeometryHandler 
  extends SimpleInterfaceHandler
  implements Constants {

  protected GeometryHandler() {
    super(Geometry.class);
  }

  @Override
  protected ImmutableMap<String, InterfaceHandler> init() {
    return ImmutableMap.<String,InterfaceHandler>builder()
      .put("asWKT", stringPrimitiveHandler(GS_ASWKT))
      .build();
  }


}
