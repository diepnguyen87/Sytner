package browserFactory;

import commons.GlobalContants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CocCocDriverManager extends BrowserFactory{

    private WebDriver driver;

    @Override
    public WebDriver getBrowserDriver() {
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.default_directory", GlobalContants.DOWNLOAD_PATH);

        ChromeOptions ccOptions = new ChromeOptions();
        ccOptions.addArguments("--disable-infobars");
        ccOptions.addArguments("--disable-notifications");
        ccOptions.addArguments("--disable-geolocation");
        ccOptions.addArguments("--lang=en");
        //ccOptions.addArguments("--incognito");
        ccOptions.setBinary("");
        ccOptions.setExperimentalOption("useAutomationExtension", false);
        ccOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        ccOptions.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(ccOptions);
        return driver;
    }
}
