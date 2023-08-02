package test;

import org.openqa.selenium.WebDriver;

public class BaseClass {
    WebDriver driver;
    private int i;

    public BaseClass() {
    }

    public BaseClass(WebDriver driver, int num) {
        this.driver = driver;
        i = num;
        System.out.println(i);
    }
}
