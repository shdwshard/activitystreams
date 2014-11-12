package com.ibm.common.activitystreams;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Maps.newLinkedHashMap;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableMap;

public final class NLV
  implements Iterable<String> {

  public static final class Builder 
    implements Supplier<NLV> {

    protected final Map<String,String> map = 
      newLinkedHashMap();
    
    @Override
    public NLV get() {
      return new NLV(this);
    }
    
    public Builder set(String lang, String val) {
      checkNotNull(lang);
      checkNotNull(val);
      lang = lang.toLowerCase();
      map.put(lang,val);
      return this;
    }
    
    public Builder set(String val) {
      return set("*", val);
    }
  }
  
  private final ImmutableMap<String,String> map;
  
  private NLV(Builder builder) {
    this.map = ImmutableMap.copyOf(builder.map);
  }
  
  public String get(String langtag) {
    if (langtag == null) langtag = "*";
    return map.get(langtag.toLowerCase());
  }
  
  public String get(Locale locale) {
    if (locale == null) return get("*");
    return get(locale.toLanguageTag());
  }
  
  public String get() {
    return get("*");
  }
  
  public String getDefault() {
    return get(Locale.getDefault());
  }
  
  public boolean has(String langtag) {
    if (langtag == null) langtag = "*";
    return map.containsKey(langtag.toLowerCase());
  }
  
  public boolean has(Locale locale) {
    if (locale == null) return has("*");
    return map.containsKey(locale.toLanguageTag());
  }

  @Override
  public Iterator<String> iterator() {
    return map.keySet().iterator();
  }
  
}
