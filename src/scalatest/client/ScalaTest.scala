package scalatest.client

import com.google.gwt.core.client.EntryPoint
import com.google.gwt.core.client.GWT
import com.google.gwt.user.client.Window
import com.google.gwt.user.client.rpc.AsyncCallback

class ScalaTest extends EntryPoint {
  val greetingService: GreetingServiceAsync = GWT.create(classOf[GreetingService])

  def onModuleLoad(): Unit = {
    Window.alert("hello there!!")
    greetingService.greet("hello", new AsyncCallback[String]() {
      override def onSuccess(result: String) {
        Window.alert(result)
      }

       override def onFailure(caught: Throwable) {
       }
     })
  }
}