package components.global;

import commons.BasePage;
import pageObjects.PageGeneratorManager;
import pageObjects.SearchPage;

public class SytnerHeaderComp extends BasePage {

    private String SYTNER_HEADER = "xpath=//img[@alt='Sytner Group Logo']/ancestor::div[contains(@class, 'navbar-header--centered')]";
    private String SYTNER_LOGO = "xpath=//img[@alt='Sytner Group Logo']/parent::a";
    private String SEARCH_ICON = "xpath=//button[@title='Open search' and contains(@id, 'desktop')]";
    private String SEARCH_INPUT_ICON = "xpath=//div[contains(@id, 'search-desktop') and @aria-hidden='false']//label[contains(@id,'search-input-desktop')]/following-sibling::*[name()='svg']";
    private String SEARCH_TEXTBOX = "css=input[id*='search-input-desktop']";
    private String SEARCH_BUTTON = "css=button[id*='search-input-desktop']";
    private String SEARCH_CLOSE_ICON = "css=button[title='Close search'][id*='desktop']";
    private String LOCATION_ICON = "css=a[title='Locations']";
    private String DYNAMIC_OPEN_CLOSE_ICON_MENU_ITEMS = "xpath=//div[contains(@class, 'navbar-header')]/button[@title='%s']";
    private String POPUP_MENU_ITEMS = "xpath=//header[contains(@class,'Navbar_is-open')]";
    private String DYNAMIC_TAB_BY_NAME = "xpath=//button[@role='tab' and text()='%s']";
    private String DYNAMIC_MENU_ITEMS_BRAND_OFFER_BY_NAME = "xpath=//ul[@role='tablist']/following-sibling::div//a[text()='%s']";
    //private String DYNAMIC_SECTION_NAME_MENU_ITEMS = "xpath=//h6[text()='%s']";
    private String DYNAMIC_MENU_ITEM_BY_SECTION_NAME = "xpath=//h6[text()='%s']/following-sibling::ul//a[text()='%s']";

    public boolean isSytnerLogoDisplayed() {
        waitForElementVisible(SYTNER_LOGO);
        return isElementDisplayedInDOM(SYTNER_LOGO);
    }

    public boolean isOpenMenuIconDisplayed(String buttonTitle) {
        waitForElementVisible(DYNAMIC_OPEN_CLOSE_ICON_MENU_ITEMS, buttonTitle);
        return isElementDisplayedInDOM(DYNAMIC_OPEN_CLOSE_ICON_MENU_ITEMS, buttonTitle);
    }

    public void openCloseMenuItems(String iconName) {
        waitForElementClickable(DYNAMIC_OPEN_CLOSE_ICON_MENU_ITEMS, iconName);
        clickToElement(DYNAMIC_OPEN_CLOSE_ICON_MENU_ITEMS, iconName);
    }

    public boolean isMenuItemPopupDisplayed() {
        waitForElementVisible(POPUP_MENU_ITEMS);
        return isElementDisplayedInDOM(POPUP_MENU_ITEMS);
    }

    public boolean isMenuItemPopupUndisplayed() {
        return isElementUnDisplayed(POPUP_MENU_ITEMS);
    }

    public void clickMenuItemsBySectionAndName(String sectionName, String hyperlinkName) {
        waitForElementClickable(DYNAMIC_MENU_ITEM_BY_SECTION_NAME, sectionName, hyperlinkName);
        clickToElement(DYNAMIC_MENU_ITEM_BY_SECTION_NAME, sectionName, hyperlinkName);
        sleepInSecond(5);
    }

    public void clickTabByName(String tabName) {
        waitForElementClickable(DYNAMIC_TAB_BY_NAME, tabName);
        clickToElement(DYNAMIC_TAB_BY_NAME, tabName);
    }

    public void clickMenuItemAtTabBrandOfferByName(String brandOfferName) {
        waitForElementClickable(DYNAMIC_MENU_ITEMS_BRAND_OFFER_BY_NAME, brandOfferName);
        clickToElement(DYNAMIC_MENU_ITEMS_BRAND_OFFER_BY_NAME, brandOfferName);
        sleepInSecond(5);
    }

