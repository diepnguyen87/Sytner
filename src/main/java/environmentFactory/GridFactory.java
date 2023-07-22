package environmentFactory;

import browserFactory.*;
import commons.BrowserList;
import commons.GlobalContants;
import org.openqa.selenium.WebDriver;

public class GridFactory {
    private WebDriver driver;
    private String appURL;
    private String browserName;
    private String osName;
    private String IP;
    private String port;
    private BrowserList currentBrowser;

    public GridFactory(String appURL, String osName, String browserName, String IP, String port) {
        this.appURL = appURL;
        this.osName = osName;
        this.browserName = browserName;
        this.IP = IP;
        this.port = port;
    }

    public WebDriver createDriver() {
        if (driver == null) {
            currentBrowser = BrowserList.valueOf(browserName.toUpperCase());
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
}
