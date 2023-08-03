package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        LoginPage loginPage = new LoginPage(driver);
        System.out.println("login page i = " + BaseClass.i);
        BaseClass.i = 99;
        RegisterPage registerPage = new RegisterPage(driver);
        System.out.println("Register page i = " + BaseClass.i);
    }
}
