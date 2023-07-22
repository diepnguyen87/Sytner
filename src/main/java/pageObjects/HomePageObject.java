package pageObjects;

import UI.page.HomePageUI;
import commons.BasePage;
import components.global.SytnerFooterComp;
import components.global.SytnerHeaderComp;
import org.openqa.selenium.WebDriver;

public class HomePageObject extends BasePage {

    private WebDriver driver;
    private SytnerHeaderComp sytnerHeader;
    private SytnerFooterComp sytnerFooter;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void openMenuItems() {
        //sytnerHeader = SytnerHeaderComp.getSytnerHeaderCompInstance(driver);
        sytnerHeader = new SytnerHeaderComp(driver);
        sytnerHeader.openMenuItems("Open Menu");
    }

    public void acceptAllCookies() {
        waitForElementClickable(driver, HomePageUI.ACCEPT_ALL_COOKIES_BUTTON);
        clickToElement(driver, HomePageUI.ACCEPT_ALL_COOKIES_BUTTON);
        waitForElementInVisibleNotInDOM(driver, HomePageUI.COOKIE_SDK);
    }

    public void clickOnToAboutUs(String sectionName, String hyperlinkName) {
        sytnerHeader.clickAboutUsHyperlink(sectionName, hyperlinkName);
    }

}
