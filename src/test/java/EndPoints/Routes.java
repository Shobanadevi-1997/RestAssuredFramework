package EndPoints;

public class Routes {
    public static String baseUrl = "https://petstore.swagger.io/v2";

    //USER
    public static String userPostUrl = baseUrl+"/user";
    public static String userGetPutDeleteUrl = baseUrl+"/user/{username}";
}
