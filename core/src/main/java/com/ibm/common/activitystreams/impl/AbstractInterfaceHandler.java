package com.ibm.common.activitystreams.impl;

import static com.google.common.base.Preconditions.checkArgument;

import java.lang.reflect.Method;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.Period;

import com.google.common.base.Throwables;
import com.google.common.collect.Iterables;
import com.google.common.net.MediaType;
import com.ibm.common.activitystreams.Constants;
import com.ibm.common.activitystreams.IO;
import com.ibm.common.activitystreams.NLV;
import com.ibm.common.activitystreams.ext.InterfaceHandler;

@SuppressWarnings("unchecked")
public abstract class AbstractInterfaceHandler 
  implements InterfaceHandler, Constants {

  public static double getDouble(Map<String, Object> map, String id) {
    try {
      String val = getString(map,id);
      return Double.parseDouble(val);
    } catch (Throwable t) {
      throw Throwables.propagate(t);
    }
  }
  
  public static float getFloat(Map<String, Object> map, String id) {
    try {
      String val = getString(map,id);
      return Float.parseFloat(val);
    } catch (Throwable t) {
      throw Throwables.propagate(t);
    }
  }
  
  public static int getInt(Map<String, Object> map, String id) {
    try {
      String val = getString(map,id);
      return Integer.parseInt(val);
    } catch (Throwable t) {
      throw Throwables.propagate(t);
    }
  }
  
  public static String getString(Map<String, Object> map, String id) {
    return (String)getValue(map,id);
  }

  public static Object getValue(Map<String, Object> map, String id) {
    Object val = map.get(id);
    if (val == null) 
      return null;
    checkArgument(val instanceof Iterable);
    Iterable<Object> ival = (Iterable<Object>) val;
    Object first = Iterables.getFirst(ival, null);
    checkArgument(first instanceof Map);
    return ((Map<String,Object>)first).get("@value");
  }
  
  public static String getId(Map<String, Object> map, String id) {
    Object val = map.get(id);
    if (val == null) 
      return null;
    checkArgument(val instanceof Iterable);
    Iterable<Object> ival = (Iterable<Object>) val;
    Object first = Iterables.getFirst(ival, null);
    checkArgument(first instanceof Map);
    return (String) ((Map<String,Object>)first).get("@id");
  }
  
  public static InterfaceHandler idPrimitiveHandler(final String id) {
    return new InterfaceHandler() {
      @Override
      public Object invoke(
        IO io, 
        Map<String, Object> map, 
        Method method,
        Object[] args) {
          return getId(map,id);
      }
    };
  }
  
  public static InterfaceHandler stringPrimitiveHandler(final String id) {
    return new InterfaceHandler() {
      @Override
      public Object invoke(
        IO io, 
        Map<String, Object> map, 
        Method method,
        Object[] args) {
          return getString(map,id);
      }
    };
  }
  
  public static InterfaceHandler intPrimitiveHandler(final String id) {
    return new InterfaceHandler() {
      @Override
      public Object invoke(
        IO io, 
        Map<String, Object> map, 
        Method method,
        Object[] args) {
          return getInt(map,id);
      }
    };
  }
  
  public static InterfaceHandler floatPrimitiveHandler(final String id) {
    return new InterfaceHandler() {
      @Override
      public Object invoke(
        IO io, 
        Map<String, Object> map, 
        Method method,
        Object[] args) {
          return getFloat(map,id);
      }
    };
  }
  
  public static InterfaceHandler doublePrimitiveHandler(final String id) {
    return new InterfaceHandler() {
      @Override
      public Object invoke(
        IO io, 
        Map<String, Object> map, 
        Method method,
        Object[] args) {
          return getDouble(map,id);
      }
    };
  }
  
  public static InterfaceHandler dateTimePrimitiveHandler(final String id) {
    return new InterfaceHandler() {
      @Override
      public Object invoke(
        IO io, 
        Map<String, Object> map, 
        Method method,
        Object[] args) {
          String val = getString(map,id);
          return new DateTime(val);
      }
    };
  }
  
  public static InterfaceHandler nlvHandler(final String id) {
    return new InterfaceHandler() {
      @Override
      public Object invoke(
        IO io, 
        Map<String, Object> map, 
        Method method,
        Object[] args) {
          Object val = map.get(id);
          if (val == null) 
            return null;
          checkArgument(val instanceof Iterable);
          Iterable<Object> ival = (Iterable<Object>) val;
          NLV.Builder builder = new NLV.Builder();
          for (Object obj : ival) {
            Map<String,Object> i = (Map<String, Object>) obj;
            String v = (String) i.get("@value");
            String l = (String) i.get("@language");
            if (l != null)
              builder.set(l, v);
            else 
              builder.set(v);
          }
          return builder.get();
      }
    };
  }
  
  public static InterfaceHandler wrapHandler(final String id) {
    return wrapHandler(id, false);
  }
  
  public static InterfaceHandler wrapHandler(
    final String id, 
    final boolean isALink) {
    return new InterfaceHandler() {
      @Override
      public Object invoke(
        IO io, 
        Map<String, Object> map, 
        Method method,
        Object[] args) {
          Object val = map.get(id);
          if (val == null)
            return null;
          checkArgument(val instanceof Iterable);
          return new WrappingIterable((Iterable<Object>) val,io, isALink);
      }
    };
  }
  
  public static InterfaceHandler collectionHandler(final String id) {
    return new InterfaceHandler() {
      @Override
      public Object invoke(
        IO io, 
        Map<String, Object> map, 
        Method method,
        Object[] args) {
          Object val = map.get(id);
          if (val == null)
            return null;
          checkArgument(val instanceof Iterable);
          Object first = Iterables.getFirst((Iterable<Object>) val, null);
          checkArgument(first instanceof Map);
          return io.wrap((Map<String, Object>) first);
      }
    };
  }
  
  public static InterfaceHandler linkHandler(final String id) {
    return new InterfaceHandler() {
      @Override
      public Object invoke(
        IO io, 
        Map<String, Object> map, 
        Method method,
        Object[] args) {
          Object val = map.get(id);
          if (val == null)
            return null;
          checkArgument(val instanceof Iterable);
          Object first = Iterables.getFirst((Iterable<Object>) val, null);
          checkArgument(first instanceof Map);
          return io.wrap((Map<String, Object>) first, true);
      }
    };
  }
  
  public static InterfaceHandler durationHandler(final String id) {
    return new InterfaceHandler() {
      @Override
      public Object invoke(
        IO io, 
        Map<String, Object> map, 
        Method method,
        Object[] args) {
        Object val = map.get(id);
        if (val == null) 
          return null;
        checkArgument(val instanceof Iterable);
        Iterable<Object> ival = (Iterable<Object>) val;
        Object first = Iterables.getFirst(ival, null);
        checkArgument(first instanceof Map);
        Map<String,Object> mval = (Map<String, Object>) first;
        Object v = mval.get("@value");
        if (v instanceof Integer)
          return Period.seconds((Integer)v);
        else 
          return Period.parse(v.toString());
      }
    };
  }
  
  public static InterfaceHandler mediaTypeHandler(final String id) {
    return new InterfaceHandler() {
      @Override
      public Object invoke(
        IO io, 
        Map<String, Object> map, 
        Method method,
        Object[] args) {
          String val = getString(map, id);
          return MediaType.parse(val);
      }
    };
  }
  
  public static InterfaceHandler enumHandler(final String id, final Class<? extends Enum<?>> _enum) {
    return new InterfaceHandler() {
      @Override
      public Object invoke(
        IO io, 
        Map<String, Object> map, 
        Method method,
        Object[] args) {
          return null;
      }
    };
  }
}
