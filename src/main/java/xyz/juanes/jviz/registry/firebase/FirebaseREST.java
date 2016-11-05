package xyz.juanes.jviz.registry.firebase;

public class FirebaseREST
{
  //Do a get request
  public static FirebaseResponse get(String path){ return doRequest("GET", path); }

  //Do a post request
  public static FirebaseResponse post(String path, String body){ return doRequest("POST", path, body); }

  //Do a put request
  public static FirebaseResponse put(String path, String body){ return doRequest("PUT", path, body); }

  //Do a patch request
  public static FirebaseResponse patch(String path, String body){ return doRequest("PATCH", path, body); }

  //Do a delete request
  public static FirebaseResponse delete(String path){ return doRequest("DELETE", path); }

  //Do a complete request
  private static FirebaseResponse doRequest(String method, String path, String body)
  {
    //Get the new Firebase request object
    FirebaseRequest request = new FirebaseRequest(method);

    //Set the url
    request.setUrl(FirebaseConfig.buildUrl(path));

    //Set the body
    request.setBody(body);

    //Do the request and return
    return request.doRequest();
  }

  //Do a basic request
  private static FirebaseResponse doRequest(String method, String path)
  {
    //Get the new Firebase request object
    FirebaseRequest request = new FirebaseRequest(method);

    //Set the url
    request.setUrl(FirebaseConfig.buildUrl(path));

    //Do the request and return
    return request.doRequest();
  }
}
