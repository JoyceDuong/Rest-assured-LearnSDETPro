package Test4Method;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import module.PostBody;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PutMethod {
    @BeforeClass
    public void init() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
        RestAssured.basePath = "/posts";

    }

    @Test
    public void Get_User() {

        // Map response với java object
        // extract trực tiếp respond

        Gson gson = new Gson();
        PostBody postBody = new PostBody();
        postBody.setUserId(1);
        postBody.setId(2);
        postBody.setTitle(" new title");
        postBody.setBody(" new body");

        String postedBody = gson.toJson(postBody);
        System.out.println(postedBody);

        Response response = given().contentType(ContentType.JSON).when().body(postedBody).put("/2");

        response.then().body("title" , equalTo(postBody.getTitle()));
        response.then().body("body" , equalTo(postBody.getBody()));
        response.then().body("id" , equalTo(postBody.getId()));
        response.then().body("userId" , equalTo(postBody.getUserId()));


        // Map với Java object -> extract
        String newTitle = gson.fromJson(response.prettyPrint(), PostBody.class).getTitle();
        System.out.println(newTitle);

        // Extract trực tiếp từ respond
        JsonObject js = gson.fromJson(response.prettyPrint() , JsonObject.class);
        String newBody = js.get("body").getAsString();
        System.out.println(newBody);

    }

    @AfterClass
    public void Close() {
        RestAssured.baseURI = null;
        RestAssured.basePath = null;

    }
}
