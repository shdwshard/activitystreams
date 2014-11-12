package com.ibm.common.activitystreams.geo;

import com.ibm.common.activitystreams.ASObject;
import com.ibm.common.activitystreams.ext.Handler;
import com.ibm.common.activitystreams.ext.ID;

@Handler(value=BasicGeoHandler.class,id=Constants.GS_TYPE_GEOMETRY)
public interface Geometry 
  extends ASObject, Constants {

  String asWKT();
  
  @ID(value=GS_TYPE_GEOMETRY)
  public static interface Builder 
    extends ASObject.AbstractBuilder<Geometry, Builder> {
    
    @ID(value=GS_ASWKT,transform=WktToValueObject.class,functional=true)
    Builder asWKT(String wkt);
    
  }
  
}
