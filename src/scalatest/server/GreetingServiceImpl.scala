package scalatest.server

import scalatest.client.GreetingService
import com.google.gwt.user.server.rpc.RemoteServiceServlet

class GreetingServiceImpl extends RemoteServiceServlet with GreetingService {

  def greet(input: String): String = {
    println("CALLED")
    return input + " from the server"
  }

}