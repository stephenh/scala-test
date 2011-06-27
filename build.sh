#!/bin/bash

SCALA_HOME=/home/stephen/other/scalagwt-scala/build/pack
GWT_HOME=/home/stephen/other/google-web-toolkit
GWT_TOOLS=/home/stephen/other/gwt-tools
PATH=$SCALA_HOME/bin:$PATH
CP=$GWT_HOME/eclipse/dev/bin:$GWT_HOME/eclipse/user/bin:$GWT_TOOLS/lib/tomcat/servlet-api-2.5.jar

scalac -sourcepath src/ \
    -classpath $CP \
    -d war/WEB-INF/classes \
    -target:jribble \
    src/scalatest/client/GreetingServiceAsync.scala \
    src/scalatest/client/GreetingService.scala \
    src/scalatest/client/ScalaTest.scala

scalac -sourcepath src/ \
    -classpath $CP \
    -d war/WEB-INF/classes \
    src/scalatest/client/GreetingServiceAsync.scala \
    src/scalatest/client/GreetingService.scala \
    src/scalatest/client/ScalaTest.scala \
    src/scalatest/server/GreetingServiceImpl.scala

