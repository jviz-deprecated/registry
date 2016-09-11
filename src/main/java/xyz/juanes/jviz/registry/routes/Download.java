package xyz.juanes.jviz.registry.routes;

import org.json.simple.JSONObject;

import xyz.juanes.jviz.registry.Config;
import xyz.juanes.jviz.registry.firebase.FirebaseREST;
import xyz.juanes.jviz.registry.firebase.FirebaseResponse;
import xyz.juanes.jviz.registry.helpers.HttpOut;
import xyz.juanes.jviz.registry.helpers.SplitUrl;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Download extends HttpServlet
{
  //Init function
  public void init(ServletConfig conf){ Config.initFirebase(); }

  //Download
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
  {
    //Split the url
    String[] url = SplitUrl.split(request);

    //Check the length
    if(url.length != 2)
    {
      //Display the error
      HttpOut.error(response, 400, "Bad request"); return;
    }

    //Get the module info
    FirebaseResponse res = FirebaseREST.get("modules/" + url[0]);

    //Check for null or error
    if(res.isError() == true)
    {
      //Display the error
      HttpOut.error(response, 500, "Error connecting to the database"); return;
    }

    //Check for empty response
    if(res.isNull() == true)
    {
      //Display the error
      HttpOut.error(response, 404, "Module not found"); return;
    }

    //Try reading the information
    try
    {
      //Build the json object
      JSONObject obj = res.getBodyJSON();

      //Build the download url
      String download = obj.get("repository") + Config.download.replace("{release}", url[1]);

      //Redirect
      response.sendRedirect(download);
    }
    catch(Exception e)
    {
      //Display error
      HttpOut.error(response, 500, "Error redirecting..."); return;
    }

    //Exit
    return;
  }
}
