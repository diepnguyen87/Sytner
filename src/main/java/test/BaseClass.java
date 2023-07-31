package test;

import org.openqa.selenium.WebDriver;

public class BaseClass {
    private WebDriver driver;
    private static int i;

    public BaseClass() {
    }

    public BaseClass(WebDriver driver) {
        this.driver = driver;
        System.out.println(i);
        ++i;
    }
}
