package com.ibm.common.activitystreams;

import com.google.common.base.Supplier;
import com.ibm.common.activitystreams.builder.BaseToObject;
import com.ibm.common.activitystreams.builder.PrimitiveToValueObject;
import com.ibm.common.activitystreams.ext.ID;

public interface Activity 
  extends ASObject {

  public static enum Status {
    UNSPECIFIED,
    ACTIVE,
    CANCELED,
    COMPLETED,
    PENDING,
    TENTATIVE,
    VOIDED;
  }
  
  <X extends Base>Iterable<X> actor();
  <X extends Base>Iterable<X> bcc();
  <X extends Base>Iterable<X> bto();
  <X extends Base>Iterable<X> cc();
  <X extends Base>Iterable<X> to();
  <X extends Base>Iterable<X> instrument();
  <X extends Base>Iterable<X> object();
  <X extends Base>Iterable<X> participant();
  <X extends Base>Iterable<X> result();
  <X extends Base>Iterable<X> target();
  double priority();
  Status status();
 
  public static interface Builder extends AbstractBuilder<Activity,Builder> {}
  
  public static abstract interface AbstractBuilder
    <A extends Activity, B extends AbstractBuilder<A,B>> 
    extends ASObject.AbstractBuilder<A,B> {
    
    @ID(value=ACTOR,transform=BaseToObject.class) 
    B actor(String uri);
    @ID(value=ACTOR,transform=BaseToObject.class) 
    B actor(ASObject obj);
    @ID(value=ACTOR,transform=BaseToObject.class) 
    B actor(Link link);
    @ID(value=ACTOR,transform=BaseToObject.class) 
    B actor(Supplier<? extends Base> obj);
    @ID(value=BCC,transform=BaseToObject.class)
    B bcc(String uri);
    @ID(value=BCC,transform=BaseToObject.class) 
    B bcc(ASObject obj);
    @ID(value=BCC,transform=BaseToObject.class) 
    B bcc(Link link);
    @ID(value=BCC,transform=BaseToObject.class) 
    B bcc(Supplier<? extends Base> obj);
    @ID(value=BTO,transform=BaseToObject.class) 
    B bto(String uri);
    @ID(value=BTO,transform=BaseToObject.class) 
    B bto(ASObject obj);
    @ID(value=BTO,transform=BaseToObject.class) 
    B bto(Link link);
    @ID(value=BTO,transform=BaseToObject.class) 
    B bto(Supplier<? extends Base> obj);
    @ID(value=CC,transform=BaseToObject.class) 
    B cc(String uri);
    @ID(value=CC,transform=BaseToObject.class) 
    B cc(ASObject obj);
    @ID(value=CC,transform=BaseToObject.class) 
    B cc(Link link);
    @ID(value=CC,transform=BaseToObject.class)
    B cc(Supplier<? extends Base> obj);
    @ID(value=TO,transform=BaseToObject.class)
    B to(String uri);
    @ID(value=TO,transform=BaseToObject.class)
    B to(ASObject obj);
    @ID(value=TO,transform=BaseToObject.class)
    B to(Link link);
    @ID(value=TO,transform=BaseToObject.class) 
    B to(Supplier<? extends Base> obj);
    @ID(value=INSTRUMENT,transform=BaseToObject.class)
    B instrument(String uri);
    @ID(value=INSTRUMENT,transform=BaseToObject.class) 
    B instrument(ASObject obj);
    @ID(value=INSTRUMENT,transform=BaseToObject.class) 
    B instrument(Link link);
    @ID(value=INSTRUMENT,transform=BaseToObject.class) 
    B instrument(Supplier<? extends Base> obj);
    @ID(value=OBJECT,transform=BaseToObject.class) 
    B object(String uri);
    @ID(value=OBJECT,transform=BaseToObject.class) 
    B object(ASObject obj);
    @ID(value=OBJECT,transform=BaseToObject.class) 
    B object(Link link);
    @ID(value=OBJECT,transform=BaseToObject.class) 
    B object(Supplier<? extends Base> obj);
    @ID(value=PARTICIPANT,transform=BaseToObject.class) 
    B participant(String uri);
    @ID(value=PARTICIPANT,transform=BaseToObject.class) 
    B participant(ASObject obj);
    @ID(value=PARTICIPANT,transform=BaseToObject.class) 
    B participant(Link link);
    @ID(value=PARTICIPANT,transform=BaseToObject.class) 
    B participant(Supplier<? extends Base> obj);
    @ID(value=RESULT,transform=BaseToObject.class) 
    B result(String uri);
    @ID(value=RESULT,transform=BaseToObject.class) 
    B result(ASObject obj);
    @ID(value=RESULT,transform=BaseToObject.class) 
    B result(Link link);
    @ID(value=RESULT,transform=BaseToObject.class) 
    B result(Supplier<? extends Base> obj);
    @ID(value=TARGET,transform=BaseToObject.class)
    B target(String uri);
    @ID(value=TARGET,transform=BaseToObject.class) 
    B target(ASObject obj);
    @ID(value=TARGET,transform=BaseToObject.class) 
    B target(Link link);
    @ID(value=TARGET,transform=BaseToObject.class) 
    B target(Supplier<? extends Base> obj);
    @ID(value=PRIORITY,transform=PrimitiveToValueObject.class,functional=true) 
    B priority(double priority);
  }
}
