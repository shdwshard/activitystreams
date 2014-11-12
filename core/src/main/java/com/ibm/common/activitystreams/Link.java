package com.ibm.common.activitystreams;

import com.ibm.common.activitystreams.ext.ID;
import com.ibm.common.activitystreams.builder.DurationToValueObject;
import com.ibm.common.activitystreams.builder.LanguageTaggedValueObject;
import com.ibm.common.activitystreams.builder.MediaTypeToValueObject;
import com.ibm.common.activitystreams.builder.PrimitiveToIDObject;
import com.ibm.common.activitystreams.builder.PrimitiveToValueObject;

import org.joda.time.Duration;
import org.joda.time.ReadableDuration;
import org.joda.time.ReadablePeriod;

import com.google.common.net.MediaType;

public interface Link
  extends Base {

  NLV displayName();
  NLV title();
  Duration duration();
  int height();
  int width();
  String href();
  String hreflang();
  String rel();
  MediaType mediaType();
  
  public static interface Builder extends Base.Builder<Link, Link.Builder> {
    @ID(value=DURATION,transform=DurationToValueObject.class,functional=true) 
    Builder duration(ReadablePeriod period);
    @ID(value=DURATION,transform=DurationToValueObject.class,functional=true) 
    Builder duration(ReadableDuration duration);
    @ID(value=DURATION,transform=DurationToValueObject.class,functional=true) 
    Builder duration(int seconds);
    @ID(value=DURATION,transform=DurationToValueObject.class,functional=true) 
    Builder duration(long seconds);
    @ID(value=HREF,transform=PrimitiveToIDObject.class,functional=true) 
    Builder href(String url);
    @ID(value=MEDIATYPE,transform=MediaTypeToValueObject.class,functional=true) 
    Builder mediaType(String mediaType);
    @ID(value=MEDIATYPE,transform=MediaTypeToValueObject.class,functional=true) 
    Builder mediaType(MediaType mediaType);
    @ID(value=REL,transform=PrimitiveToValueObject.class,functional=true) 
    Builder rel(String rel);
    @ID(value=HREFLANG,transform=PrimitiveToValueObject.class,functional=true) 
    Builder hreflang(String lang);
    @ID(value=WIDTH,transform=PrimitiveToValueObject.class,functional=true) 
    Builder width(int width);
    @ID(value=HEIGHT,transform=PrimitiveToValueObject.class,functional=true) 
    Builder height(int height);
    @ID(value=TITLE, transform=LanguageTaggedValueObject.class) 
    Builder title(String title);
    @ID(value=TITLE, transform=LanguageTaggedValueObject.class) 
    Builder title(String title, String lang);
    @ID(value=DISPLAYNAME, transform=LanguageTaggedValueObject.class) 
    Builder displayName(String displayName);
    @ID(value=DISPLAYNAME, transform=LanguageTaggedValueObject.class) 
    Builder displayName(String displayName, String lang);
  }
}
