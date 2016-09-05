package xyz.juanes.jviz.registry.helpers;

//Import dependencies
import javax.servlet.http.HttpServletRequest;

//Get params from url
public class SplitUrl
{
  //Split an url
  public static String[] split(HttpServletRequest request)
  {
    //Get the path and remove the first /
    //This is because the getPathInfo method returns a path like "/jviz", and the first
    //element of the split method is an empty string.
    String pathInfo = request.getPathInfo().substring(1);

    //Check for empty or null pathinfo
    if(pathInfo == "" || pathInfo == null)
    {
      //Return an empty array
      return new String[0];
    }

    //Split the url
    String[] url = pathInfo.split("/");

    //Return the url
    return url;
  }
}
