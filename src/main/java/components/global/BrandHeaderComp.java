package components.global;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class BrandHeaderComp extends BasePage {

    private String LOGO_LINK = "css=header[id*='%s'] a[id*='logo-link']";
    private String MENU_LINK = "xpath=//header[contains(@id, '%s')]//nav[contains(@id, 'menu-links')]/a[text()='%s']";
    private String OPEN_MENU_ICON = "css=header[id*='%s'] button[title='Open Menu']";

    public boolean isLogoLinkDisplayed(String brandName) {
        brandName = brandName.toUpperCase();
        waitForElementVisible(LOGO_LINK, brandName);
        return isElementDisplayedInDOM(LOGO_LINK, brandName);
    }

    public boolean isMenuLinksDisplayed(String brandName, String[] menuLinks) {
        brandName = brandName.toUpperCase();
        for (String menuLink : menuLinks) {
            waitForElementVisible(MENU_LINK, brandName, menuLink);
            if (!isElementDisplayedInDOM(MENU_LINK, brandName, menuLink)) return false;
        }
        return true;
    }

    public boolean isOpenMenuIconDisplayed(String brandName) {
        brandName = brandName.toUpperCase();
        waitForElementVisible(OPEN_MENU_ICON, brandName);
        return isElementDisplayedInDOM(OPEN_MENU_ICON, brandName);
    }

    public void isBrandHeaderDisplayed(String brandName, String[] menuLinks) {
        isLogoLinkDisplayed(brandName);
        isMenuLinksDisplayed(brandName, menuLinks);
        isOpenMenuIconDisplayed(brandName);
    }
}
