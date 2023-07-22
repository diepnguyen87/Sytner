package browserFactory;

import commons.GlobalContants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FireFoxHeadlessDriverManager extends BrowserFactory{

    private WebDriver driver;

    @Override
    public WebDriver getBrowserDriver() {
        FirefoxOptions firefoxHeadlessOptions = new FirefoxOptions();
        firefoxHeadlessOptions.addArguments("--headless");
        firefoxHeadlessOptions.addArguments("window-size=1920x1080");
        //firefoxHeadlessOptions.addArguments("-private");

        firefoxHeadlessOptions.addPreference("browser.download.folderList", 2);
        firefoxHeadlessOptions.addPreference("browser.download.dir", GlobalContants.DOWNLOAD_PATH);
        firefoxHeadlessOptions.addPreference("browser.download.userDownloadDir", true);
        firefoxHeadlessOptions.addPreference("browser.helperApps.neverAsk.saveToDisk", "multipart/x-zip" +
                        ",application/zip,application/x-zip-compressed,application/x-compressed" +
                        ",application/pdf,application/excel,application/vnd.ms-excel,application/x-excel,application/x-msexcel,application/octet-stream,application/msword,application/csv" +
                        ",text/csv,text/html,text/plain" +
                        ",image/png,image/jpeg,");
        firefoxHeadlessOptions.addPreference("pdfjs.disabled", true);
        firefoxHeadlessOptions.addPreference("intl.accept_language", "en-us");
        driver = new FirefoxDriver(firefoxHeadlessOptions);
        return driver;
    }
}
