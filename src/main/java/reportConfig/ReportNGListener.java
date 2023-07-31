package reportConfig;

import commons.BaseTest;
import commons.GlobalContants;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReportNGListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.setProperty("org.uncommons.reportng.escape-output", "false");

        Object testClass = iTestResult.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();

        //String screenshotPath = captureScreenshotAsFile(driver, iTestResult.getName());
        String screenshotPath = captureScreenshotAsBase64(driver);

        Reporter.getCurrentTestResult();
        //Reporter.log("<br/><a target='_blank' href='file:///" + screenshotPath + "'> <img src='file:///" + screenshotPath + "' height='100' width='150'/></a>");
        Reporter.log("<br/><a target='_self' href='data:image/png;base64," + screenshotPath + "'> <img src='data:image/png;base64," + screenshotPath + "' height='100' width='150'/></a>");
        Reporter.setCurrentTestResult(null);
    }

    private String captureScreenshotAsFile(WebDriver driver, String screenshotName){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = GlobalContants.REPORTNG_SCREENSHOT_PATH + screenshotName + "_" + sdf.format(calendar.getTime()) + ".png";

        try {
            FileUtils.copyFile(file, new File(screenshotPath));
            return screenshotPath;
        } catch (IOException e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
            return e.getMessage();
        }
    }

    private String captureScreenshotAsBase64(WebDriver driver){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
