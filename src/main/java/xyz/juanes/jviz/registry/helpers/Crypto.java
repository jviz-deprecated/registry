package xyz.juanes.jviz.registry.helpers;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;

public class Crypto
{
  //Generate the md5
  public static String md5(String str)
  {
    //Try generating the md5 value
    try
    {
      //Get the md5 diggest
      MessageDigest md = MessageDigest.getInstance("MD5");

      //Check the password
      return md.digest(str.getBytes("UTF-8")).toString();
    }
    catch(Exception e)
    {
      //Return a null string
      return null;
    }
  }

  //Generate the base 64
  public static String base64(String str)
  {
    //Encode
    byte[] encodedBytes = Base64.encodeBase64(str.getBytes());

    //Return the encoded string
    return new String(encodedBytes);
  }
}
