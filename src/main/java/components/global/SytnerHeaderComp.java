package components.global;

import UI.comp.SytnerHeaderCompUI;
import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class SytnerHeaderComp extends BasePage {
    private WebDriver driver;
    public static SytnerHeaderComp sytnerHeaderComp;

    public SytnerHeaderComp(WebDriver driver) {
        this.driver = driver;
    }

    public static SytnerHeaderComp getSytnerHeaderCompInstance(WebDriver driver){
        if(sytnerHeaderComp == null){
            sytnerHeaderComp = new SytnerHeaderComp(driver);
        }
        return sytnerHeaderComp;
    }

    public void openMenuItems(String iconName) {
        waitForElementClickable(driver, SytnerHeaderCompUI.MENU_ITEMS_OPEN_CLOSE_ICON, iconName);
        clickToElement(driver, SytnerHeaderCompUI.MENU_ITEMS_OPEN_CLOSE_ICON, iconName);
    }

    public void clickAboutUsHyperlink(String sectionName, String hyperlinkName) {
        waitForElementClickable(driver, SytnerHeaderCompUI.DYNAMIC_MENU_ITEM_BY_SECTION_NAME, sectionName, hyperlinkName);
        clickToElement(driver, SytnerHeaderCompUI.DYNAMIC_MENU_ITEM_BY_SECTION_NAME, sectionName, hyperlinkName);
    }
}
