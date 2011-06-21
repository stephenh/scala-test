package scalatest.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class JavaTest implements EntryPoint {

  private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

  @Override
  public void onModuleLoad() {
    greetingService.greet("hello", new AsyncCallback<String>() {
      @Override
      public void onSuccess(String result) {
        Window.alert(result);
      }
      @Override
      public void onFailure(Throwable caught) {
      }
    });
  }

}
