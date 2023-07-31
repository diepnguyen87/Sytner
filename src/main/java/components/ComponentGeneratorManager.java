package components;

import components.global.SytnerHeaderComp;
import org.openqa.selenium.WebDriver;

public class ComponentGeneratorManager {

    public static SytnerHeaderComp getSytnerHeaderComp(WebDriver driver){
        return new SytnerHeaderComp(driver);
    }
}
