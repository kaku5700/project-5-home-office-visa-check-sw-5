package gov.uk.check.visa.customlisteners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static gov.uk.check.visa.utility.Utility.takeScreenShot;


public class CustomListeners implements ITestListener {

    public ExtentSparkReporter reporter;
    public ExtentReports reports;
    public static ExtentTest test;

    @Override
    public void onTestStart(ITestResult iTestResult) {
        test = reports.createTest(iTestResult.getName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        test.log(Status.PASS, "TEST CASE PASSED IS " + iTestResult.getName());
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        test.log(Status.FAIL, "TEST FAILED IS " + iTestResult.getName());
        test.log(Status.FAIL, "TEST FAILED IS " + iTestResult.getThrowable());
        String screenshotPath = takeScreenShot(iTestResult.getName());
        test.addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        test.log(Status.SKIP, "TEST SKIPPED IS " + iTestResult.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        reporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/extent.html");   //location
        reporter.config().setDocumentTitle("Automation Report");
        reporter.config().setReportName("Home Office Visa Check");
        reporter.config().setTheme(Theme.DARK);
        reports = new ExtentReports();
        reports.attachReporter(reporter);

        reports.setSystemInfo("User Name", System.getProperty("user.name"));
        reports.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
        reports.setSystemInfo("Machine", "Windows 11 Professional" + "64 Bit");
        reports.setSystemInfo("Selenium", "4.15.0");
        reports.setSystemInfo("Maven", "4.0.0");
        reports.setSystemInfo("Java Version", "17.0.8");
    }

    @Override
    public void onFinish(ITestContext iTestContext)
    {
        reports.flush();
    }
}