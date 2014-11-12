package com.ibm.common.activitystreams;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.ReadableDuration;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePeriod;

import com.ibm.common.activitystreams.builder.DateTimeToValueObject;
import com.ibm.common.activitystreams.builder.DurationToValueObject;
import com.ibm.common.activitystreams.builder.LanguageTaggedValueObject;
import com.ibm.common.activitystreams.builder.PrimitiveToIDObject;
import com.ibm.common.activitystreams.builder.PrimitiveToValueObject;
import com.ibm.common.activitystreams.builder.BaseToObject;
import com.ibm.common.activitystreams.ext.ID;
import com.google.common.base.Supplier;

public interface ASObject 
  extends Base {
  
  <X extends Base>Iterable<X> attachment();  
  <X extends Base>Iterable<X> author();
  <X extends Base>Iterable<X> generator(); 
  <X extends Base>Iterable<X> icon();  
  <X extends Base>Iterable<X> image();  
  <X extends Base>Iterable<X> inReplyTo();
  <X extends Base>Iterable<X> location();  
  <X extends Base>Iterable<X> provider();   
  <X extends Base>Iterable<X> scope();    
  <X extends Base>Iterable<X> tag();
  Iterable<Link> url();
  NLV content();  
  NLV displayName();
  NLV summary(); 
  NLV title(); 
  Collection replies(); 
  Period duration();  
  DateTime endTime();
  DateTime published(); 
  DateTime startTime(); 
  DateTime updated();  
  DateTime validAfter();  
  DateTime validBefore();  
  DateTime validFrom();  
  DateTime validUntil();
  String alias();
  int height(); 
  int width();  
  double rating(); 
  
  public static interface Builder extends AbstractBuilder<ASObject,Builder> {}
  
  public static abstract interface AbstractBuilder
    <A extends ASObject, B extends AbstractBuilder<A,B>> 
      extends Base.Builder<A, B> {
    @ID(value=ATTACHMENT,transform=BaseToObject.class) 
    B attachment(String uri);
    @ID(value=ATTACHMENT,transform=BaseToObject.class) 
    B attachment(ASObject obj);
    @ID(value=ATTACHMENT,transform=BaseToObject.class) 
    B attachment(Supplier<? extends Base> val);
    @ID(value=ATTACHMENT,transform=BaseToObject.class) 
    B attachment(Link link);
    @ID(value=AUTHOR,transform=BaseToObject.class) 
    B author(String uri);
    @ID(value=AUTHOR,transform=BaseToObject.class) 
    B author(ASObject obj);
    @ID(value=AUTHOR,transform=BaseToObject.class) 
    B author(Supplier<? extends Base> val);
    @ID(value=AUTHOR,transform=BaseToObject.class)
    B author(Link link);    
    @ID(value=GENERATOR,transform=BaseToObject.class) 
    B generator(String uri);
    @ID(value=GENERATOR,transform=BaseToObject.class) 
    B generator(ASObject obj);
    @ID(value=GENERATOR,transform=BaseToObject.class) 
    B generator(Supplier<? extends Base> val);
    @ID(value=GENERATOR,transform=BaseToObject.class) 
    B generator(Link link);
    @ID(value=ICON,transform=BaseToObject.class) 
    B icon(String uri);
    @ID(value=ICON,transform=BaseToObject.class) 
    B icon(ASObject obj);
    @ID(value=ICON,transform=BaseToObject.class) 
    B icon(Supplier<? extends Base> val);
    @ID(value=ICON,transform=BaseToObject.class) 
    B icon(Link link);
    @ID(value=IMAGE,transform=BaseToObject.class) 
    B image(String uri);
    @ID(value=IMAGE,transform=BaseToObject.class) 
    B image(ASObject obj);
    @ID(value=IMAGE,transform=BaseToObject.class) 
    B image(Supplier<? extends Base> val);
    @ID(value=IMAGE,transform=BaseToObject.class) 
    B image(Link link);
    @ID(value=INREPLYTO,transform=BaseToObject.class) 
    B inReplyTo(String uri);
    @ID(value=INREPLYTO,transform=BaseToObject.class) 
    B inReplyTo(ASObject obj);
    @ID(value=INREPLYTO,transform=BaseToObject.class) 
    B inReplyTo(Supplier<? extends Base> val);
    @ID(value=INREPLYTO,transform=BaseToObject.class) 
    B inReplyTo(Link link);
    @ID(value=LOCATION,transform=BaseToObject.class) 
    B location(String uri);
    @ID(value=LOCATION,transform=BaseToObject.class) 
    B location(ASObject obj);
    @ID(value=LOCATION,transform=BaseToObject.class) 
    B location(Supplier<? extends Base> val);
    @ID(value=LOCATION,transform=BaseToObject.class) 
    B location(Link link);
    @ID(value=PROVIDER,transform=BaseToObject.class) 
    B provider(String uri);
    @ID(value=PROVIDER,transform=BaseToObject.class) 
    B provider(ASObject obj);
    @ID(value=PROVIDER,transform=BaseToObject.class) 
    B provider(Supplier<? extends Base> val);
    @ID(value=PROVIDER,transform=BaseToObject.class) 
    B provider(Link link);
    @ID(value=SCOPE,transform=BaseToObject.class) 
    B scope(String uri);
    @ID(value=SCOPE,transform=BaseToObject.class) 
    B scope(ASObject obj);
    @ID(value=SCOPE,transform=BaseToObject.class) 
    B scope(Supplier<? extends Base> val);
    @ID(value=SCOPE,transform=BaseToObject.class) 
    B scope(Link link);
    @ID(value=TAG,transform=BaseToObject.class) 
    B tag(String uri);
    @ID(value=TAG,transform=BaseToObject.class) 
    B tag(ASObject obj);
    @ID(value=TAG,transform=BaseToObject.class) 
    B tag(Supplier<? extends Base> val);
    @ID(value=TAG,transform=BaseToObject.class) 
    B tag(Link link);
    @ID(value=URL,transform=BaseToObject.class) 
    B url(String uri);
    @ID(value=URL,transform=BaseToObject.class) 
    B url(Link link);
    @ID(value=URL,transform=BaseToObject.class) 
    B url(Supplier<Link> link);
    @ID(value=REPLIES,transform=BaseToObject.class) 
    B replies(Collection collection);
    @ID(value=REPLIES,transform=BaseToObject.class) 
    B replies(Supplier<Collection> collection);
    @ID(value=DISPLAYNAME, transform=LanguageTaggedValueObject.class) 
    B displayName(String displayName);
    @ID(value=DISPLAYNAME, transform=LanguageTaggedValueObject.class)
    B displayName(String displayName, String lang);
    @ID(value=CONTENT, transform=LanguageTaggedValueObject.class)
    B content(String displayName);
    @ID(value=CONTENT, transform=LanguageTaggedValueObject.class)
    B content(String displayName, String lang);
    @ID(value=SUMMARY, transform=LanguageTaggedValueObject.class)
    B summary(String displayName);
    @ID(value=SUMMARY, transform=LanguageTaggedValueObject.class) 
    B summary(String displayName, String lang);
    @ID(value=TITLE, transform=LanguageTaggedValueObject.class) 
    B title(String displayName);
    @ID(value=TITLE, transform=LanguageTaggedValueObject.class)
    B title(String displayName, String lang);    
    @ID(value=DURATION,transform=DurationToValueObject.class,functional=true) 
    B duration(ReadablePeriod period);
    @ID(value=DURATION,transform=DurationToValueObject.class,functional=true) 
    B duration(ReadableDuration duration);
    @ID(value=DURATION,transform=DurationToValueObject.class,functional=true) 
    B duration(int seconds);
    @ID(value=ALIAS,transform=PrimitiveToIDObject.class,functional=true) 
    B alias(String alias);
    @ID(value=HEIGHT,transform=PrimitiveToValueObject.class,functional=true)
    B height(int height);
    @ID(value=WIDTH,transform=PrimitiveToValueObject.class,functional=true)
    B width(int width);
    @ID(value=RATING,transform=PrimitiveToValueObject.class,functional=true) 
    B rating(float rating);
    @ID(value=ENDTIME,transform=DateTimeToValueObject.class,functional=true) 
    B endTime(ReadableInstant dt);
    @ID(value=ENDTIME,transform=DateTimeToValueObject.class,functional=true) 
    B endTimeNow();
    @ID(value=STARTTIME,transform=DateTimeToValueObject.class,functional=true) 
    B startTime(ReadableInstant dt);
    @ID(value=STARTTIME,transform=DateTimeToValueObject.class,functional=true) 
    B startTimeNow();
    @ID(value=PUBLISHED,transform=DateTimeToValueObject.class,functional=true) 
    B published(ReadableInstant dt);
    @ID(value=PUBLISHED,transform=DateTimeToValueObject.class,functional=true) 
    B publishedNow();
    @ID(value=UPDATED,transform=DateTimeToValueObject.class,functional=true) 
    B updated(ReadableInstant dt);
    @ID(value=UPDATED,transform=DateTimeToValueObject.class,functional=true) 
    B updatedNow();
    @ID(value=VALIDAFTER,transform=DateTimeToValueObject.class,functional=true) 
    B validAfter(ReadableInstant dt);
    @ID(value=VALIDAFTER,transform=DateTimeToValueObject.class,functional=true) 
    B validAfterNow();
    @ID(value=VALIDBEFORE,transform=DateTimeToValueObject.class,functional=true) 
    B validBefore(ReadableInstant dt);
    @ID(value=VALIDBEFORE,transform=DateTimeToValueObject.class,functional=true) 
    B validBeforeNow();
    @ID(value=VALIDFROM,transform=DateTimeToValueObject.class,functional=true) 
    B validFrom(ReadableInstant dt);
    @ID(value=VALIDFROM,transform=DateTimeToValueObject.class,functional=true) 
    B validFromNow();
    @ID(value=VALIDUNTIL,transform=DateTimeToValueObject.class,functional=true) 
    B validUntil(ReadableInstant dt);
    @ID(value=VALIDUNTIL,transform=DateTimeToValueObject.class,functional=true) 
    B validUntilNow();
  }
}
