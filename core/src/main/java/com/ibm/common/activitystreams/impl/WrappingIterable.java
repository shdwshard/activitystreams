package com.ibm.common.activitystreams.impl;

import java.util.Iterator;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

import com.ibm.common.activitystreams.IO;

final class WrappingIterable
  implements Iterable<Object> {

  private final Iterable<Object> inner;
  private final IO io;
  private final boolean isALink;
  
  WrappingIterable(Iterable<Object> inner, IO io, boolean isALink) {
    this.inner = inner;
    this.io = io;
    this.isALink = isALink;
  }
  
  @Override
  public Iterator<Object> iterator() {
    return new WrappingIterator(inner.iterator(), io);
  }

  final class WrappingIterator implements Iterator<Object> {

    private final Iterator<Object> inner;
    private final IO io;
    
    WrappingIterator(Iterator<Object> inner, IO io) {
      this.inner = inner;
      this.io = io;
    }
    
    @Override
    public boolean hasNext() {
      return inner.hasNext();
    }

    @Override
    public void remove() {
      inner.remove();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object next() {
      Object obj = inner.next();
      checkArgument(obj instanceof Map);
      return io.wrap((Map<String, Object>) obj, isALink);
    }

  }
  
  public String toString() {
    return inner.toString();
  }
}
