package module;


import io.restassured.http.Header;

import java.util.function.Function;

public interface BaseTest {

    // Builder header
    // 2 cách viết để get authorization avf truyền trong header


    Header contentTypeHeader = new Header("Content-type" , "application/json");
    Header acceptJsonHeader = new Header("Accept" , "application/json");

    static Header getAuthorizationHeader(String encodedCredStr) {
        if (encodedCredStr == null) {
            throw new IllegalArgumentException(" encodedCredStr cannot null");
        }
        return new Header("Authorization", "Basic " + encodedCredStr);


    }

    Function<String , Header> getAuthorizationHeader = encodedCredStr ->{
        if (encodedCredStr == null) {
            throw new IllegalArgumentException(" encodedCredStr cannot null");
        }
        return new Header("Authorization", "Basic " + encodedCredStr);
    };

}
