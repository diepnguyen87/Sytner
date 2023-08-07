package pages.bmw;

import UI.page.BMWPageUI;
import commons.BasePage;
import components.global.BrandHeaderComp;
import components.global.SytnerHeaderComp;
import org.openqa.selenium.WebDriver;

public class BMWPage extends BasePage {

    protected BrandHeaderComp brandHeaderComp;

    public BMWPage() {

    }

    public BMWPage(WebDriver driver) {
        super(driver);
        brandHeaderComp = BrandHeaderComp.getBrandHeaderComp();
    }

    public void moveToMainContent() {
        waitForElementVisible(BMWPageUI.MAIN_CONTENT);
        scrollToElementOnDown(BMWPageUI.MAIN_CONTENT);
        sleepInSecond(1);
    }

    public void moveToSytnerHeader(){
        waitForElementVisible(SytnerHeaderComp.SYTNER_HEADER);
        scrollToElementOnTop(SytnerHeaderComp.SYTNER_HEADER);
        sleepInSecond(1);
    }


}
