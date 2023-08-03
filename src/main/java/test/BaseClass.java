package test;

import org.openqa.selenium.WebDriver;

public class BaseClass {
    WebDriver driver;
    protected static int i;

    public BaseClass() {
    }

    public BaseClass(WebDriver driver) {
        this.driver = driver;
    }
}
