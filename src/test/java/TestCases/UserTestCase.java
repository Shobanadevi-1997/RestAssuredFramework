package TestCases;

import EndPoints.UserEndPointAction;
import PayLoads.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class UserTestCase {
    Faker faker;
    User user;


    @BeforeClass
    public void setup() throws IOException {
        faker = new Faker();
            user = new User();
            user.setId(faker.idNumber().hashCode());
            user.setUsername(faker.name().username());
            user.setFirstName(faker.name().firstName());
            user.setLastName(faker.name().lastName());
            user.setPassword(faker.internet().password(5, 10));
            user.setEmail(faker.internet().safeEmailAddress());
            user.setPhone(faker.phoneNumber().phoneNumber());
    }

    @Test(priority = 1,dataProvider = "User",dataProviderClass = Utility.DataProviderUtility.class)
    public void validatePostUser(String[] testData) throws JsonProcessingException {
        user.setId(Integer.parseInt(testData[0]));
        user.setUsername(testData[1]);
        user.setFirstName(testData[2]);
        user.setLastName(testData[3]);
        user.setPassword(testData[4]);
        user.setEmail(testData[5]);
        user.setPhone(testData[6]);
        Response response = UserEndPointAction.createUser(user);
        response.then().log().all().statusCode(200);
        System.out.println(user.getUsername());
    }

    @Test(priority=2)
    public void validateGetUser(){
        System.out.println(user.getUsername());
        Response response = UserEndPointAction.readUser(user.getUsername());
        response.then().log().all().statusCode(200);
    }

    @Test(priority = 3, enabled = false)
    public void validateUpdateUser() throws JsonProcessingException {
        //can change all data except username
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndPointAction.updateUser(user.getUsername(),user);
        response.then().log().body().statusCode(200);
        System.out.println(user.getUsername());
    }

    @Test(priority=4, enabled = false)
    public void validateDeleteUser(){
        Response response = UserEndPointAction.deleteUser(user.getUsername());
        response.then().log().all().statusCode(200);
    }
}
