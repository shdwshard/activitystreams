package com.ibm.common.activitystreams;

import org.joda.time.DateTime;

import com.ibm.common.activitystreams.ext.ID;
import com.ibm.common.activitystreams.builder.BaseToObject;
import com.ibm.common.activitystreams.builder.DateTimeToValueObject;
import com.ibm.common.activitystreams.builder.PrimitiveToValueObject;
import com.google.common.base.Supplier;

public interface Collection 
  extends ASObject {

  Link current();
  Link first();
  Link last();
  Link next();
  Link prev();
  Link self();
  <X extends Base>Iterable<X> items();
  int itemsPerPage();
  int totalItems();
  int startIndex();
  DateTime itemsAfter();
  DateTime itemsBefore();
  
  public static interface Builder extends ASObject.AbstractBuilder
    <Collection, Collection.Builder> {
    @ID(value=CURRENT,transform=BaseToObject.class,functional=true) 
    Builder current(String uri);
    @ID(value=CURRENT,transform=BaseToObject.class,functional=true) 
    Builder current(Link link);
    @ID(value=CURRENT,transform=BaseToObject.class,functional=true) 
    Builder current(Supplier<Link> link);
    @ID(value=FIRST,transform=BaseToObject.class,functional=true) 
    Builder first(String uri);
    @ID(value=FIRST,transform=BaseToObject.class,functional=true) 
    Builder first(Link link);
    @ID(value=FIRST,transform=BaseToObject.class,functional=true) 
    Builder first(Supplier<Link> link);
    @ID(value=LAST,transform=BaseToObject.class,functional=true) 
    Builder last(String uri);
    @ID(value=LAST,transform=BaseToObject.class,functional=true) 
    Builder last(Link link);
    @ID(value=LAST,transform=BaseToObject.class,functional=true) 
    Builder last(Supplier<Link> link);
    @ID(value=NEXT,transform=BaseToObject.class,functional=true) 
    Builder next(String uri);
    @ID(value=NEXT,transform=BaseToObject.class,functional=true) 
    Builder next(Link link);
    @ID(value=NEXT,transform=BaseToObject.class,functional=true) 
    Builder next(Supplier<Link> link);
    @ID(value=PREV,transform=BaseToObject.class,functional=true) 
    Builder prev(String uri);
    @ID(value=PREV,transform=BaseToObject.class,functional=true) 
    Builder prev(Link link);
    @ID(value=PREV,transform=BaseToObject.class,functional=true) 
    Builder prev(Supplier<Link> link);
    @ID(value=SELF,transform=BaseToObject.class,functional=true) 
    Builder self(String uri);
    @ID(value=SELF,transform=BaseToObject.class,functional=true) 
    Builder self(Link link);
    @ID(value=SELF,transform=BaseToObject.class,functional=true) 
    Builder self(Supplier<Link> link);
    @ID(value=ITEMS,transform=BaseToObject.class) 
    Builder item(String uri);
    @ID(value=ITEMS,transform=BaseToObject.class) 
    Builder item(ASObject obj);
    @ID(value=ITEMS,transform=BaseToObject.class) 
    Builder item(Link link);
    @ID(value=ITEMS,transform=BaseToObject.class) 
    Builder item(Supplier<? extends Base> obj);
    @ID(value=ITEMSPERPAGE,transform=PrimitiveToValueObject.class,functional=true) 
    Builder itemsPerPage(int i);
    @ID(value=TOTALITEMS,transform=PrimitiveToValueObject.class,functional=true) 
    Builder totalItems(int i);
    @ID(value=STARTINDEX,transform=PrimitiveToValueObject.class,functional=true) 
    Builder startIndex(int i);
    @ID(value=ITEMSAFTER,transform=DateTimeToValueObject.class,functional=true) 
    Builder itemsAfter(DateTime dt);
    @ID(value=ITEMSAFTER,transform=DateTimeToValueObject.class,functional=true) 
    Builder itemsAfterNow();
    @ID(value=ITEMSBEFORE,transform=DateTimeToValueObject.class,functional=true) 
    Builder itemsBefore(DateTime dt);
    @ID(value=ITEMSBEFORE,transform=DateTimeToValueObject.class,functional=true) 
    Builder itemsBeforeNow();
  }
}
