/**
 * Copyright 2013 OpenSocial Foundation
 * Copyright 2013 International Business Machines Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Utility library for working with Activity Streams Actions
 * Requires underscorejs.
 *
 * @author James M Snell (jasnell@us.ibm.com)
 */
package com.ibm.common.activitystreams;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Iterables.contains;
import static com.google.common.collect.Maps.newLinkedHashMap;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;
import com.google.common.base.Supplier;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.ibm.common.activitystreams.ext.Handler;
import com.ibm.common.activitystreams.ext.Module;
import com.ibm.common.activitystreams.ext.Preprocessor;
import com.ibm.common.activitystreams.impl.RootHandler;

public final class IO
  implements Constants {
    
  /**
   * Create a new IO.Builder
   * @return Builder 
   */
  public static Builder make() {
    return new Builder();
  }
      
  public static class Builder 
    implements Supplier<IO> {
    
    private final Set<Preprocessor> preprocessors = Sets.newLinkedHashSet();
    private final Set<Object> context = Sets.newLinkedHashSet();
    private final Map<String,Class<?>> typeMap = 
      newLinkedHashMap();
    private boolean prettyPrint = false;
    private final JsonLdOptions options = 
      new JsonLdOptions();
    
    Builder() {
      options.setCompactArrays(true);
      context.add("http://www.w3.org/ns/activitystreams");
    }
    
    public Builder addPreprocessor(Preprocessor preprocessor) {
      preprocessors.add(preprocessor);
      return this;
    }
    
    public Builder addContext(String uri) {
      context.add(uri);
      return this;
    }
    
    public Builder addContext(Map<String,Object> context) {
      this.context.add(context);
      return this; 
    }
    
    public Builder regiser(Module module) {
      module.config(this);
      return this;
    }
    
    public Builder register(Class<?> _class) {
      if (_class.isAnnotationPresent(Handler.class)) {
        Handler handler = _class.getAnnotation(Handler.class);
        if (!"".equals(handler.id()))
          return register(handler.id(), _class);
      }
      throw new IllegalArgumentException();
    }
    
    public Builder register(String id, Class<?> _class) {
      if (id.startsWith(NS) || 
          !_class.isInterface())
        throw new IllegalArgumentException();
      typeMap.put(id, _class);
      return this;
    }
    
    public Builder base(String uri) {
      options.setBase(uri);
      return this;
    }
    
    /**
     * Turn pretty print on or off
     * @param on boolean
     * @return Builder 
     **/
    public Builder prettyPrint(boolean on) {
      this.prettyPrint = on;
      return this;
    }
    
    /**
     * Turn pretty print on
     * @return Builder 
     **/
    public Builder prettyPrint() {
      return prettyPrint(true);
    }
    
    public IO get() {
      return new IO(this);
    }
  }
  
  private final boolean prettyPrint;
  private final JsonLdOptions options;
  private final ImmutableMap<String,Class<?>> typeMap;
  private final ImmutableMap<String,Object> context;
  private final ImmutableList<Preprocessor> preprocessors;
  
  protected IO(Builder builder) {
    this.context = ImmutableMap.<String,Object>builder().put(
      "@context", ImmutableList.copyOf(builder.context)).build();
    this.prettyPrint = builder.prettyPrint;
    this.options = builder.options;
    this.options.setExpandContext(context);
    this.typeMap = ImmutableMap.copyOf(builder.typeMap);
    this.preprocessors = ImmutableList.copyOf(builder.preprocessors);
  }
 
  /**
   * Write the given object
   * @param w Writable
   * @return String
   */
  public String write(Writable w) {
    StringWriter sw = new StringWriter();
    w.writeTo(sw,this);
    return sw.toString();
  }
  
  /**
   * Asynchronously write the given object
   * @param w
   * @param executor
   * @return java.util.concurrent.Future&lt;String>
   */
  public Future<String> write(
    final Writable w, 
    ExecutorService executor) {
    return executor.submit(
      new Callable<String>() {
        public String call() throws Exception {
          return write(w);
        }        
      }
    );
  }
  
  /**
   * Write the object to the given outputstream
   * @param w Writable
   * @param out OutputStream
   */
  public void write(Writable w, OutputStream out) {
    try {
      Map<String,Object> object = 
        JsonLdProcessor.compact(w.map(), context, options);
      object.put("@context", context.get("@context"));
      String ser = prettyPrint ?
        JsonUtils.toPrettyString(object) : 
        JsonUtils.toString(object);
      out.write(ser.getBytes("UTF-8"));
    } catch (Throwable t) {
      throw Throwables.propagate(t);
    }
  }
  
  /**
   * Asychronously write the object to the given output stream
   * @param w
   * @param out
   * @param executor
   * @return java.util.concurrent.Future&lt;?>
   */
  public Future<?> write(
    final Writable w, 
    final OutputStream out,
    ExecutorService executor) {
    return executor.submit(
      new Runnable() {
        public void run() {
          write(w, out);
        }        
      }
    );
  }
  
  /**
   * Asychronously write the object to the given writer
   * @param w
   * @param out
   * @param executor
   * @return java.util.concurrent.Future&lt;?>
   */
  public Future<?> write(
    final Writable w, 
    final Writer out,
    ExecutorService executor) {
    return executor.submit(
      new Runnable() {
        public void run() {
          write(w, out);
        }        
      }
    );
  }
  
  /**
   * Write the object to the given writer
   * @param w Writable
   * @param out Writer
   */
  public void write(Writable w, Writer out) {
    try {
      Map<String,Object> object = 
        JsonLdProcessor.compact(w.map(), context, options);
      object.put("@context", context.get("@context"));
      String ser = prettyPrint ?
        JsonUtils.toPrettyString(object) : 
        JsonUtils.toString(object);
      out.write(ser);
    } catch (Throwable t) {
      throw Throwables.propagate(t);
    }
  }
  
  /**
   * Asynchronously read the given input stream and 
   * return a parsed object of the given type
   * @param in
   * @param type
   * @param executor
   * @return java.util.concurrent.Future&lt;A extends ASObject>
   */
  public Future<Base> read(
    final InputStream in,
    ExecutorService executor) {
      return executor.submit(
        new Callable<Base>() {
          public Base call() throws Exception {
            return read(in);
          }          
        }
      );
  }
  
  /**
   * Read the given input stream and return a parsed object
   * of the given type
   * @param in InputStream
   * @param type Class<? extends A>
   * @return A */
  @SuppressWarnings("unchecked")
  public Base read(
    InputStream in) {
    try {
      Map<String,Object> map = 
        (Map<String, Object>) JsonUtils.fromInputStream(in);
      return doRead(map);
    } catch (Throwable e) {
      throw Throwables.propagate(e);
    }
  }
  
  /**
   * Asynchronously read the given reader and return a parsed
   * object of the given type
   * @param in
   * @param type
   * @param executor
   * @return java.util.concurrent.Future&lt;A extends ASObject>
   */
  public Future<Base> read(
    final Reader in,
    ExecutorService executor) {
      return executor.submit(
        new Callable<Base>() {
          public Base call() throws Exception {
            return read(in);
          }          
        }
      );
  }
  
  /**
   * Read the given reader and return a parsed object of the given type
   * @param in Reader
   * @param type Class<? extends A>
   * @return A */
  @SuppressWarnings("unchecked")
  public Base read(
    Reader in) {
      try {
        Map<String,Object> map = 
          (Map<String, Object>) JsonUtils.fromReader(in);
        return doRead(map);
      } catch (Throwable e) {
        throw Throwables.propagate(e);
      }
  }
  
  @SuppressWarnings("unchecked")
  public Base doRead(Map<String,Object> map) {
    try {
      for (Preprocessor p : preprocessors) 
        map = p.apply(map);
      List<Object> obj = JsonLdProcessor.expand(map, options);
      checkArgument(obj.size() == 1);
      return wrap((Map<String,Object>)obj.get(0));
    } catch (Throwable e) {
      throw Throwables.propagate(e);
    }
  }
  
  public Base wrap(Map<String,Object> map) {
    return wrap(map,false);
  }
  
  public Base wrap(Map<String,Object> map, boolean isALink) {
    RootHandler handler = new RootHandler(this,map);
    ClassLoader cl = Base.class.getClassLoader();
    return (Base)Proxy.newProxyInstance(
      cl, determine_interfaces(map, isALink), handler);
  }
  
  @SuppressWarnings("unchecked")
  private Class<?>[] determine_interfaces(Map<String,Object> map, boolean isALink) {
    Iterable<String> types = (Iterable<String>) map.get("@type");
    if (types == null)
      types = ImmutableSet.of();
    ImmutableSet.Builder<Class<?>> set = ImmutableSet.builder();
    set.add(Base.class);
    if (isALink || contains(types, TYPE_LINK)) {
      set.add(Link.class);
      isALink = true;
    } else { 
      set.add(ASObject.class);
      if (contains(types, TYPE_COLLECTION) || 
          map.containsKey(ITEMS))
        set.add(Collection.class);
      if (contains(types, TYPE_ACTIVITY) || 
          map.containsKey(ACTOR) || map.containsKey(OBJECT))
        set.add(Activity.class);
    }
    for (String type : types) {
      Class<?> _class = typeMap.get(type);
      if (_class != null) {
        if ((isALink && !ASObject.class.isAssignableFrom(_class)) || 
            (!isALink && !Link.class.isAssignableFrom(_class)))
          set.add(_class);
      }
    }
    
    ImmutableSet<Class<?>> _set = set.build();
    return _set.toArray(new Class[_set.size()]);
  }
  
  public Makers makers() {
    return new Makers(this);
  }
}
