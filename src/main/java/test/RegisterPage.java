package test;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BaseClass {

    public RegisterPage(WebDriver driver, int num) {
        super(driver, num);
        System.out.println("Register page: " + this);
    }
}
