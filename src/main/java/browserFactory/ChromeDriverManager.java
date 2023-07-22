package browserFactory;

import commons.GlobalContants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ChromeDriverManager extends BrowserFactory{

    private WebDriver driver;

    @Override
    public WebDriver getBrowserDriver() {
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.default_directory", GlobalContants.DOWNLOAD_PATH);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("window-size=1920x1080");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-geolocation");
        chromeOptions.addArguments("--lang=en");
        //chromeOptions.addArguments("--incognito");

        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        chromeOptions.setExperimentalOption("prefs", prefs);

        /*File chromeFile = new File(projectPath.concat("/BrowserExtension/Adblock_3_17_0.crx"));
        chromeOptions.addExtensions(chromeFile);*/
        driver = new ChromeDriver(chromeOptions);
        return driver;
    }
}
