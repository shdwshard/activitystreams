# Activity Streams 2.0 Implementation

## Getting Started

Maven:

Use Maven to build.

- mvn compile
- mvn install 
- mvn -f assembly assembly:assembly

```xml
<dependency>
  <groupId>com.ibm.common</groupId>
  <artifactId>activitystreams</artifactId>
  <version>0.0.2-SNAPSHOT</version>
</dependency>
```

Dependencies:

- jsonld-java 0.5.0 (https://github.com/jsonld-java/jsonld-java)
- guava 16.0.1
- joda-time 2.3

## Creating an Activity statement

```java
import com.ibm.common.activitystreams.IO;
import com.ibm.common.activitystreams.Activity;

public class Example {

  public static void main(String... args) throws Exception {
    
    IO io = IO.make()
      .prettyPrint()
      .get();
    
    Activity activity = 
      io.makers()
        .activity()
        .type("urn:example:verbs:Post")
        .actor("acct:sally@example.org")
        .object("http://example.org/posts/1")
        .get();
    
    activity.writeTo(System.out);
  }
  
}
```

The library uses a consistent fluent generator pattern to construct all 
object types. Once created, objects are immutable.

## Serializing and Parsing objects

The library has one job: to make it easy to create and parse Activity 
Stream objects that are conformant to the Activity Streams 2.0 
specification.

The IO object is used to serialize and parse Activity Stream objects. 
IO objects are threadsafe and immutable so they can be safely created 
once and stored as a static final constant. 

```java
package com.ibm.common.activitystreams;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.ibm.common.activitystreams.IO;
import com.ibm.common.activitystreams.Activity;

public class Example {

  public static void main(String... args) throws Exception {
    
    IO io = IO.make()
      .prettyPrint()
      .get();
    
    Activity activity = 
      io.makers()
        .activity()
        .type("urn:example:verbs:Post")
        .actor("acct:sally@example.org")
        .object("http://example.org/posts/1")
        .get();
    
    ByteArrayOutputStream out = 
      new ByteArrayOutputStream();
    // Write it out
    activity.writeTo(out, io);
    
    ByteArrayInputStream in = 
      new ByteArrayInputStream(out.toByteArray());
    
    // Read it in
    activity = (Activity)io.read(in);

    System.out.println(activity.type());
    
    Iterable<Base> actor = activity.actor();
    if (actor != null) {
      for (Base base : actor) {
        System.out.println(base.id());
      }
    }

    Iterable<Base> object = activity.object();
    if (object != null) {
      for (Base base : object) {
        System.out.println(base.id());
      }
    }

  }
  
}
```

All Activity Stream objects support a variety of writeTo methods. These
serialize those objects as JSON to either an OutputStream, Writer or a
String. You can choose to use the default IO instance or pass in an IO.
Alternatively, you can use the write methods on the IO object itself to
serialize. 

## Using Modules

A Module is a package collection of extensions to the Activity Streams 2.0
data model. The implementation currently provides a Geolocation
module.

Modules are registered when the IO object is created. For example, to 
use the Geo module:

```java
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.ibm.common.activitystreams.ASObject;
import com.ibm.common.activitystreams.Base;
import com.ibm.common.activitystreams.IO;
import com.ibm.common.activitystreams.geo.BasicGeoModule;
import com.ibm.common.activitystreams.geo.BasicGeoPoint;

public class Test {

  public static void main(String... args) throws Exception {
    
    FileInputStream in = new FileInputStream("/Users/james/Workspaces/projects/activitystreams/core/src/test/resources/data/1.json");
    InputStreamReader reader = new InputStreamReader(in, "UTF-8");
    
    IO io = IO.make()
      .prettyPrint()
      .register(BasicGeoModule.instance)
      .get();
    
    ASObject obj = (ASObject) io.read(reader);
    
    for (Base b : obj.location()) {
      BasicGeoPoint p = (BasicGeoPoint) b;
      System.out.println(p.longitude());
      System.out.println(p.latitude());
      System.out.println(p.altitude());
      System.out.println(p.displayName().get());
    }
  }
  
}
```

The Geo module is provided as a separate Maven artifact:

```xml
  <dependency>
    <groupId>com.ibm.common</groupId>
    <artifactId>activitystreams-geo</artifactId>
    <version>0.0.2-SNAPSHOT</version>
  </dependency>
```
