package com.ibm.common.activitystreams;

import com.google.common.base.Supplier;
import com.ibm.common.activitystreams.ext.ID;

public interface Base
  extends Writable, Constants {

  String id();
  Iterable<String> type();
  boolean has(String id);
  <T>T extendWith(Class<? extends T> _class);
  
  public static interface Builder<A extends Base, B extends Builder<A,B>>
    extends Supplier<A> {
     @ID(value="@id", single=true)
     B id(String id);
     @ID("@type")
     B type(String id);
  }
}