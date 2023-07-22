package browserFactory;

import commons.GlobalContants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class FireFoxDriverManager extends BrowserFactory{

    private WebDriver driver;

    @Override
    public WebDriver getBrowserDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        //firefoxOptions.setCapability("marionette", true);
        firefoxOptions.addArguments("window-size=1920x1080");
        //firefoxOptions.addArguments("-private");
        firefoxOptions.addPreference("browser.download.folderList", 2);
        firefoxOptions.addPreference("browser.download.dir", GlobalContants.DOWNLOAD_PATH);
        firefoxOptions.addPreference("browser.download.userDownloadDir", true);
        firefoxOptions.addPreference("browser.helperApps.neverAsk.saveToDisk", "multipart/x-zip" +
                ",application/zip,application/x-zip-compressed,application/x-compressed" +
                ",application/pdf,application/excel,application/vnd.ms-excel,application/x-excel,application/x-msexcel,application/octet-stream,application/msword,application/csv" +
                ",text/csv,text/html,text/plain" +
                ",image/png,image/jpeg,");
        firefoxOptions.addPreference("pdfjs.disabled", true);
        firefoxOptions.addPreference("intl.accept_language", "en-us");
        FirefoxProfile profile = new FirefoxProfile();
        /*profile.addExtension(new File(""));
        firefoxOptions.setProfile(profile);*/
        driver = new FirefoxDriver(firefoxOptions);
        return driver;
    }
}
