package JiraAPI;

import Buider.IssueContentBuilder;
import Util.AuthenticationHandler;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import module.BaseTest;
import module.IssueFields;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsStringIgnoringCase;

public class JiraNewIssue implements BaseTest {
    String baseURI, path, email, token;

    @BeforeClass
    public void init() {
        // API info
        baseURI = "https://joyceduong.atlassian.net";
        path = "/rest/api/3/issue";

        email = "Joyceduong2021@gmail.com";
        token = "dqrRRTnG8JyBB2tBeDXB9AD6";


    }

    @Test
    public void Create_Issue() {

        // Util để build 1 json để gửi đi
        // Lấy ra java object để get response sau đó so sánh

        String encodedCredStr = AuthenticationHandler.encodedStr(email, token);

        // request object
        RequestSpecification request = given();
        request.baseUri(baseURI);
        request.header(contentTypeHeader);
        request.header(acceptJsonHeader);
        request.header(getAuthorizationHeader.apply(encodedCredStr));

        // define Body
        String summary = RandomStringUtils.random(20 , true , true);

        IssueContentBuilder issueContentBuilder = new IssueContentBuilder();
       String issueBody =  issueContentBuilder.build(summary , "JDP" , "10001");

       // lấy ra Object để verify
        IssueFields issueFields = issueContentBuilder.getIssueFields();
        String expectedSummary = issueFields.getFields().getSummary();



        // send request - CREATE issue
        Response response = request.body(issueBody).post(path);
        response.prettyPrint();

        response.then().body("key", containsStringIgnoringCase("JDP"));

        Map<String,String> resBody = JsonPath.from(response.asString()).get();
        String id = resBody.get("id");

        String issuePath = path + "/" + id;

        // Send request Get
        Response response1 = request.get(issuePath);
        response1.prettyPrint();

        Map<String , Object>  fields = JsonPath.from(response1.getBody().asString()).get("fields");
        String actualSumarry = fields.get("summary").toString();

        Map<String ,Object> status = (Map<String, Object>) fields.get("status");
        Map<String ,String> statusCategory = (Map<String ,String>) status.get("statusCategory");
        String name = statusCategory.get("name").toString();

        Assert.assertEquals(actualSumarry , expectedSummary);
        Assert.assertEquals(name , "To Do");






    }

    @AfterClass
    public void Close() {
        RestAssured.baseURI = null;
        RestAssured.basePath = null;

    }
}
