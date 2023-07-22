package environmentFactory;

import commons.BrowserList;
import commons.GlobalContants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class SauceLabFactory {
    private WebDriver driver;
    private String appURL;

    private String osName;
    private String browserName;
    private BrowserList currentBrowser;
    private String currentBrowsername;

    public SauceLabFactory(String appURL, String osName, String browserName) {
        this.appURL = appURL;
        this.osName = osName;
        this.browserName = browserName;
    }

    public WebDriver createDriver(){
        if (driver == null) {

            currentBrowser = BrowserList.valueOf(browserName.toUpperCase());
            currentBrowsername = currentBrowser.name();

            Map<String, Object> sauceOptions = new HashMap<>();
            sauceOptions.put("build", "1.0.1");
            if(osName.toLowerCase().contains("macos")){
                sauceOptions.put("screenResolution", "2048x1536");
            }else{
                sauceOptions.put("screenResolution", "2560x1600");
            }
            sauceOptions.put("name", "Run on " + osName + " | " + browserName);

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", osName);
            caps.setCapability("browserName", browserName);
            caps.setCapability("browserVersion", "latest");
            caps.setCapability("sauce:options", sauceOptions);

            try {
                driver = new RemoteWebDriver(new URL(GlobalContants.GC_INSTANCE.getSauceLabURL()), caps);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        return driver;
    }
}
