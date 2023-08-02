package test;

import org.openqa.selenium.WebDriver;

public class LoginPage extends BaseClass {

    public LoginPage(WebDriver driver, int num) {
        super(driver, num);
        System.out.println("Login page: " + this);
    }
}
