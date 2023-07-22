package homepage;

import commons.BaseTest;
import commons.GlobalContants;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.HomePageObject;
import pageObjects.PageGeneratorManager;

public class MenuItem_001 extends BaseTest {

    @Parameters({"seleniumComp", "IP", "port", "osName", "osVersion", "browserName"})
    @BeforeClass
    public void beforeClass(String seleniumComp, @Optional String IP, @Optional String port, @Optional String osName, @Optional String osVersion, String browserName) {
        driver = getBrowserDriverAndOpenHomePage(seleniumComp, IP, port, osName, osVersion, browserName);
        System.out.println("Driver: " + driver);
        homePage = PageGeneratorManager.getHomePageObject(driver);
    }

    @Test
    public void MenuItems_001_AboutUs(){
        homePage.acceptAllCookies();
        homePage.openMenuItems();
        homePage.clickOnToAboutUs(companySection, "About Us");
        Assert.assertEquals(homePage.getCurrentURL(driver), appURL + GlobalContants.GC_INSTANCE.getAboutUsSlug());
    }

    private WebDriver driver;
    private HomePageObject homePage;
    private String servicingAtSytnerSection = "ServicingAtSytner";
    private String exploreSection = "Explore";
    private String companySection = "Company";
    private String contactSection = "Contact";



}
