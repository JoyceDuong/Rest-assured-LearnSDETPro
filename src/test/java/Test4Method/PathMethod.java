package Test4Method;

import Buider.BodyJSONBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import module.PostBody;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PathMethod {

    // Sử dụng class bodyJsonBuilder.getJSOM conntent để chuyển từ dạng Java object sang Json (String) để gửi trong request

    @BeforeClass
    public void init() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
        RestAssured.basePath = "/posts";

    }

    @Test
    public void Path_User() {

        PostBody postBody = new PostBody();
        postBody.setTitle(" title for Path");


        String jsonBody = BodyJSONBuilder.getJSONContent(postBody);
        System.out.println(jsonBody);
        Response response = given().contentType(ContentType.JSON).body(jsonBody).patch("/1");
        response.prettyPrint();


    }

    @AfterClass
    public void Close() {
        RestAssured.baseURI = null;
        RestAssured.basePath = null;

    }
}
