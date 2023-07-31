package components.global;

import UI.comp.SytnerHeaderCompUI;
import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGeneratorManager;
import pageObjects.SearchPageObject;

public class SytnerHeaderComp extends BasePage {
    private WebDriver driver;
    public static SytnerHeaderComp sytnerHeaderComp;

    public SytnerHeaderComp(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void openCloseMenuItems(String iconName) {
        waitForElementClickable(SytnerHeaderCompUI.MENU_ITEMS_OPEN_CLOSE_ICON, iconName);
        clickToElement(SytnerHeaderCompUI.MENU_ITEMS_OPEN_CLOSE_ICON, iconName);
    }

    public boolean isMenuItemPopupDisplayed() {
        waitForElementVisible(SytnerHeaderCompUI.MENU_ITEMS_POPUP);
        return isElementDisplayedInDOM(SytnerHeaderCompUI.MENU_ITEMS_POPUP);
    }

    public boolean isMenuItemPopupUndisplayed() {
        return isElementUnDisplayed(SytnerHeaderCompUI.MENU_ITEMS_POPUP);
    }

    public void clickMenuItemsBySectionAndName(String sectionName, String hyperlinkName) {
        waitForElementClickable(SytnerHeaderCompUI.DYNAMIC_MENU_ITEM_BY_SECTION_NAME, sectionName, hyperlinkName);
        clickToElement(SytnerHeaderCompUI.DYNAMIC_MENU_ITEM_BY_SECTION_NAME, sectionName, hyperlinkName);
        sleepInSecond(5);
    }

    public void clickTabByName(String tabName) {
        waitForElementClickable(SytnerHeaderCompUI.DYNAMIC_TAB, tabName);
        clickToElement(SytnerHeaderCompUI.DYNAMIC_TAB, tabName);
    }

    public void clickBrandOfferByName(String brandName) {
        waitForElementClickable(SytnerHeaderCompUI.DYNAMIC_BRAND_BY_NAME, brandName);
        clickToElement(SytnerHeaderCompUI.DYNAMIC_BRAND_BY_NAME, brandName);
        sleepInSecond(5);
    }

    public void clickSytnerIcon() {
        waitForElementClickable(SytnerHeaderCompUI.SYTNER_LOGO);
        clickToElement(SytnerHeaderCompUI.SYTNER_LOGO);
    }

    public boolean isSearchIconUndisplayed() {
        return isElementUnDisplayed(SytnerHeaderCompUI.SEARCH_ICON);
    }

    public boolean isSearchIconDisplayed() {
        waitForElementVisible(SytnerHeaderCompUI.SEARCH_ICON);
        return isElementDisplayedInDOM(SytnerHeaderCompUI.SEARCH_ICON);
    }

    public boolean isLocationIconUndisplayed() {
        return isElementUnDisplayed(SytnerHeaderCompUI.LOCATION_ICON);
    }

    public boolean isLocationIconDisplayed() {
        waitForElementVisible(SytnerHeaderCompUI.LOCATION_ICON);
        return isElementDisplayedInDOM(SytnerHeaderCompUI.LOCATION_ICON);
    }

    public boolean isNavbarHeaderUncentered() {
        return isElementUnDisplayed(SytnerHeaderCompUI.NAVBAR_HEADER);
    }

    public boolean isNavbarHeaderCentered() {
        waitForElementVisible(SytnerHeaderCompUI.NAVBAR_HEADER);
        return isElementDisplayedInDOM(SytnerHeaderCompUI.NAVBAR_HEADER);
    }

    public boolean isSearchInputIconUndisplayed() {
        return isElementUnDisplayed(SytnerHeaderCompUI.SEARCH_INPUT_ICON);
    }

    public boolean isSearchTextboxUndisplayed() {
        return isElementUnDisplayed(SytnerHeaderCompUI.SEARCH_TEXTBOX);
    }

    public boolean isSearchButtonUndisplayed() {
        return isElementUnDisplayed(SytnerHeaderCompUI.SEARCH_BUTTON);
    }

    public boolean isSearchCloseIconUndisplayed() {
        return isElementUnDisplayed(SytnerHeaderCompUI.SEARCH_CLOSE_ICON);
    }

    public void clickSearchIcon() {
        waitForElementClickable(SytnerHeaderCompUI.SEARCH_ICON);
        clickToElement(SytnerHeaderCompUI.SEARCH_ICON);
    }

    public boolean isSearchInputIconDisplayed() {
        waitForElementVisible(SytnerHeaderCompUI.SEARCH_INPUT_ICON);
        return isElementDisplayedInDOM(SytnerHeaderCompUI.SEARCH_INPUT_ICON);
    }

    public boolean isSearchTextboxDisplayed() {
        waitForElementVisible(SytnerHeaderCompUI.SEARCH_TEXTBOX);
        return isElementDisplayedInDOM(SytnerHeaderCompUI.SEARCH_TEXTBOX);
    }

    public boolean isSearchButtonDisplayed() {
        waitForElementVisible(SytnerHeaderCompUI.SEARCH_BUTTON);
        return isElementDisplayedInDOM(SytnerHeaderCompUI.SEARCH_BUTTON);
    }

    public boolean isSearchCloseIconDisplayed() {
        waitForElementVisible(SytnerHeaderCompUI.SEARCH_CLOSE_ICON);
        return isElementDisplayedInDOM(SytnerHeaderCompUI.SEARCH_CLOSE_ICON);
    }

    public void inputSearchTextbox(String searchValue) {
        waitForElementVisible(SytnerHeaderCompUI.SEARCH_TEXTBOX);
        sendkeyToElement(SytnerHeaderCompUI.SEARCH_TEXTBOX, searchValue);
        sleepInSecond(1);
    }

    public void closeSearchIcon() {
        waitForElementClickable(SytnerHeaderCompUI.SEARCH_CLOSE_ICON);
        clickToElement(SytnerHeaderCompUI.SEARCH_CLOSE_ICON);
    }

    public String getSearchPlaceHolder() {
        waitForElementVisible(SytnerHeaderCompUI.SEARCH_TEXTBOX);
        return getAttributeValue(SytnerHeaderCompUI.SEARCH_TEXTBOX, "placeholder");
    }

    public String getSearchValue() {
        waitForElementVisible(SytnerHeaderCompUI.SEARCH_TEXTBOX);
        return getAttributeValue(SytnerHeaderCompUI.SEARCH_TEXTBOX, "value");
    }

    public SearchPageObject clickSearchBtn() {
        waitForElementClickable(SytnerHeaderCompUI.SEARCH_BUTTON);
        clickToElement(SytnerHeaderCompUI.SEARCH_BUTTON);
        return PageGeneratorManager.getSearchPageObject(driver);
    }
}
