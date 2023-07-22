package browserFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class IEDriverManager extends BrowserFactory{

    private WebDriver driver;

    @Override
    public WebDriver getBrowserDriver() {
        InternetExplorerOptions ieOptions = new InternetExplorerOptions();
        driver = new InternetExplorerDriver();
        return driver;
    }
}
