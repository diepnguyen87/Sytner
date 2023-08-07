package maseratiPage;

import commons.BaseTest;
import components.global.BrandHeaderComp;
import data.DataController;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.Maserati.MaseratiPage;
import pages.PageGeneratorManager;

public class MaseratiPage_002_BrandHeader extends BaseTest {

    @BeforeClass
    public void beforeClass() {
        homePage.getSytnerHeaderComp().openBrandPageByName(childBrand);
        maseratiPage = PageGeneratorManager.getMaseratiPage(driver);
        maseratiURL = appURL.concat(DataController.getSlugByBrandName(childBrand));
        brandHeaderComp = MaseratiPage.getBrandHeaderComp();
    }

    @Test
    public void BrandHeader_001_Maserati_Visibility() {
        String[] brandMenuLinks = DataController.brandMenuLinkDataSet(parentBrand);
        brandHeaderComp.isBrandHeaderDisplayed(parentBrand, brandMenuLinks);
    }

    @Test
    public void SytnerHeader_002_Sticky_WhenPageScrollDown() {
        maseratiPage.moveToMainContent();
        Assert.assertTrue(brandHeaderComp.isBrandHeaderSectionDisplayed(parentBrand));
        Assert.assertTrue(brandHeaderComp.isSticky(parentBrand));
    }

    private MaseratiPage maseratiPage;
    private BrandHeaderComp brandHeaderComp;
    private String maseratiURL;
    private String parentBrand = "Maserati";
    private String childBrand = "Maserati";
}
