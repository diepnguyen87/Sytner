package browserFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class SafariDriverManager extends BrowserFactory{

    private WebDriver driver;

    @Override
    public WebDriver getBrowserDriver() {
        driver = new SafariDriver();
        return driver;
    }
}
