package xyz.juanes.jviz.registry.firebase;

public class FirebaseConfig
{
  //Firebase url
  public static String url = "https://app.firebaseio.com/";

  //Firebase credentials
  public static String credentials = "";

  //Set the url
  public static void setUrl(String u){ url = u; }

  //Set the credentials
  public static void setCredentials(String c){ credentials = c; }

  //Generate the firebase url
  public static String buildUrl(String path)
  {
    //Return the new url
    return url + path + ".json?auth=" + credentials;
  }
}
