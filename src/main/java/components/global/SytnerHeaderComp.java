package components.global;

import commons.BasePage;
import pages.HomePage;
import pages.PageGeneratorManager;
import pages.SearchPage;

public class SytnerHeaderComp extends BasePage {

    public String SYTNER_HEADER_CENTERED = "xpath=//img[@alt='Sytner Group Logo']/ancestor::div[contains(@class, 'navbar-header--centered')]";
    public static String SYTNER_HEADER = "xpath=//img[@alt='Sytner Group Logo']/ancestor::div[contains(@class, 'Navbar_sui-navbar-fixed')]";
    public String SYTNER_LOGO = "xpath=//img[@alt='Sytner Group Logo']/parent::a";
    public String SEARCH_ICON = "xpath=//button[@title='Open search' and contains(@id, 'desktop')]";
    public String SEARCH_INPUT_ICON = "xpath=//div[contains(@id, 'search-desktop') and @aria-hidden='false']//label[contains(@id,'search-input-desktop')]/following-sibling::*[name()='svg']";
    public String SEARCH_TEXTBOX = "css=input[id*='search-input-desktop']";
    public String SEARCH_BUTTON = "css=button[id*='search-input-desktop']";
    public String SEARCH_CLOSE_ICON = "css=button[title='Close search'][id*='desktop']";
    public String LOCATION_ICON = "css=a[title='Locations']";
    public String OPEN_MENU_ITEM = "xpath=//div[contains(@class, 'navbar-header')]/button[@title='Open Menu']";
    public String CLOSE_MENU_ITEM = "xpath=//div[contains(@class, 'navbar-header')]/button[@title='Close']";
    public String POPUP_MENU_ITEMS = "xpath=//header[contains(@class,'Navbar_is-open')]";
    public String DYNAMIC_TAB_BY_NAME = "xpath=//button[@role='tab' and text()='%s']";
    public String DYNAMIC_MENU_ITEMS_BRAND_BY_NAME = "xpath=//button[@role='tab' and text()='Our Brands' and @aria-selected='true']/ancestor::ul[@role='tablist']/following-sibling::div//a[text()='%s']";
    public String DYNAMIC_MENU_ITEMS_OFFER_BY_NAME = "xpath=//button[@role='tab' and text()='Offers' and @aria-selected='true']/ancestor::ul[@role='tablist']/following-sibling::div//a[text()='%s']";
    public String DYNAMIC_MENU_ITEM_BY_SECTION_NAME = "xpath=//h6[text()='%s']/following-sibling::ul//a[text()='%s']";

    public boolean isSytnerLogoDisplayed() {
        waitForElementVisible(SYTNER_LOGO);
        return isElementDisplayedInDOM(SYTNER_LOGO);
    }

    public boolean isOpenMenuIconDisplayed() {
        waitForElementVisible(OPEN_MENU_ITEM);
        return isElementDisplayedInDOM(OPEN_MENU_ITEM);
    }

    public void openMenuItem() {
        waitForElementClickable(OPEN_MENU_ITEM);
        clickToElement(OPEN_MENU_ITEM);
    }

    public void closeMenuItem() {
        waitForElementClickable(CLOSE_MENU_ITEM);
        clickToElement(CLOSE_MENU_ITEM);
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

    public void clickMenuItemAtTabBrandByName(String brandOfferName) {
        waitForElementClickable(DYNAMIC_MENU_ITEMS_BRAND_BY_NAME, brandOfferName);
        clickToElement(DYNAMIC_MENU_ITEMS_BRAND_BY_NAME, brandOfferName);
        sleepInSecond(5);
    }

    public void clickMenuItemAtTabOfferByName(String brandOfferName) {
        waitForElementClickable(DYNAMIC_MENU_ITEMS_OFFER_BY_NAME, brandOfferName);
        clickToElement(DYNAMIC_MENU_ITEMS_OFFER_BY_NAME, brandOfferName);
        sleepInSecond(5);
    }

    public HomePage clickSytnerIcon() {
        waitForElementClickable(SYTNER_LOGO);
        clickToElement(SYTNER_LOGO);
        return PageGeneratorManager.getHomePage(getDriver());
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
        return isElementUnDisplayed(SYTNER_HEADER_CENTERED);
    }

    public boolean isSytnerHeaderCentered() {
        waitForElementVisible(SYTNER_HEADER_CENTERED);
        return isElementDisplayedInDOM(SYTNER_HEADER_CENTERED);
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

    public void openSearchComponent() {
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

    public void closeSearchComponent() {
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
        return PageGeneratorManager.getSearchPage(getDriver());
    }

    public boolean isSectionMenuItemOpennedOnCurrentPage(String sectionName, String hyperlinkName) {
        waitForElementVisible(DYNAMIC_MENU_ITEM_BY_SECTION_NAME, sectionName, hyperlinkName);
        String target = getAttributeValue(DYNAMIC_MENU_ITEM_BY_SECTION_NAME, "target", sectionName, hyperlinkName);
        if (target.isBlank()) {
            return true;
        }
        return false;
    }

    public boolean isBrandMenuItemOpennedOnCurrentPage(String hyperlinkName) {
        waitForElementVisible(DYNAMIC_MENU_ITEMS_BRAND_BY_NAME, hyperlinkName);
        String target = getAttributeValue(DYNAMIC_MENU_ITEMS_BRAND_BY_NAME, "target", hyperlinkName);
        if (target.isBlank()) {
            return true;
        }
        return false;
    }

    public boolean isOfferMenuItemOpennedOnCurrentPage(String hyperlinkName) {
        waitForElementVisible(DYNAMIC_MENU_ITEMS_OFFER_BY_NAME, hyperlinkName);
        String target = getAttributeValue(DYNAMIC_MENU_ITEMS_OFFER_BY_NAME, "target", hyperlinkName);
        if (target.isBlank()) {
            return true;
        }
        return false;
    }

    public void openBrandPageByName(String brandName) {
        openMenuItem();
        clickMenuItemAtTabBrandByName(brandName);
    }

    public boolean isSytnerHeader_Mode1_Visibility() {
        if (isSearchIconUndisplayed() && isLocationIconUndisplayed() &&
                isSytnerLogoDisplayed() && isOpenMenuIconDisplayed() && isSytnerHeaderCentered()) {
            return true;
        }
        return false;
    }

    public boolean isSytnerHeader_Mode2_Visibility_SearchComponentClosed() {
        if (isSearchInputIconUndisplayed() && isSearchTextboxUndisplayed() && isSearchButtonUndisplayed() && isSearchCloseIconUndisplayed() &&
                isSytnerLogoDisplayed() && isSearchIconDisplayed() && isLocationIconDisplayed() && isOpenMenuIconDisplayed() && isSytnerHeaderUncentered()) {
            return true;
        }
        return false;
    }

    public boolean isSytnerHeader_Mode2_Visibility_SearchComponentOpened() {
        if (isSearchIconUndisplayed() && isLocationIconUndisplayed() &&
                isSearchInputIconDisplayed() && isSearchTextboxDisplayed() && isSearchButtonDisplayed() && isSearchCloseIconDisplayed()) {
            return true;
        }
        return false;
    }

    public boolean isSytnerHeaderOutsideViewport() {
        if(isElementInViewport(SYTNER_HEADER)){
            return false;
        }
        return true;
    }


    public boolean isSytnerHeaderInsideViewport() {
        return isElementInViewport(SYTNER_HEADER);
    }
}
