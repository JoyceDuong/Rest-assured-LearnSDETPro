package Util;

import org.apache.commons.codec.binary.Base64;

public class AuthenticationHandler {
    public static String encodedStr (String email , String token){
        if (email == null || token == null){
            throw  new IllegalArgumentException(" Argurment cannot null");
        }
        String cred = email + ":" + token;
        byte[] ecodedCred = Base64.encodeBase64(cred.getBytes());
       return  new String(ecodedCred);
    }
}
