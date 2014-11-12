package com.ibm.common.activitystreams.geo;

import com.google.common.collect.ImmutableMap;
import com.ibm.common.activitystreams.ext.InterfaceHandler;
import com.ibm.common.activitystreams.ext.SimpleInterfaceHandler;

public class BasicGeoHandler 
  extends SimpleInterfaceHandler
  implements Constants {

  protected BasicGeoHandler() {
    super(BasicGeoPoint.class);
  }

  @Override
  protected ImmutableMap<String, InterfaceHandler> init() {
    return ImmutableMap.<String,InterfaceHandler>builder()
      .put("longitude", doublePrimitiveHandler(GEO_LONG))
      .put("latitude", doublePrimitiveHandler(GEO_LAT))
      .put("altitude", doublePrimitiveHandler(GEO_ALT))
      .build();
  }


}
