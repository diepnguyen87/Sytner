package commons;

import environmentFactory.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

import java.time.Duration;

public class BaseTest {

    public BaseTest() {
        tDriver = new ThreadLocal<WebDriver>();
        this.log = LogFactory.getLog(getClass());
        longTimeout = GlobalContants.LONG_TIMEOUT;
        appURL = getURLByEnvironmentName();
    }

    public WebDriver getDriver() {
        return tDriver.get();
    }
    protected WebDriver getBrowserDriverAndOpenHomePage(String seleniumComp, String IP, String port, String osName, String osVerion, String browserName) {

        SeleniumComp type = SeleniumComp.valueOf(seleniumComp.toUpperCase());
        switch (type) {
            case LOCAL:
                tDriver.set(new LocalFactory(appURL, browserName).createDriver());
                break;
            case GRID:
                tDriver.set(new GridFactory(appURL, osName, browserName, IP, port).createDriver());
                break;
            case BROWSER_STACK:
                tDriver.set(new BrowserStackFactory(appURL, osName, osVerion, browserName).createDriver());
                break;
            case SAUCE_LAB:
                tDriver.set(new SauceLabFactory(appURL, osName, browserName).createDriver());
                break;
            case CROSS_BROWSER:
                /* TO DO */
                break;
            case LAMBDA:
               tDriver.set(new LambdaFactory(appURL, osName, browserName).createDriver());
                break;
            default:
                throw new RuntimeException("Loi default day ne");
        }

        getDriver().manage().timeouts().implicitlyWait(longTimeout);
        getDriver().manage().window().maximize();
        getDriver().get(appURL);
        return getDriver();
    }

    private String getURLByEnvironmentName() {
        String environmentName = System.getProperty("env");
        //String environmentName = "Dev";
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

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        if (getDriver() != null) {
            System.out.println("driver closed: " + tDriver.get());
            getDriver().quit();
        }
    }

    private ThreadLocal<WebDriver> tDriver;
    protected final Log log;
    private Duration longTimeout;
    protected String appURL;

    private String projectPath = GlobalContants.PROJECT_PATH;
}
