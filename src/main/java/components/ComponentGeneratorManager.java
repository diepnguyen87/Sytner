package components;

import components.global.BrandHeaderComp;
import org.openqa.selenium.WebDriver;

public class ComponentGeneratorManager {

    public static BrandHeaderComp getBrandHeaderComp(WebDriver driver){
        return new BrandHeaderComp(driver);
    }
}
