package scalatest.client

import com.google.gwt.user.client.rpc.AsyncCallback

trait GreetingServiceAsync {
  def greet(input: String, callback: AsyncCallback[String]): Unit
}