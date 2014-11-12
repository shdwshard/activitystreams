package com.ibm.common.activitystreams;

public interface Constants {

  public static final String NS = "http://www.w3.org/ns/activitystreams#";
  
  public static final String TYPE_LINK = NS + "Link";
  public static final String TYPE_ACTIVITY = NS + "Activity";
  public static final String TYPE_OBJECT = NS + "Object";
  public static final String TYPE_COLLECTION = NS + "Collection";
  
  public static final String ID = "@id";
  public static final String TYPE = "@type";
  public static final String CONTEXT = "@context";
  
  public static final String ACTION = NS + "action";
  public static final String ALIAS = NS + "alias";
  public static final String ATTACHMENT = NS + "attachment";
  public static final String AUTHOR = NS + "author";
  public static final String CONTENT = NS + "content";
  public static final String DISPLAYNAME = NS + "displayName";
  public static final String DURATION = NS + "duration";
  public static final String ENDTIME = NS + "endTime";
  public static final String GENERATOR = NS + "generator";
  public static final String HEIGHT = NS + "height";
  public static final String ICON = NS + "icon";
  public static final String IMAGE = NS + "image";
  public static final String INREPLYTO = NS + "inReplyTo";
  public static final String LOCATION = NS + "location";
  public static final String PROVIDER = NS + "provider";
  public static final String PUBLISHED = NS + "published";
  public static final String RATING = NS + "rating";
  public static final String REPLIES = NS + "replies";
  public static final String SCOPE = NS + "scope";
  public static final String STARTTIME = NS + "startTime";
  public static final String SUMMARY = NS + "summary";
  public static final String TAG = NS + "tag";
  public static final String TITLE = NS + "title";
  public static final String UPDATED = NS + "updated";
  public static final String VALIDAFTER = NS + "validAfter";
  public static final String VALIDBEFORE = NS + "validBefore";
  public static final String VALIDFROM = NS + "validFrom";
  public static final String VALIDUNTIL = NS + "validUntil";
  public static final String WIDTH = NS + "width";
  public static final String URL = NS + "url";
  
  public static final String HREF = NS + "href";
  public static final String HREFLANG = NS + "hreflang";
  public static final String REL = NS + "rel";
  public static final String MEDIATYPE = NS + "mediaType";
  
  public static final String ACTOR = NS + "actor";
  public static final String OBJECT = NS + "object";
  public static final String TARGET = NS + "target";
  public static final String RESULT = NS + "result";
  public static final String INSTRUMENT = NS + "instrument";
  public static final String PARTICIPANT = NS + "participant";
  public static final String PRIORITY = NS + "priority";
  public static final String STATUS = NS + "status";
  public static final String TO = NS + "to";
  public static final String BTO = NS + "bto";
  public static final String CC = NS + "cc";
  public static final String BCC = NS + "bcc";
  
  public static final String ITEMS = NS + "items";
  public static final String TOTALITEMS = NS + "totalItems";
  public static final String ITEMSPERPAGE = NS + "itemsPerPage";
  public static final String STARTINDEX = NS + "startIndex";
  public static final String ITEMSAFTER = NS + "itemsAfter";
  public static final String ITEMSBEFORE = NS + "itemsBefore";
  public static final String CURRENT = NS + "current";
  public static final String NEXT = NS + "next";
  public static final String PREV = NS + "prev";
  public static final String FIRST = NS + "first";
  public static final String LAST = NS + "last";
  public static final String SELF = NS + "self";
  
  public static final String STATUS_ACTIVE = NS + "status-active";
  public static final String STATUS_CANCELED = NS + "status-canceled";
  public static final String STATUS_COMPLETED = NS + "status-completed";
  public static final String STATUS_PENDING = NS + "status-pending";
  public static final String STATUS_TENTATIVE = NS + "status-tenative";
  public static final String STATUS_VOIDED = NS + "status-voided";
  
  public static final String XSD = "http://www.w3.org/2001/XMLSchema#";
  public static final String XSD_STRING = XSD + "string";
  public static final String XSD_INTEGER = XSD + "integer";
  public static final String XSD_INT = XSD + "int";
  public static final String XSD_DATETIME = XSD + "dateTime";
  public static final String XSD_FLOAT = XSD + "float";
  public static final String XSD_DOUBLE = XSD + "double";
  public static final String XSD_DURATION = XSD + "duration";
  public static final String XSD_ANYURI = XSD + "anyURI";
  public static final String XSD_NONNEGATIVEINTEGER = XSD + "nonNegativeInteger";
}
