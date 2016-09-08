package xyz.juanes.jviz.registry.helpers;

import com.auth0.jwt.JWTSigner;
import xyz.juanes.jviz.registry.Config;

import java.util.HashMap;

public class Token
{
  //Generate a new token
  public static String generate(String uid)
  {
    //Get the token expired
    long iat = System.currentTimeMillis() / 1000L;

    //Get the token expiration
    long exp = iat + 7L*24L*60L*60L;

    JWTSigner signer = new JWTSigner(Config.token_secret);

    //Initialize the claims
    HashMap<String, Object> claims = new HashMap<>();

    //Add the issuer
    claims.put("iss", Config.token_issuer);

    //Add the expiration
    claims.put("exp", exp);

    //Add the token creation
    claims.put("iat", iat);

    //Add the user ID
    claims.put("uid", uid);

    //Return the token
    return signer.sign(claims);
  }
}
