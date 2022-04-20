package JiraAPI;

import Util.AuthenticationHandler;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import module.BaseTest;
import org.apache.commons.codec.binary.Base64;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class GetProject_ExtractRespond implements BaseTest {

    RequestSpecification request = given();

    @BeforeClass
    public void init() {
        String baseURI = "https://joyceduong.atlassian.net";

        request.baseUri(baseURI);
        request.header(contentTypeHeader);
        request.header(acceptJsonHeader);


    }

    @Test
    public void Get_Project() {

        // extract trực tiếp nested response

        String email = "Joyceduong2021@gmail.com";
        String token = "dqrRRTnG8JyBB2tBeDXB9AD6";
        String encodedCredStr = AuthenticationHandler.encodedStr(email, token);


        request.header(getAuthorizationHeader.apply(encodedCredStr));

        Response response = request.get("/rest/api/3/project/JDP");
//        response.prettyPrint();

        // Map các dữ liệu response
        Map<String, List<Map<String, Object>>> projectInfo = JsonPath.from(response.asString()).get();
        System.out.println(projectInfo.get("id"));

        // Get list các issueTypes
        List<Map<String, Object>> issueTypes = projectInfo.get("issueTypes");

        List<String> ids = new ArrayList<>();
        for (Map<String, Object> issueType : issueTypes) {
            ids.add(issueType.get("id").toString());

        }
        System.out.println(ids);
    }


    @AfterClass
    public void Close() {
        RestAssured.baseURI = null;
        RestAssured.basePath = null;

    }
}
