package components.global;

import commons.BasePage;
import components.ComponentGeneratorManager;

public class BrandHeaderComp extends BasePage {

    private static ThreadLocal<BrandHeaderComp> tbrandHeader = new ThreadLocal<>();
    public static String DYNAMIC_BRAND_HEADER = "id=franchise-nav-%s";
    public static String DYNAMIC_LOGO_LINK_BY_BRAND_NAME = "css=header[id*='%s'] a[id*='logo-link']";
    public static String DYNAMIC_MENU_LINK_BY_BRAND_NAME = "xpath=//header[contains(@id, '%s')]//nav[contains(@id, 'menu-links')]/a[text()='%s']";
    public static String DYNAMIC_OPEN_MENU_ICON_BY_BRAND_NAME = "css=header[id*='%s'] button[title='Open Menu']";

    public static BrandHeaderComp getBrandHeaderComp(){
        if(tbrandHeader.get() == null){
            tbrandHeader.set(ComponentGeneratorManager.getBrandHeaderComp());
        }
        return tbrandHeader.get();
    }

    public boolean isLogoLinkDisplayed(String brandName) {
        brandName = brandName.toUpperCase();
        waitForElementVisible(DYNAMIC_LOGO_LINK_BY_BRAND_NAME, brandName);
        return isElementDisplayedInDOM(DYNAMIC_LOGO_LINK_BY_BRAND_NAME, brandName);
    }

    public boolean isMenuLinksDisplayed(String brandName, String[] menuLinks) {
        brandName = brandName.toUpperCase();
        for (String menuLink : menuLinks) {
            waitForElementVisible(DYNAMIC_MENU_LINK_BY_BRAND_NAME, brandName, menuLink);
            if (!isElementDisplayedInDOM(DYNAMIC_MENU_LINK_BY_BRAND_NAME, brandName, menuLink)) return false;
        }
        return true;
    }

    public boolean isOpenMenuIconDisplayed(String brandName) {
        brandName = brandName.toUpperCase();
        waitForElementVisible(DYNAMIC_OPEN_MENU_ICON_BY_BRAND_NAME, brandName);
        return isElementDisplayedInDOM(DYNAMIC_OPEN_MENU_ICON_BY_BRAND_NAME, brandName);
    }

    public void isBrandHeaderDisplayed(String brandName, String[] menuLinks) {
        isLogoLinkDisplayed(brandName);
        isMenuLinksDisplayed(brandName, menuLinks);
        isOpenMenuIconDisplayed(brandName);
    }

    public boolean isSticky(String brandName) {
        String position = getCssValue(DYNAMIC_BRAND_HEADER, "position", brandName);
        String top = getCssValue(DYNAMIC_BRAND_HEADER, "top", brandName);
        String left = getCssValue(DYNAMIC_BRAND_HEADER, "left", brandName);
        if (position.equalsIgnoreCase("sticky") && top.equalsIgnoreCase("0px") && left.equalsIgnoreCase("0px")) {
            return true;
        }
        return false;
    }

    public boolean isBrandHeaderSectionDisplayed(String brandName) {
        waitForElementVisible(DYNAMIC_BRAND_HEADER, brandName);
        return isElementDisplayedInDOM(DYNAMIC_BRAND_HEADER, brandName);
    }

}
