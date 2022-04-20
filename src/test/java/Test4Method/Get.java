package Test4Method;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Get {

    // Get Method
    // Extract trục tiếp reponse body sủ dụng hamcret tích hợp trong rest-assured

    @BeforeClass
    public void init() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
        RestAssured.basePath = "/users";

    }

    @Test
    public void Get_User() {

        Response response = given().get("/2");
        response.prettyPrint();

        // verify status code
        response.then().statusCode(200);

        // Get object cha
        response.then().body("id", equalTo(2));

        // get object con
        response.then().body("address.street", equalTo("Victor Plains"));

    }

    @AfterClass
    public void Close() {
        RestAssured.baseURI = null;
        RestAssured.basePath = null;

    }

}
