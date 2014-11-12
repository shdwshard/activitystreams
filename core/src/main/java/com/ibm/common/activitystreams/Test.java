package com.ibm.common.activitystreams;

import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Test {

  public static void main(String... args) throws Exception {
    
    FileInputStream in = new FileInputStream("/Users/james/Workspaces/projects/activitystreams/core/src/test/resources/data/1.json");
    InputStreamReader reader = new InputStreamReader(in, "UTF-8");
    IO io = IO.make()
      .prettyPrint()
      .base("http://example.org/")
      .get();
    
    Makers makers = io.makers();
    Activity obj = 
      makers.activity()
        .publishedNow()
        .summary("testing", "fr")
        .get();
    
    System.out.println(obj);
    
  }

}
