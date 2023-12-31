package alpinaPage;

import commons.BaseTest;
import components.global.SytnerHeaderComp;
import data.DataController;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.PageGeneratorManager;
import pages.SearchPage;
import pages.bmw.AlpinaPage;
import utilities.model.Brand;
import utilities.model.MenuItems;
import utilities.model.Offer;

import java.util.Arrays;

public class AlpinaPage_001_SytnerHeader extends BaseTest {

    @BeforeClass
    public void beforeClass() {
        homePage.getSytnerHeaderComp().openBrandPageByName(childBrand);
        alpinaPage = PageGeneratorManager.getAlpinaPage(driver);
        sytnerHeaderComp = alpinaPage.getSytnerHeaderComp();
        alpinaURL = appURL.concat(DataController.getSlugByBrandName(childBrand));
    }

    //@Test
    public void SytnerHeader_001_Elements_Visibility_Mode2() {
        Assert.assertTrue(sytnerHeaderComp.isSytnerHeader_Mode2_Visibility_SearchComponentClosed());
    }

    //@Test
    public void SytnerHeader_002_Sytner_Logo() {
        homePage = sytnerHeaderComp.clickSytnerIcon();
        Assert.assertEquals(homePage.getCurrentURL(), appURL);
    }

    //@Test
    public void SytnerHeader_003_Mode2_Open_Search_Components() {
        homePage.navigateToURL(alpinaURL);
        sytnerHeaderComp.openSearchComponent();
        Assert.assertTrue(sytnerHeaderComp.isSytnerHeader_Mode2_Visibility_SearchComponentOpened());
        Assert.assertEquals(sytnerHeaderComp.getSearchPlaceHolder(), "Search for your next vehicle");
    }

    //@Test
    public void SytnerHeader_004_Mode2_Close_Search_Components() {
        sytnerHeaderComp.inputSearchTextbox("search value");
        sytnerHeaderComp.closeSearchComponent();
        Assert.assertTrue(sytnerHeaderComp.isSytnerHeader_Mode2_Visibility_SearchComponentClosed());

        sytnerHeaderComp.openSearchComponent();
        Assert.assertEquals(sytnerHeaderComp.getSearchValue(), "");
    }

    //@Test
    public void SytnerHeader_005_Mode2_Search() {
        String keyword = "Ferrari 812";
        sytnerHeaderComp.inputSearchTextbox(keyword);
        searchPage = sytnerHeaderComp.clickSearchBtn();

        Assert.assertTrue(searchPage.getCurrentURL().contains("https://www.sytner.co.uk/search?"));
        Assert.assertTrue(searchPage.isFilterByKeywordDisplayed(keyword));
        Assert.assertEquals(searchPage.getSearchValue(), keyword);
    }

    //@Test
    public void SytnerHeader_006_OpenMenuItems() {
        homePage.navigateToURL(alpinaURL);
        alpinaPage = PageGeneratorManager.getAlpinaPage(driver);

        sytnerHeaderComp.openMenuItem();
        Assert.assertTrue(sytnerHeaderComp.isMenuItemPopupDisplayed());
    }

    //@Test
    public void SytnerHeader_007_CloseMenuItems() {
        sytnerHeaderComp.closeMenuItem();
        Assert.assertTrue(sytnerHeaderComp.isMenuItemPopupUndisplayed());
    }

    //@Test
    public void SytnerHeader_008_MenuItems_Sytner_Logo() {
        sytnerHeaderComp.openMenuItem();
        homePage = sytnerHeaderComp.clickSytnerIcon();
        Assert.assertTrue(sytnerHeaderComp.isMenuItemPopupUndisplayed());
        Assert.assertEquals(homePage.getCurrentURL(), appURL);
    }

    //@Test(dataProvider = "menuItems", dataProviderClass = DataController.class)
    public void SytnerHeader_009_MenuItems_By_Sections(MenuItems menuItems) {
        homePage.navigateToURL(alpinaURL);

        String sectionName = menuItems.getSectionName();
        MenuItems.Item[] items = menuItems.getItems();
        Arrays.stream(items).forEach(item -> {
            sytnerHeaderComp.openMenuItem();
            Assert.assertTrue(sytnerHeaderComp.isSectionMenuItemOpennedOnCurrentPage(sectionName, item.getName()));

            sytnerHeaderComp.clickMenuItemsBySectionAndName(sectionName, item.getName());
            if (item.getName().equalsIgnoreCase("Careers")) {
                Assert.assertEquals(homePage.getCurrentURL(), item.getSlug());
            } else {
                Assert.assertEquals(homePage.getCurrentURL(), appURL + item.getSlug());
            }
            homePage.navigateToURL(alpinaURL);
        });
    }

    //@Test(dataProvider = "carBrands", dataProviderClass = DataController.class)
    public void SytnerHeader_010_CarBrands(Brand brand) {
        sytnerHeaderComp.openMenuItem();
        Assert.assertTrue(sytnerHeaderComp.isBrandMenuItemOpennedOnCurrentPage(brand.getName()));

        sytnerHeaderComp.clickMenuItemAtTabBrandByName(brand.getName());
        Assert.assertEquals(homePage.getCurrentURL(), appURL + brand.getSlug());
        homePage.navigateToURL(alpinaURL);
    }

    //@Test(dataProvider = "offers", dataProviderClass = DataController.class)
    public void SytnerHeader_011_Offers(Offer offer) {
        sytnerHeaderComp.openMenuItem();
        sytnerHeaderComp.clickTabByName("Offers");
        Assert.assertTrue(sytnerHeaderComp.isOfferMenuItemOpennedOnCurrentPage(offer.getName()));

        sytnerHeaderComp.clickMenuItemAtTabOfferByName(offer.getName());
        Assert.assertEquals(homePage.getCurrentURL(), appURL + offer.getSlug());
        homePage.navigateToURL(alpinaURL);
        alpinaPage = PageGeneratorManager.getAlpinaPage(driver);
    }

    @Test
    public void SytnerHeader_012_OutsideViewport_WhenPageScrollDown() {
        alpinaPage.moveToMainContent();
        Assert.assertFalse(sytnerHeaderComp.isElmentOnLeftTopMostViewPort());
    }

    @Test
    public void SytnerHeader_013_InsideViewport_WhenPageScrollUp() {
        alpinaPage.moveOnTopPage();
        Assert.assertTrue(sytnerHeaderComp.isElmentOnLeftTopMostViewPort());
    }

    private SytnerHeaderComp sytnerHeaderComp;
    private AlpinaPage alpinaPage;
    private SearchPage searchPage;
    private String alpinaURL;

    private String parentBrand = "BMW";
    private String childBrand = "Alpina";
}
