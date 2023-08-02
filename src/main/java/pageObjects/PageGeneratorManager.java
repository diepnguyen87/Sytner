package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.bmw.AlpinaPage;

public class PageGeneratorManager {

    public static BasePage getBasePage(WebDriver driver){
        return new BasePage(driver);
    }

    public static HomePage getHomePageObject(WebDriver driver){
        return new HomePage(driver);
    }

    public static AboutUsPage getAboutUsPageObject(WebDriver driver){
        return new AboutUsPage(driver);
    }

    public static NewsPage getNewsPageObject(WebDriver driver){
        return new NewsPage(driver);
    }

    public static CareersPage getCareersPageObject(WebDriver driver){
        return new CareersPage(driver);
    }

    public static CustomerServicesPage getCustomerServicesPageObject(WebDriver driver){
        return new CustomerServicesPage(driver);
    }
    public static SearchPage getSearchPageObject(WebDriver driver){
        return new SearchPage(driver);
    }

    public static AlpinaPage getAlpinaPage(WebDriver driver){
        return new AlpinaPage(driver);
    }
}
