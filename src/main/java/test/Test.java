package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {

    public static void main(String[] args) {
        ThreadLocal<BaseClass> tBase = ThreadLocal.withInitial(() -> {
            WebDriver driver = new ChromeDriver();
            BaseClass baseClass = new BaseClass();
            return baseClass;
        });

        WebDriver driver = tBase.get().driver;
        LoginPage loginPage = new LoginPage(driver, 10);
        RegisterPage registerPage = new RegisterPage(driver, 90);

    }
}
