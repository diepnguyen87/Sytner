package pages.bmw;

import UI.page.AlpinaPageUI;
import components.global.BrandHeaderComp;
import org.openqa.selenium.WebDriver;

public class AlpinaPage extends BMWPage{

    public AlpinaPage(){

    }
    public AlpinaPage(WebDriver driver){
        super(driver);
    }

    public BrandHeaderComp getBrandHeaderComp(){
        return brandHeaderComp;
    }

    public void moveToRangeSection() {
        waitForElementVisible(AlpinaPageUI.RANGE_TITLE);
        scrollToElementOnDown(AlpinaPageUI.RANGE_TITLE);
        sleepInSecond(1);
    }
}
