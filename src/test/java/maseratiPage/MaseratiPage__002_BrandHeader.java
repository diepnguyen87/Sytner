package maseratiPage;

import commons.BaseTest;
import components.global.BrandHeaderComp;
import data.DataController;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.PageGeneratorManager;
import pages.bmw.AlpinaPage;

public class MaseratiPage__002_BrandHeader extends BaseTest {

    @BeforeClass
    public void beforeClass(){
        homePage.getSytnerHeaderComp().openBrandPageByName(childBrand);
        alpinaPage = PageGeneratorManager.getAlpinaPage(driver);
        alpinaURL = appURL.concat(DataController.getSlugByBrandName(childBrand));
        brandHeaderComp = alpinaPage.getBrandHeaderComp();
    }

    @Test
    public void BrandHeader_001_Alpina_Visibility() {
        String[] brandMenuLinks = DataController.brandMenuLinkDataSet(parentBrand);
        brandHeaderComp.isBrandHeaderDisplayed(parentBrand, brandMenuLinks);
    }

    @Test
    public void SytnerHeader_002_Sticky_WhenPageScroll(){
        Assert.assertTrue(brandHeaderComp.isSticky(parentBrand));
    }

    private AlpinaPage alpinaPage;
    private BrandHeaderComp brandHeaderComp;
    private String alpinaURL;
    private String parentBrand = "BMW";
    private String childBrand = "Alpina";
}
