package alpinaPage;

import commons.BaseTest;
import data.DataController;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.PageGeneratorManager;
import pageObjects.bmw.AlpinaPage;

public class Header_001 extends BaseTest {

    @BeforeClass
    public void beforeClass(){
        homePage.openBrandPageByName("Alpina");
        alpinaPage = PageGeneratorManager.getAlpinaPage(driver);
    }

    @Test
    public void Header_001_Sytner_Visibility() {
        Assert.assertTrue(alpinaPage.isSytnerLogoDisplayed());
        Assert.assertTrue(alpinaPage.isSearchIconDisplayed());
        Assert.assertTrue(alpinaPage.isLocationIconDisplayed());
        Assert.assertTrue(alpinaPage.isOpenMenuIconDisplayed("Open Menu"));

        Assert.assertTrue(alpinaPage.isSytnerHeaderUncentered());
    }

    @Test
    public void Header_002_Alpina_Visibility() {
        String brandName = "BMW";
        String[] brandMenuLinks = DataController.brandMenuLinkDataSet(brandName);
        alpinaPage.isBrandHeaderDisplayed(brandName, brandMenuLinks);
    }

    private AlpinaPage alpinaPage;
}
