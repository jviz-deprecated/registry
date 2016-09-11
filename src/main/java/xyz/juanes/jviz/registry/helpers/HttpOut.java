package xyz.juanes.jviz.registry.helpers;

import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import java.io.IOException;

//Import config
import xyz.juanes.jviz.registry.Config;

public class HttpOut
{
  //Send response
  public static void send(HttpServletResponse response, Integer code, JSONObject obj) throws IOException
  {
    //Send the response
    send(response, code, obj.toJSONString());

    //Exit
    return;
  }

  //Send response
  public static void send(HttpServletResponse response, Integer code, String str) throws IOException
  {
    //Set the content type
    response.setContentType("application/json");

    //Set the status code
    response.setStatus(code);

    //Print the response
    response.getWriter().println(str);

    //Exit
    return;
  }

  //Send a new error
  public static void error(HttpServletResponse response, Integer code, String message) throws IOException
  {
    //Set the content type
    response.setContentType("application/json");

    //Generate the new json object
    JSONObject obj = new JSONObject();

    //Add the message
    obj.put("message", message);

    //Add the documentation lik
    obj.put("documentation", Config.documentation);

    //Set the response code
    response.setStatus(code);

    //Print the response
    response.getWriter().println(obj.toJSONString());

    //Exit
    return;
  }
}