    public void clickSytnerIcon() {
        waitForElementClickable(SYTNER_LOGO);
        clickToElement(SYTNER_LOGO);

    }

    public boolean isSearchIconUndisplayed() {
        return isElementUnDisplayed(SEARCH_ICON);
    }

    public boolean isSearchIconDisplayed() {
        waitForElementVisible(SEARCH_ICON);
        return isElementDisplayedInDOM(SEARCH_ICON);
    }

    public boolean isLocationIconUndisplayed() {
        return isElementUnDisplayed(LOCATION_ICON);
    }

    public boolean isLocationIconDisplayed() {
        waitForElementVisible(LOCATION_ICON);
        return isElementDisplayedInDOM(LOCATION_ICON);
    }

    public boolean isSytnerHeaderUncentered() {
        return isElementUnDisplayed(SYTNER_HEADER);
    }

    public boolean isSytnerHeaderCentered() {
        waitForElementVisible(SYTNER_HEADER);
        return isElementDisplayedInDOM(SYTNER_HEADER);
    }

    public boolean isSearchInputIconUndisplayed() {
        return isElementUnDisplayed(SEARCH_INPUT_ICON);
    }

    public boolean isSearchTextboxUndisplayed() {
        return isElementUnDisplayed(SEARCH_TEXTBOX);
    }

    public boolean isSearchButtonUndisplayed() {
        return isElementUnDisplayed(SEARCH_BUTTON);
    }

    public boolean isSearchCloseIconUndisplayed() {
        return isElementUnDisplayed(SEARCH_CLOSE_ICON);
    }

    public void clickSearchIcon() {
        waitForElementClickable(SEARCH_ICON);
        clickToElement(SEARCH_ICON);
    }

    public boolean isSearchInputIconDisplayed() {
        waitForElementVisible(SEARCH_INPUT_ICON);
        return isElementDisplayedInDOM(SEARCH_INPUT_ICON);
    }

    public boolean isSearchTextboxDisplayed() {
        waitForElementVisible(SEARCH_TEXTBOX);
        return isElementDisplayedInDOM(SEARCH_TEXTBOX);
    }

    public boolean isSearchButtonDisplayed() {
        waitForElementVisible(SEARCH_BUTTON);
        return isElementDisplayedInDOM(SEARCH_BUTTON);
    }

    public boolean isSearchCloseIconDisplayed() {
        waitForElementVisible(SEARCH_CLOSE_ICON);
        return isElementDisplayedInDOM(SEARCH_CLOSE_ICON);
    }

    public void inputSearchTextbox(String searchValue) {
        waitForElementVisible(SEARCH_TEXTBOX);
        sendkeyToElement(SEARCH_TEXTBOX, searchValue);
        sleepInSecond(1);
    }

    public void closeSearchIcon() {
        waitForElementClickable(SEARCH_CLOSE_ICON);
        clickToElement(SEARCH_CLOSE_ICON);
    }

    public String getSearchPlaceHolder() {
        waitForElementVisible(SEARCH_TEXTBOX);
        return getAttributeValue(SEARCH_TEXTBOX, "placeholder");
    }

    public String getSearchValue() {
        waitForElementVisible(SEARCH_TEXTBOX);
        return getAttributeValue(SEARCH_TEXTBOX, "value");
    }

    public SearchPage clickSearchBtn() {
        waitForElementClickable(SEARCH_BUTTON);
        clickToElement(SEARCH_BUTTON);
        return PageGeneratorManager.getSearchPageObject(getDriver());
    }

    public String getMenuItemTarget(String sectionName, String hyperlinkName) {
        waitForElementVisible(DYNAMIC_MENU_ITEM_BY_SECTION_NAME, sectionName, hyperlinkName);
        return getAttributeValue(DYNAMIC_MENU_ITEM_BY_SECTION_NAME, "target", sectionName, hyperlinkName);
    }

    public String isBrandOfferMenuItemOpennedOnCurrentPage(String hyperlinkName) {
        waitForElementVisible(DYNAMIC_MENU_ITEMS_BRAND_OFFER_BY_NAME, hyperlinkName);
        return getAttributeValue(DYNAMIC_MENU_ITEMS_BRAND_OFFER_BY_NAME, "target", hyperlinkName);
    }
}
