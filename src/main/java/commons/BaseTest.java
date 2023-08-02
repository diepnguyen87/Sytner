package commons;

import environmentFactory.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pageObjects.HomePage;
import pageObjects.PageGeneratorManager;

import java.time.Duration;

public class BaseTest {

    @Parameters({"seleniumComp", "IP", "port", "osName", "osVersion", "browserName"})
    @BeforeTest
    public void beforeTest(String seleniumComp, @Optional String IP, @Optional String port, @Optional String osName, @Optional String osVersion, String browserName) {
        driver = getBrowserDriverAndOpenHomePage(seleniumComp, IP, port, osName, osVersion, browserName);
        homePage = PageGeneratorManager.getHomePageObject(driver);
        homePage.acceptAllCookies();
    }

    public BaseTest() {
        this.log = LogFactory.getLog(getClass());
        longTimeout = GlobalContants.LONG_TIMEOUT;
        appURL = getURLByEnvironmentName();
    }

    public WebDriver getDriver() {
        return driver;
    }

    protected WebDriver getBrowserDriverAndOpenHomePage(String seleniumComp, String IP, String port, String osName, String osVerion, String browserName) {

        SeleniumComp type = SeleniumComp.valueOf(seleniumComp.toUpperCase());
        switch (type) {
            case LOCAL:
                driver = new LocalFactory(appURL, browserName).createDriver();
                break;
            case GRID:
                driver = new GridFactory(appURL, osName, browserName, IP, port).createDriver();
                break;
            case BROWSER_STACK:
                driver = new BrowserStackFactory(appURL, osName, osVerion, browserName).createDriver();
                break;
            case SAUCE_LAB:
                driver = new SauceLabFactory(appURL, osName, browserName).createDriver();
                break;
            case CROSS_BROWSER:
                /* TO DO */
                break;
            case LAMBDA:
                driver = new LambdaFactory(appURL, osName, browserName).createDriver();
                break;
            default:
                throw new RuntimeException("Loi default day ne");
        }

        driver.manage().timeouts().implicitlyWait(longTimeout);
        driver.manage().window().maximize();
        driver.get(appURL);
        return driver;
    }

    private String getURLByEnvironmentName() {
        String environmentName = System.getProperty("env");
        EnvironmentList env = EnvironmentList.valueOf(environmentName.toUpperCase());
        String url = null;

        switch (env) {
            case DEV:
                url = GlobalContants.GC_INSTANCE.getDevURL();
                break;
            case TEST:
                url = GlobalContants.GC_INSTANCE.getTestURL();
                break;
            case STAGING:
                url = GlobalContants.GC_INSTANCE.getStagingURL();
                break;
            case LIVE:
                url = GlobalContants.GC_INSTANCE.getLiveURL();
                break;
            default:
                throw new IllegalArgumentException(environmentName + "is an invalid environment name");
        }
        System.out.println(url);
        return url;
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected WebDriver driver;
    protected final Log log;
    private Duration longTimeout;
    protected String appURL;

    private String projectPath = GlobalContants.PROJECT_PATH;
    protected HomePage homePage;
}
