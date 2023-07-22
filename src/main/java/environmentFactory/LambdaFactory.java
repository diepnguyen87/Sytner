package environmentFactory;

import commons.BrowserList;
import commons.GlobalContants;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LambdaFactory {
    private WebDriver driver;
    private String appURL;
    private String osName;
    private String browserName;
    private BrowserList currentBrowser;
    private GlobalContants globalContants;

    public LambdaFactory(String appURL, String osName, String browserName) {
        this.appURL = appURL;
        this.osName = osName;
        this.browserName = browserName;
        globalContants = ConfigFactory.create(GlobalContants.class);
    }

    public WebDriver createDriver() {
        if (driver == null) {

            currentBrowser = BrowserList.valueOf(browserName.toUpperCase());

            Map<String, Object> ltOptions = new HashMap<>();
            ltOptions.put("build", "1.0.1");
            ltOptions.put("platformName", osName);
            ltOptions.put("resolution", "1920x1080");
            ltOptions.put("name", "Run on " + osName + " | " + browserName);

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browserName", browserName);
            caps.setCapability("browserVersion", "latest");
            caps.setCapability("LT:Options", ltOptions);

            try {
                driver = new RemoteWebDriver(new URL(globalContants.getLambdaURL()), caps);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        return driver;
    }
}
