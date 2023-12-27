package Utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportListener implements ITestListener {
    ExtentSparkReporter spark ;
    ExtentReports extent;
    public static ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("DD-MM-yyyy-HH-mm-ss");
        Date date = new Date();
        String currentTime = dateFormat.format(date.getTime()).toString();
        spark = new ExtentSparkReporter(System.getProperty("user.dir")+"\\Reports\\"+"Report_"+currentTime+".html");
        spark.config().setReportName("Rest Assured Framework");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("User","Shobana");
    }

    public void onTestSuccess(ITestResult result){
        test = extent.createTest(result.getName());
        test.createNode(result.getName());
        test.log(Status.PASS, result.getMethod()+" passed");
    }

    public void onTestFail(ITestResult result){
        test = extent.createTest(result.getName());
        test.createNode(result.getName());
        test.log(Status.FAIL, result.getTestName()+" failed"+" "+result.getThrowable().getMessage());
    }

    public void onTestSkip(ITestResult result){
        test = extent.createTest(result.getName());
        test.createNode(result.getName());
        test.log(Status.SKIP, result.getTestName()+" skipped"+" "+result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
