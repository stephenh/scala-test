
Roughly:

1. Clone:

   https://github.com/stephenh/google-web-toolkit

   Checkout the scala-test branch.

   Run `ant dist-dev`

2. Import `gwt-dev` and `gwt-user` into Eclipse

   Change the `/home/stephen/.../scala-library.jar` build path location to a `scala-library.jar` from 2.9.0-1 download on your machine.

3. In Window / Preferences / Google / Web Toolkit add a GWT SDK with the name of "GWT trunk" that points to the checkout from step 1.

4. Import this project (`scala-test`) into Eclipse

5. Change the paths in `build.sh` for your machine.

6. Invoke `./build.sh` to create jribble files

Now you should be able to:

* Run ScalaTest.html.launch to start DevMode
* Run ScalaTestCompile.launch to compile to WebMode
* Run JavaTestCompile.launch to compile Java code that uses GWT-RPC interfaces defined in Scala

If you want to try the output from WebMode, to start a server:

* Download [jtty.jar](https://github.com/downloads/stephenh/jtty/jtty.jar)
* Copy `gwt-servlet.jar` and `scala-library.jar` to `WEB-INF/lib`
* Run `java -jar jtty.jar 8080 war`
* Open `http://127.0.0.1:8080/ScalaTest.html` in Firefox

