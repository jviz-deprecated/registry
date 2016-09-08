package xyz.juanes.jviz.registry.routes;

import xyz.juanes.jviz.registry.Config;
import xyz.juanes.jviz.registry.helpers.HttpOut;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Home extends HttpServlet
{
  //Get
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
  {
    //Return the api
    HttpOut.send(response, 200, Config.api());

    //Exit
    return;
  }
}
