package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {

    public static BasePage getBasePage(WebDriver driver){
        return new BasePage(driver);
    }

    public static HomePageObject getHomePageObject(WebDriver driver){
        return new HomePageObject(driver);
    }

    public static AboutUsPageObject getAboutUsPageObject(WebDriver driver){
        return new AboutUsPageObject(driver);
    }

    public static NewsPageObject getNewsPageObject(WebDriver driver){
        return new NewsPageObject(driver);
    }

    public static CareersPageObject getCareersPageObject(WebDriver driver){
        return new CareersPageObject(driver);
    }

    public static CustomerServicesPageObject getCustomerServicesPageObject(WebDriver driver){
        return new CustomerServicesPageObject(driver);
    }

    public static SearchPageObject getSearchPageObject(WebDriver driver){
        return new SearchPageObject(driver);
    }
}
