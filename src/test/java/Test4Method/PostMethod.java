package Test4Method;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import module.PostBody;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.equalTo;

public class PostMethod {

    // Post trực tiếp Json dạng String
    // Gson - Tạo Java Object dạng đơn giản -> chuyển qua String Json để gửi trong request


    @BeforeClass
    public void init() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
//        RestAssured.basePath = "/posts";

    }

    @Test
    public void Post_User() {
        String bodyString ="{\n" +
                "  \"userId\": 10,\n" +
                "  \"id\": 3,\n" +
                "  \"title\": \" title request test\",\n" +
                "  \"body\": \"body request test\"\n" +
                "}";

        Gson gson = new Gson();
        PostBody postBody = new PostBody();
        postBody.setUserId(2);
        postBody.setId(5);
        postBody.setTitle(" this is title");
        postBody.setBody(" this is body");

        String jsBody = gson.toJson(postBody);

        Response response = given().contentType(ContentType.JSON).body(jsBody).post("/posts");
        response.prettyPrint();

        System.out.println(response.statusCode());
        System.out.println(response.statusLine());

        // Verify
        response.then().statusLine(containsStringIgnoringCase("201 Created"));
        response.then().statusCode(equalTo(201));

    }

    @AfterClass
    public void Close() {
        RestAssured.baseURI = null;
        RestAssured.basePath = null;

    }
}
