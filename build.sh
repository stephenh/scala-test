#!/bin/bash

GWT_HOME=/home/stephen/other/google-web-toolkit
GWT_TOOLS=/home/stephen/other/gwt-tools

# use jribble-enabled scalac
SCALA_HOME=/home/stephen/other/scalagwt-scala/build/pack
PATH=$SCALA_HOME/bin:$PATH

# assume user has built the GWT checkout
CP=$GWT_HOME/build/lib/'*':$GWT_TOOLS/lib/tomcat/servlet-api-2.5.jar

# first pass to make jribble files
scalac -sourcepath src/ \
    -classpath $CP \
    -d war/WEB-INF/classes \
    -target:jribble \
    src/scalatest/client/GreetingServiceAsync.scala \
    src/scalatest/client/GreetingService.scala \
    src/scalatest/client/ScalaTest.scala

# second pass to make class files
scalac -sourcepath src/ \
    -classpath $CP \
    -d war/WEB-INF/classes \
    src/scalatest/client/GreetingServiceAsync.scala \
    src/scalatest/client/GreetingService.scala \
    src/scalatest/client/ScalaTest.scala \
    src/scalatest/server/GreetingServiceImpl.scala

