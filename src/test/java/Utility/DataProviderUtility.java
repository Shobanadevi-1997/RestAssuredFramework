package Utility;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DataProviderUtility {
    ExcelUtility excel = new ExcelUtility();
    public static final String PATH = System.getProperty("user.dir")+"//src//test//resources//TestData//";
    public static final String USER_FILENAME = "UserData";
    public static final String USER_INSHEET = "INPUT";

    @DataProvider(name = "User")
    public Object[][] userDataProvider() throws IOException {
        String[][] data = excel.readExcelTo2D(PATH,USER_FILENAME,USER_INSHEET);
        return data;
    }


}
