package pages.bmw;

import commons.BasePage;
import components.ComponentGeneratorManager;
import components.global.BrandHeaderComp;
import org.openqa.selenium.WebDriver;

public class BMWPage extends BasePage {
    private static ThreadLocal<BrandHeaderComp> tbrandHeader = new ThreadLocal<>();

    public BMWPage() {

    }

    public BMWPage(WebDriver driver) {
        super(driver);
    }

    public BrandHeaderComp getBrandHeaderComp(){
        if(tbrandHeader.get() == null){
            tbrandHeader.set(ComponentGeneratorManager.getBrandHeaderComp());
        }
        return tbrandHeader.get();
    }
}
