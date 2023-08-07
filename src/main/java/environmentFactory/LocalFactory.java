package environmentFactory;

import commons.BrowserList;
import commons.EnvironmentList;
import commons.GlobalContants;
import browserFactory.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LocalFactory {
    private WebDriver driver;
    private String appURL;
    private String browserName;
    private String role;
    private String envName;
    private BrowserList currentBrowser;
    private String currentBrowsername;

    public LocalFactory(String appURL, String browserName) {
        this.appURL = appURL;
        this.browserName = browserName;
        System.out.println("Local Factory - " + browserName + ": " + this);
    }

    public WebDriver createDriver() {
        if (driver == null) {
            currentBrowser = BrowserList.valueOf(browserName.toUpperCase());
            currentBrowsername = currentBrowser.name();
            disableBrowserDriverLogOnIDE();
            switch (currentBrowser) {
                case CHROME:
                    driver = new ChromeDriverManager().getBrowserDriver();
                    break;
                case CHROME_HEADLESS:
                    driver = new ChromeHeadlessDriverManager().getBrowserDriver();
                    break;
                case FIREFOX:
                    driver = new FireFoxDriverManager().getBrowserDriver();
                    break;
                case FIREFOX_HEADLESS:
                    driver = new FireFoxHeadlessDriverManager().getBrowserDriver();
                    break;
                case EDGE:
                    driver = new EdgeDriverManager().getBrowserDriver();
                    break;
                case SAFARI:
                    driver = new SafariDriverManager().getBrowserDriver();
                    break;
                case COC_COC:
                    driver = new CocCocDriverManager().getBrowserDriver();
                    break;
                case IE:
                    driver = new IEDriverManager().getBrowserDriver();
                    break;
                default:
                    throw new RuntimeException("[ERROR] The browser " + browserName + " is NOT supported");
            }
        }

        return driver;
    }

    private void disableBrowserDriverLogOnIDE() {
        if (currentBrowsername.startsWith("CHROME")) {
            System.setProperty("webdriver.chrome.args", "--disable-logging");
            System.setProperty("webdriver.chrome.silentOutput", "true");
        } else if (currentBrowsername.startsWith("FIREFOX")) {
            //System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalContants.BROWSER_DRIVER_LOG.concat("log.txt"));
        } else {
            System.out.println("[Warning] " + currentBrowsername + " is NOT supported to disable browser driver logs");
        }
    }

    private String getAppURLByEnvironmentName(String environmentName) {
        EnvironmentList env = EnvironmentList.valueOf(environmentName);
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
}
