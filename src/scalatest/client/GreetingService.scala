package scalatest.client

import com.google.gwt.user.client.rpc.RemoteService
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath

@RemoteServiceRelativePath("greet")
trait GreetingService extends RemoteService {
  def greet(input: String): String
}