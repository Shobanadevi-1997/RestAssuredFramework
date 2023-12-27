package EndPoints;

import PayLoads.User;
import Utility.ExtentReportListener;
import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserEndPointAction {

    static ObjectMapper mapper = new ObjectMapper();

    public static Response createUser(User user) throws JsonProcessingException {
            Response response =  given().contentType(ContentType.JSON).accept(ContentType.JSON).body(user).
                    when().post(Routes.userPostUrl);
            return response;
    }

    public static Response readUser(String userName){
        Response response =  given().pathParam("username",userName).
                when().get(Routes.userGetPutDeleteUrl);
        return response;
    }

    public static Response deleteUser(String userName){
        Response response =  given().pathParam("username",userName).accept(ContentType.JSON).
                when().delete(Routes.userGetPutDeleteUrl);
        return response;
    }

    public static Response updateUser(String userName,User user) throws JsonProcessingException {
        Response response =  given().pathParam("username",userName).contentType(ContentType.JSON).accept(ContentType.JSON).body(user).
                when().put(Routes.userGetPutDeleteUrl);
        return response;
    }

}
