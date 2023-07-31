package pageObjects;

import UI.page.HomePageUI;
import commons.BasePage;
import commons.GlobalContants;
import components.ComponentGeneratorManager;
import components.global.SytnerFooterComp;
import components.global.SytnerHeaderComp;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class HomePageObject extends BasePage {

    public HomePageObject(WebDriver driver) {
        super(driver);
        sytnerHeader = ComponentGeneratorManager.getSytnerHeaderComp(driver);
    }

    public void openMenuItems() {
        sytnerHeader.openCloseMenuItems("Open Menu");
    }

    public void closeMenuItems() {
        sytnerHeader.openCloseMenuItems("Close");
    }

    public void acceptAllCookies() {
        waitForElementClickable(HomePageUI.ACCEPT_ALL_COOKIES_BUTTON);
        clickToElement(HomePageUI.ACCEPT_ALL_COOKIES_BUTTON);
        waitForElementInVisibleNotInDOM(HomePageUI.COOKIE_SDK);
    }

    public void clickMenuItemsBySectionAndName(String sectionName, String hyperlinkName) {
        sytnerHeader.clickMenuItemsBySectionAndName(sectionName, hyperlinkName);
    }

    public void selectTabByName(String tabName) {
        sytnerHeader.clickTabByName(tabName);
    }

    public void clickBrandByName(String brandName) {
        sytnerHeader.clickBrandOfferByName(brandName);
    }

    public void clickOfferByName(String offerName) {
        sytnerHeader.clickBrandOfferByName(offerName);
    }

    public boolean isMenuItemPopupDisplayed() {
        return sytnerHeader.isMenuItemPopupDisplayed();
    }

    public boolean isMenuItemPopupUndisplayed() {
        return sytnerHeader.isMenuItemPopupUndisplayed();
    }

    public void clickSytnerIcon() {
        sytnerHeader.clickSytnerIcon();
    }

    public void moveToNavigation() {
        waitForElementVisible(HomePageUI.HEADER_NAVIGATION);
        scrollToElementOnDown(HomePageUI.HEADER_NAVIGATION);
        sleepInSecond(5);
    }

    public boolean isSearchIconUndisplayed() {
        return sytnerHeader.isSearchIconUndisplayed();
    }

    public boolean isSearchIconDisplayed() {
        return sytnerHeader.isSearchIconDisplayed();
    }

    public boolean isLocationIconUndisplayed() {
        return sytnerHeader.isLocationIconUndisplayed();
    }

    public boolean isLocationIconDisplayed() {
        return sytnerHeader.isLocationIconDisplayed();
    }

    public boolean isNavbarHeaderUncentered() {
        return sytnerHeader.isNavbarHeaderUncentered();
    }

    public boolean isNavbarHeaderCentered() {
        return sytnerHeader.isNavbarHeaderCentered();
    }

    public boolean isSearchInputIconUndisplayed() {
        return sytnerHeader.isSearchInputIconUndisplayed();
    }

    public boolean isSearchTextboxUndisplayed() {
        return sytnerHeader.isSearchTextboxUndisplayed();
    }

    public boolean isSearchButtonUndisplayed() {
        return sytnerHeader.isSearchButtonUndisplayed();
    }

    public boolean isSearchCloseIconUndisplayed() {
        return sytnerHeader.isSearchCloseIconUndisplayed();
    }

    public void clickSearchIcon() {
        sytnerHeader.clickSearchIcon();
    }

    public boolean isSearchInputIconDisplayed() {
        return sytnerHeader.isSearchInputIconDisplayed();
    }

    public boolean isSearchTextboxDisplayed() {
        return sytnerHeader.isSearchTextboxDisplayed();
    }

    public boolean isSearchButtonDisplayed() {
        return sytnerHeader.isSearchButtonDisplayed();
    }

    public boolean isSearchCloseIconDisplayed() {
        return sytnerHeader.isSearchCloseIconDisplayed();
    }

    public void inputSearchTextbox(String searchValue) {
        sytnerHeader.inputSearchTextbox(searchValue);
    }

    public void closeSearchIcon() {
        sytnerHeader.closeSearchIcon();
    }

    public String getSearchPlaceHolder() {
        return sytnerHeader.getSearchPlaceHolder();
    }

    public String getSearchValue() {
        return sytnerHeader.getSearchValue();
    }

    public SearchPageObject clickSearchBtn() {
        return sytnerHeader.clickSearchBtn();
    }

    public boolean isJumpPointDisplayed(String jumpPointName) {
        waitForElementClickable(HomePageUI.DYNAMIC_JUMP_POINT, jumpPointName);
        boolean isDisplayed = isElementDisplayedInDOM(HomePageUI.DYNAMIC_JUMP_POINT, jumpPointName);
        scrollToElementOnTop(HomePageUI.DYNAMIC_JUMP_POINT, jumpPointName);
        sleepInSecond(1);
        return isDisplayed;
    }

    public void clickJumpPointByName(String jumpPointName) {
        clickToElement(HomePageUI.DYNAMIC_JUMP_POINT, jumpPointName);
        sleepInSecond(1);
    }

    public boolean isBrandNameDisplayed(String brandName) {
        waitForElementVisible(HomePageUI.DYNAMIC_BRAND_IMAGE_BY_NAME, brandName);
        return isElementDisplayedInDOM(HomePageUI.DYNAMIC_BRAND_IMAGE_BY_NAME, brandName);
    }

    public boolean isArticleTitleDisplayed(String articleTitle) {
        waitForElementVisible(HomePageUI.DYNAMIC_ARTICLE_TITLE, articleTitle);
        return isElementDisplayedInDOM(HomePageUI.DYNAMIC_ARTICLE_TITLE, articleTitle);
    }

    public String getArticleContent(String articleTitle) {
        waitForElementVisible(HomePageUI.DYNAMIC_ARTICLE_CONTENT, articleTitle);
        return getElementText(HomePageUI.DYNAMIC_ARTICLE_CONTENT, articleTitle);
    }

    public boolean isArticleBtnDisplayed(String articleTitle, String buttonValue) {
        waitForElementVisible(HomePageUI.DYNAMIC_ARTICLE_FIRST_BUTTON, articleTitle, buttonValue);
        return isElementDisplayedInDOM(HomePageUI.DYNAMIC_ARTICLE_FIRST_BUTTON, articleTitle, buttonValue);
    }

    public boolean isDealershipTitleDisplayed() {
        waitForElementVisible(HomePageUI.DEALERSHIP_TITLE);
        return isElementDisplayedInDOM(HomePageUI.DEALERSHIP_TITLE);
    }

    public boolean isPostCodeSearchTextboxDisplayed() {
        waitForElementVisible(HomePageUI.DEALERSHIP_POSTCODE_SEARCH_TEXTBOX);
        return isElementDisplayedInDOM(HomePageUI.DEALERSHIP_POSTCODE_SEARCH_TEXTBOX);
    }

    public String getPlaceHolderPostCodeSearchTextbox() {
        return getAttributeValue(HomePageUI.DEALERSHIP_POSTCODE_SEARCH_TEXTBOX, "placeholder");
    }

    public String getPlaceHolderBrandTextbox() {
        waitForElementVisible(HomePageUI.DEALERSHIP_BRAND_TEXTBOX);
        return getAttributeValue(HomePageUI.DEALERSHIP_BRAND_TEXTBOX, "placeholder");
    }

    public void clickBrandListbox() {
        sleepInSecond(1);
        clickToElement(HomePageUI.DEALERSHIP_BRAND_TEXTBOX);
    }

    public boolean listboxContainBrandName(String brandName) {
        return isElementDisplayedInDOM(HomePageUI.DEALERSHIP_BRAND_MENU_LISTBOX, brandName);
    }

    public boolean isAllBrandsPresence(String[] brands) {
        for (String brand : brands) {
            try {
                if (!isElementDisplayedInDOM(HomePageUI.DEALERSHIP_BRAND_MENU_LISTBOX, brand)) {
                    return false;
                }
            } catch (NoSuchElementException ne) {
                System.out.println("[ERROR] The car brand " + brand + " is in " + GlobalContants.BRANDS_DATA_JSON + " is not presence in the brand list");
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public boolean isFindADealershipButtonDisplayed() {
        waitForElementVisible(HomePageUI.FIND_DEALERSHIP_BUTTON);
        return isElementDisplayedInDOM(HomePageUI.FIND_DEALERSHIP_BUTTON);
    }

    public boolean isServiceTitleDisplayed(String serviceTitle) {
        waitForElementVisible(HomePageUI.DYNAMIC_SERVICE_TITLE, serviceTitle);
        return isElementDisplayedInDOM(HomePageUI.DYNAMIC_SERVICE_TITLE, serviceTitle);
    }

    public String getServiceContent(String serviceTitle) {
        waitForElementVisible(HomePageUI.DYNAMIC_SERVICE_CONTENT, serviceTitle);
        return getElementText(HomePageUI.DYNAMIC_SERVICE_CONTENT, serviceTitle);
    }

    public boolean isServiceButtonDisplayed(String serviceTitle, String serviceBtn) {
        waitForElementVisible(HomePageUI.DYNAMIC_SERVICE_TITLE, serviceTitle, serviceBtn);
        return isElementDisplayedInDOM(HomePageUI.DYNAMIC_SERVICE_TITLE, serviceTitle, serviceBtn);
    }

    public String getServiceButtonURL(String serviceTitle, String serviceBtn) {
        waitForElementVisible(HomePageUI.DYNAMIC_SERVICE_BUTTON, serviceTitle, serviceBtn);
        return getAttributeValue(HomePageUI.DYNAMIC_SERVICE_BUTTON, "href", serviceTitle, serviceBtn);
    }

    public void moveToSection(String sectionName) {
        waitForElementVisible(HomePageUI.FEATURED_SECTION, sectionName);
        scrollToElementOnDown(HomePageUI.FEATURED_SECTION, sectionName);
    }

    public boolean isHeadingDisplayed(String expectedSubHeading) {
        waitForElementVisible(HomePageUI.DYNAMIC_FEATURED_HEADING, expectedSubHeading);
        return isElementDisplayedInDOM(HomePageUI.DYNAMIC_FEATURED_HEADING, expectedSubHeading);
    }

    public boolean isSubHeadingDisplayed(String expectedSubHeading) {
        try {
            waitForElementVisible(HomePageUI.DYNAMIC_FEATURED_SUB_HEADING, expectedSubHeading);
        } catch (TimeoutException te) {
            waitForElementVisible(HomePageUI.NEXT_SLIDE);
            clickToElement(HomePageUI.NEXT_SLIDE);
            sleepInSecond(1);
        }
        return isElementDisplayedInDOM(HomePageUI.DYNAMIC_FEATURED_SUB_HEADING, expectedSubHeading);
    }

    public boolean isOfferDisplayed(String expectedSubHeading, String offer) {
        waitForElementVisible(HomePageUI.DYNAMIC_FEATURED_OFFER, expectedSubHeading, offer);
        return isElementDisplayedInDOM(HomePageUI.DYNAMIC_FEATURED_OFFER, expectedSubHeading, offer);
    }

    public boolean isImageDisplayed(String expectedSubHeading) {
        waitForElementVisible(HomePageUI.DYNAMIC_FEATURED_IMAGE, expectedSubHeading);
        return isElementDisplayedInDOM(HomePageUI.DYNAMIC_FEATURED_IMAGE, expectedSubHeading);
    }

    public boolean isOfferLinkDisplayed(String expectedSubHeading) {
        waitForElementVisible(HomePageUI.DYNAMIC_LINK_TO_OFFER, expectedSubHeading);
        return isElementDisplayedInDOM(HomePageUI.DYNAMIC_LINK_TO_OFFER, expectedSubHeading);
    }

    public BasePage openSubHeadingOnNewTab(String expectedSubHeading) {
        sleepInSecond(1);
        openPageOnNewTab(HomePageUI.DYNAMIC_FEATURED_SUB_HEADING, expectedSubHeading);
        switchToWindowByTitle(expectedSubHeading);
        return PageGeneratorManager.getBasePage(driver);
    }

    public BasePage openHeadingOnNewTab(String expectedSubHeading) {
        sleepInSecond(1);
        openPageOnNewTab(HomePageUI.DYNAMIC_FEATURED_HEADING, expectedSubHeading);
        switchToWindowByTitle(expectedSubHeading);
        return PageGeneratorManager.getBasePage(driver);
    }

    public BasePage openImageOnNewTab(String expectedSubHeading) {
        sleepInSecond(1);
        openPageOnNewTab(HomePageUI.DYNAMIC_FEATURED_IMAGE, expectedSubHeading);
        switchToWindowByTitle(expectedSubHeading);
        return PageGeneratorManager.getBasePage(driver);
    }

    public BasePage openOfferOnNewTab(String expectedSubHeading, String expectedOffer) {
        sleepInSecond(1);
        openPageOnNewTab(HomePageUI.DYNAMIC_FEATURED_OFFER, expectedSubHeading, expectedOffer);
        switchToWindowByTitle(expectedSubHeading);
        return PageGeneratorManager.getBasePage(driver);
    }
    public BasePage openOfferLinkOnNewTab(String expectedSubHeading) {
        sleepInSecond(1);
        openPageOnNewTab(HomePageUI.DYNAMIC_LINK_TO_OFFER, expectedSubHeading);
        switchToWindowByTitle(expectedSubHeading);
        return PageGeneratorManager.getBasePage(driver);
    }

    public void openAlpinaPage() {
        openMenuItems();
    }
}
