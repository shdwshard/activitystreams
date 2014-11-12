package com.ibm.common.activitystreams.geo;

import com.ibm.common.activitystreams.ASObject;
import com.ibm.common.activitystreams.builder.PrimitiveToValueObject;
import com.ibm.common.activitystreams.ext.Handler;
import com.ibm.common.activitystreams.ext.ID;

@Handler(value=BasicGeoHandler.class,id=Constants.GEO_TYPE_POINT)
public interface BasicGeoPoint 
  extends ASObject, Constants {

  double latitude();
  double longitude();
  double altitude();
  
  @ID(value=GEO_TYPE_POINT)
  public static interface Builder 
    extends ASObject.AbstractBuilder<BasicGeoPoint, Builder> {
    
    @ID(value=GEO_LAT,transform=PrimitiveToValueObject.class,functional=true)
    Builder latitude(double latitude);
    @ID(value=GEO_LONG,transform=PrimitiveToValueObject.class,functional=true)
    Builder longitude(double longitude);
    @ID(value=GEO_ALT,transform=PrimitiveToValueObject.class,functional=true)
    Builder altitude(double altitude);
    
  }
  
}
