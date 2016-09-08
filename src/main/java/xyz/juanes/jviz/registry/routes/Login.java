package xyz.juanes.jviz.registry.routes;

import xyz.juanes.jviz.registry.Config;
import xyz.juanes.jviz.registry.firebase.FirebaseConfig;
import xyz.juanes.jviz.registry.firebase.FirebaseREST;
import xyz.juanes.jviz.registry.firebase.FirebaseResponse;
import xyz.juanes.jviz.registry.helpers.Crypto;
import xyz.juanes.jviz.registry.helpers.HttpOut;
import xyz.juanes.jviz.registry.helpers.Token;

import org.json.simple.JSONObject;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Login extends HttpServlet
{
  //Init method
  public void init(ServletConfig config){ Config.initFirebase(); }

  //Get
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
  {
    //Return the error
    HttpOut.error(response, 405, "Method not allowed");
  }

  //Post user and password
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
  {
    //Get the user params
    String user_id = request.getParameter("user_id");
    String user_pwd = request.getParameter("user_pwd");

    //Get tue user data
    FirebaseResponse res = FirebaseREST.get("users/" + user_id);

    //Check the response
    if(res.isError() == true)
    {
      //Display internal error
      HttpOut.error(response, 500, "Error connecting to the database");
      return;
    }

    //Check for null bodu
    if(res.isNull() == true || res.getCode() != 200)
    {
      //Return user not found
      HttpOut.error(response, 404, "User not found");
      return;
    }

    //Try read the data
    try
    {
      //Get the json object
      JSONObject obj = res.getBodyJSON();

      //Convert the password to base64
      String pwd = Crypto.base64(user_pwd);

      //Check both passwords
      if(obj.get("pwd").equals(pwd) == true)
      {
        //Initialize the JSON object
        JSONObject out = new JSONObject();

        //Add the new token
        out.put("token", Token.generate(user_id));

        //Return
        HttpOut.send(response, 200, out);
        return;
      }
      else
      {
        //Display error
        HttpOut.error(response, 401, "Incorrect password");
        return;
      }
    }
    catch(Exception e)
    {
      //Return a new error
      HttpOut.error(response, 500, "Error getting the user info");
      return;
    }
  }
}
