package com.ibm.common.activitystreams.geo;

import com.ibm.common.activitystreams.ASObject;
import com.ibm.common.activitystreams.IO;
import com.ibm.common.activitystreams.Makers;

public class Test {

  public static void main(String... args) throws Exception {
    
    IO io = IO.make()
      .prettyPrint()
      .regiser(BasicGeoModule.instance)
      .get();
    
    Makers makers = io.makers();
    
    ASObject obj = 
      makers.object()
        .location(
          makers.object(BasicGeoPoint.Builder.class)
            .latitude(1)
            .longitude(1)
            .altitude(1))
        .get();
    
    System.out.println(obj);
  }
  
}
