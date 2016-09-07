package xyz.juanes.jviz.registry.helpers;

import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import java.io.IOException;

//Import config
import xyz.juanes.jviz.registry.Config;

public class HttpError
{
  //Send a new error
  public static void send(HttpServletResponse response, Integer code, String message) throws IOException
  {
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
  }
}
