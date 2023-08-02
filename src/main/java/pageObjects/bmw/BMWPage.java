package pageObjects.bmw;

import commons.BasePage;
import components.ComponentGeneratorManager;
import components.global.BrandHeaderComp;
import org.openqa.selenium.WebDriver;

public class BMWPage extends BasePage {
    protected BrandHeaderComp brandHeader;

    public BMWPage() {

    }

    public BMWPage(WebDriver driver) {
        super(driver);
        brandHeader = ComponentGeneratorManager.getBrandHeaderComp(driver);
    }

    public void isBrandHeaderDisplayed(String brandName, String[] menuLinks) {
        brandHeader.isLogoLinkDisplayed(brandName);
        brandHeader.isMenuLinksDisplayed(brandName, menuLinks);
        brandHeader.isOpenMenuIconDisplayed(brandName);
    }

    public boolean isSytnerLogoDisplayed() {
        return getSytnerHeaderComp().isSytnerLogoDisplayed();
    }

    public boolean isSearchIconDisplayed() {
        return getSytnerHeaderComp().isSearchIconDisplayed();
    }

    public boolean isLocationIconDisplayed() {
        return getSytnerHeaderComp().isLocationIconDisplayed();
    }

    public boolean isOpenMenuIconDisplayed(String open_menu) {
        return getSytnerHeaderComp().isOpenMenuIconDisplayed(open_menu);
    }

    public boolean isSytnerHeaderUncentered() {
        return getSytnerHeaderComp().isSytnerHeaderUncentered();
    }
}
