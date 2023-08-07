package pages;

import UI.page.AlpinaPageUI;
import UI.page.BrandPageUI;
import commons.BasePage;
import components.global.BrandHeaderComp;
import components.global.SytnerHeaderComp;
import org.openqa.selenium.WebDriver;

public class BrandPage extends BasePage {

    protected static BrandHeaderComp brandHeaderComp;
    public BrandPage() {
    }

    public BrandPage(WebDriver driver) {
        super(driver);
        brandHeaderComp = BrandHeaderComp.getBrandHeaderComp();
    }

    public static BrandHeaderComp getBrandHeaderComp(){
        return brandHeaderComp;
    }

    public void moveToMainContent() {
        waitForElementVisible(BrandPageUI.MAIN_CONTENT);
        scrollToElementOnDown(BrandPageUI.MAIN_CONTENT);
        sleepInSecond(1);
    }

    public void moveToRangeSection() {
        waitForElementVisible(BrandPageUI.RANGE_TITLE);
        scrollToElementOnDown(BrandPageUI.RANGE_TITLE);
        sleepInSecond(1);
    }

    public void moveToSytnerHeader(){
        waitForElementVisible(SytnerHeaderComp.SYTNER_HEADER);
        scrollToElementOnTop(SytnerHeaderComp.SYTNER_HEADER);
        sleepInSecond(1);
    }


}
