package xyz.juanes.jviz.registry.routes;

import xyz.juanes.jviz.registry.firebase.FirebaseREST;
import xyz.juanes.jviz.registry.firebase.FirebaseResponse;
import xyz.juanes.jviz.registry.helpers.Crypto;
import xyz.juanes.jviz.registry.helpers.HttpError;
import xyz.juanes.jviz.registry.helpers.Token;

import org.json.simple.JSONObject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Login extends HttpServlet
{
  //Post user and password
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
  {
    //Set the content type
    response.setContentType("application/json");

    //Get the username
    String user_id = request.getParameter("user_id");

    //Get tue user password
    String user_pwd = request.getParameter("user_pwd");

    //Get tue user data
    FirebaseResponse res = FirebaseREST.get("users/" + user_id);

    //Check the response
    if(res.isError() == true)
    {
      //Display internal error
      HttpError.send(response, 500, "Error connecting to the database");

      //Exit
      return;
    }

    //Check for null bodu
    if(res.isNull() == true || res.getCode() != 200)
    {
      //Return user not found
      HttpError.send(response, 404, "User not found");

      //Exit
      return;
    }

    //Try read the data
    try
    {
      //Get the json object
      JSONObject obj = res.getBodyJSON();

      //Convert the password to MD5
      String pwd = Crypto.md5(user_pwd);

      //Check both passwords
      if(obj.get("pwd") == pwd)
      {
        //Generate the new token
        String token = Token.generate(user_id);
      }
      else
      {
        //Display error
        HttpError.send(response, 401, "Incorrect password");
      }
    }
    catch(Exception e)
    {
      //Return a new error
      HttpError.send(response, 500, "Error getting the user info");
    }

    //Exit
    return;
  }
}
