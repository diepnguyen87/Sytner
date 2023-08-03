package alpinaPage;

import commons.BaseTest;
import components.global.BrandHeaderComp;
import data.DataController;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.PageGeneratorManager;
import pages.bmw.AlpinaPage;

public class AlpinaPage_002_BrandHeader extends BaseTest {

    @BeforeClass
    public void beforeClass(){
        homePage.getSytnerHeaderComp().openBrandPageByName(brandName);
        alpinaPage = PageGeneratorManager.getAlpinaPage(driver);
        alpinaURL = appURL.concat(DataController.getSlugByBrandName(brandName));
        brandHeaderComp = alpinaPage.getBrandHeaderComp();
    }

    @Test
    public void Header_012_Alpina_Visibility() {
        String brandName = "BMW";
        String[] brandMenuLinks = DataController.brandMenuLinkDataSet(brandName);
        brandHeaderComp.isBrandHeaderDisplayed(brandName, brandMenuLinks);
    }

    private AlpinaPage alpinaPage;
    private BrandHeaderComp brandHeaderComp;
    private String alpinaURL;
    private String brandName = "Alpina";
}
