package environmentFactory;

import commons.BrowserList;
import commons.GlobalContants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStackFactory {
    private WebDriver driver;
    private String appURL;

    private String osName;
    private String osVersion;
    private String browserName;
    private BrowserList currentBrowser;
    private String currentBrowsername;

    public BrowserStackFactory(String appURL, String osName, String osVersion, String browserName) {
        this.appURL = appURL;
        this.osName = osName;
        this.osVersion = osVersion;
        this.browserName = browserName;
    }

    public WebDriver createDriver(){
        if (driver == null) {

            currentBrowser = BrowserList.valueOf(browserName.toUpperCase());
            currentBrowsername = currentBrowser.name();

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("project", "Sauce Demo");
            caps.setCapability("build", "1.0.1");
            caps.setCapability("resolution", "2560x1600");
            caps.setCapability("os", osName);
            caps.setCapability("os_version", osVersion);
            caps.setCapability("browser", browserName);
            caps.setCapability("browser_version", "latest");
            caps.setCapability("name", "Run on " + osName + " | " + osVersion + " | " + browserName);

            try {
                driver = new RemoteWebDriver(new URL(GlobalContants.GC_INSTANCE.getBrowserStackURL()), caps);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        return driver;
    }
}
