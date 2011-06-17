package com.google.gwt.dev.javac;

import com.google.gwt.dev.javac.CompilationUnitBuilder.ResourceCompilationUnitBuilder;
import com.google.gwt.dev.jjs.SourceInfoCorrelation;
import com.google.gwt.dev.jjs.SourceOrigin;
import com.google.gwt.dev.jjs.ast.JClassType;
import com.google.gwt.dev.jjs.ast.JDeclaredType;
import com.google.gwt.dev.jjs.ast.JMethod;
import com.google.gwt.dev.jjs.ast.JPrimitiveType;
import com.google.gwt.dev.resource.Resource;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MakeGwtar {

  public static void main(String[] args) throws Exception {
    CompilationUnitArchive gwtar = new CompilationUnitArchive("scalatest/ScalaTest.gwt.xml");

    CompilationUnitBuilder cbu = new ResourceCompilationUnitBuilder(new DummyResource(
        "scalatest/client/ScalaTest.java",
        new File("./src/scalatest/client/ScalaTest.java").getAbsolutePath()));

    List<CompiledClass> ccs = new ArrayList<CompiledClass>();
    ccs.add(new CompiledClass(readBytes("/scalatest/client/ScalaTest.class"), null, false, "scalatest/client/ScalaTest"));
    cbu.setCompiledClasses(ccs);

    List<JDeclaredType> types = new ArrayList<JDeclaredType>();
    JClassType ct = new JClassType(new SourceInfoCorrelation(SourceOrigin.UNKNOWN), "scalatest.client.ScalaTest", false, false);
    JMethod m = new JMethod(new SourceInfoCorrelation(SourceOrigin.UNKNOWN), "onModuleLoad", ct, JPrimitiveType.VOID, false ,false, false, false);
    ct.addMethod(m);
    types.add(ct);
    cbu.setTypes(types);

    Dependencies d = new Dependencies();
    cbu.setDependencies(d);

    cbu.setJsniMethods(new ArrayList<JsniMethod>());

    cbu.setMethodArgs(new MethodArgNamesLookup());

    // force ContentId creation
    cbu.getContentId();

    gwtar.addUnit(cbu.build());
    gwtar.writeToFile(new File("./src/scalatest/ScalaTest.gwtar"));
  }

  private static final class DummyResource extends Resource {
    private final String abstractPath;
    private final String path;

    private DummyResource(String abstractPath, String path) {
      this.abstractPath = abstractPath;
      this.path = path;
    }

    @Override
    public boolean wasRerooted() {
      return false;
    }

    @Override
    public InputStream openContents() {
      try {
        return new FileInputStream(path);
      } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
      }
    }

    @Override
    public String getPath() {
      return abstractPath;
    }

    @Override
    public String getLocation() {
      return "file:" + path;
    }

    @Override
    public long getLastModified() {
      return new File(path).lastModified();
    }
  }

  private static final byte[] readBytes(String className) throws IOException {
    InputStream in = MakeGwtar.class.getResourceAsStream(className);
    byte[] bytes = IOUtils.toByteArray(in);
    in.close();
    return bytes;
  }

}
