package pages;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pages.Maserati.MaseratiPage;
import pages.bmw.AlpinaPage;

public class PageGeneratorManager {

    public static BasePage getBasePage(WebDriver driver){
        return new BasePage(driver);
    }

    public static HomePage getHomePage(WebDriver driver){
        return new HomePage(driver);
    }

    public static AboutUsPage getAboutUsPage(WebDriver driver){
        return new AboutUsPage(driver);
    }

    public static NewsPage getNewsPage(WebDriver driver){
        return new NewsPage(driver);
    }

    public static CareersPage getCareersPage(WebDriver driver){
        return new CareersPage(driver);
    }

    public static CustomerServicesPage getCustomerServicesPage(WebDriver driver){
        return new CustomerServicesPage(driver);
    }
    public static SearchPage getSearchPage(WebDriver driver){
        return new SearchPage(driver);
    }

    public static BrandPage getBrandPage(WebDriver driver){
        return new BrandPage(driver);
    }
    public static AlpinaPage getAlpinaPage(WebDriver driver){
        return new AlpinaPage(driver);
    }
    public static MaseratiPage getMaseratiPage(WebDriver driver){
        return new MaseratiPage(driver);
    }
}
