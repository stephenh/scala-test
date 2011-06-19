package scalatest.client

import com.google.gwt.core.client.EntryPoint
import com.google.gwt.core.client.GWT
import com.google.gwt.user.client.Window
import com.google.gwt.user.client.ui.Button
import com.google.gwt.user.client.ui.RootPanel
import com.google.gwt.event.dom.client.ClickHandler
import com.google.gwt.event.dom.client.ClickEvent
import com.google.gwt.user.client.rpc.AsyncCallback

class ScalaTest extends EntryPoint {
  val greetingService: GreetingServiceAsync = GWT.create(classOf[GreetingService])

  def onModuleLoad(): Unit = {
    // Window.alert("hello there!!")
    val b = new Button()
    b setText "click me"
    b.addClickHandler(new ClickHandler() {
      override def onClick(event: ClickEvent) {
        Window.alert("clicked")
        greetingService.greet("hello", new AsyncCallback[String]() {
          override def onSuccess(result: String) {
            Window.alert(result)
          }

          override def onFailure(caught: Throwable) {
          }
        })
      }
    })
    RootPanel.get.add(b)
  }
}