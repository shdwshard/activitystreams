package com.ibm.common.activitystreams;

import java.lang.reflect.Proxy;

import com.ibm.common.activitystreams.builder.RootBuilder;
import com.ibm.common.activitystreams.ext.ID;

public final class Makers implements Constants {

  private final IO io;
  
  protected Makers(IO io) {
    this.io = io;
  }
  
  @SuppressWarnings("unchecked")
  private <X>X proxy(boolean isALink, Class<?>... classes) {
    ClassLoader cl = Makers.class.getClassLoader();
    return (X)Proxy.newProxyInstance(
      cl, 
      classes, 
      new RootBuilder(io,isALink));
  }
  
  public Link.Builder link() {
    return this.<Link.Builder>proxy(true,Link.Builder.class)
      .type(TYPE_LINK);
  }
  
  public ASObject.Builder object() {
    return this.<ASObject.Builder>proxy(false,ASObject.Builder.class)
      .type(TYPE_OBJECT);
  }
  
  public <B extends ASObject.AbstractBuilder<?,B>>B object(
    Class<B> _class) {
      B builder = this.<B>proxy(false,_class);
      if (_class.isAnnotationPresent(ID.class)) {
        ID id = _class.getAnnotation(ID.class);
        if (!"".equals(id.value()))
          builder.type(id.value());
      }
      return builder;
  }
  
  public Collection.Builder collection() {
    return this.<Collection.Builder>proxy(false,Collection.Builder.class)
      .type(TYPE_COLLECTION);
  }
  
  public Activity.Builder activity() {
    return this.<Activity.Builder>proxy(false,Activity.Builder.class)
      .type(TYPE_ACTIVITY);
  }
  
  public <B extends Activity.AbstractBuilder<?, B>>B activity(
    Class<? extends Activity.AbstractBuilder<?, ?>> _class) {
      B builder = this.<B>proxy(false,_class).type(TYPE_ACTIVITY);
      if (_class.isAnnotationPresent(ID.class)) {
        ID id = _class.getAnnotation(ID.class);
        if (!"".equals(id.value()))
          builder.type(id.value());
      }
      return builder;
  }
}
