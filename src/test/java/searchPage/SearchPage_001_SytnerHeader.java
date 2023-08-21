package searchPage;

import commons.BaseTest;
import components.global.SytnerHeaderComp;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.PageGeneratorManager;
import pages.SearchPage;
import utilities.model.Brand;
import utilities.model.MenuItems;
import utilities.model.Offer;

import java.util.Arrays;

public class SearchPage_001_SytnerHeader extends BaseTest {

    @BeforeClass
    public void beforeClass(){
        searchPage = homePage.clickSearchButton();
        searchURL = appURL.concat("search");
        sytnerHeaderComp = searchPage.getSytnerHeaderComp();
    }

    //@Test
    public void SytnerHeader_001_Elements_Visibility_Mode3() {
        Assert.assertTrue(sytnerHeaderComp.isSytnerHeader_Mode3_Visibility_NoSearchComponent());
    }

    //@Test
    public void SytnerHeader_002_Sytner_Logo() {
        homePage = sytnerHeaderComp.clickSytnerIcon();
        Assert.assertEquals(homePage.getCurrentURL(), appURL);
    }

    //@Test
    public void SytnerHeader_003_OpenMenuItems() {
        homePage.navigateToURL(searchURL);
        searchPage = PageGeneratorManager.getSearchPage(driver);

        sytnerHeaderComp.openMenuItem();
        Assert.assertTrue(sytnerHeaderComp.isMenuItemPopupDisplayed());
    }

    //@Test
    public void SytnerHeader_004_CloseMenuItems() {
        sytnerHeaderComp.closeMenuItem();
        Assert.assertTrue(sytnerHeaderComp.isMenuItemPopupUndisplayed());
    }

    //@Test
    public void SytnerHeader_005_MenuItems_Sytner_Logo() {
        sytnerHeaderComp.openMenuItem();
        homePage = sytnerHeaderComp.clickSytnerIcon();
        Assert.assertTrue(sytnerHeaderComp.isMenuItemPopupUndisplayed());
        Assert.assertEquals(homePage.getCurrentURL(), appURL);
    }

    //@Test(dataProvider = "menuItems", dataProviderClass = DataController.class)
    public void SytnerHeader_006_MenuItems_By_Sections(MenuItems menuItems) {
        homePage.navigateToURL(searchURL);

        String sectionName = menuItems.getSectionName();
        MenuItems.Item[] items = menuItems.getItems();
        Arrays.stream(items).forEach(item -> {
            sytnerHeaderComp.openMenuItem();
            Assert.assertTrue(sytnerHeaderComp.isSectionMenuItemOpennedOnCurrentPage(sectionName, item.getName()));

            sytnerHeaderComp.clickMenuItemsBySectionAndName(sectionName, item.getName());
            if (item.getName().equalsIgnoreCase("Careers")) {
                Assert.assertEquals(searchPage.getCurrentURL(), item.getSlug());
            } else {
                Assert.assertEquals(searchPage.getCurrentURL(), appURL + item.getSlug());
            }
            searchPage.navigateToURL(searchURL);
        });
    }

    //@Test(dataProvider = "carBrands", dataProviderClass = DataController.class)
    public void SytnerHeader_007_CarBrands(Brand brand) {
        sytnerHeaderComp.openMenuItem();
        Assert.assertTrue(sytnerHeaderComp.isBrandMenuItemOpennedOnCurrentPage(brand.getName()));

        sytnerHeaderComp.clickMenuItemAtTabBrandByName(brand.getName());
        Assert.assertEquals(searchPage.getCurrentURL(), appURL + brand.getSlug());
        homePage.navigateToURL(searchURL);
    }

    //@Test(dataProvider = "offers", dataProviderClass = DataController.class)
    public void SytnerHeader_008_Offers(Offer offer) {
        sytnerHeaderComp.openMenuItem();
        sytnerHeaderComp.clickTabByName("Offers");
        Assert.assertTrue(sytnerHeaderComp.isOfferMenuItemOpennedOnCurrentPage(offer.getName()));

        sytnerHeaderComp.clickMenuItemAtTabOfferByName(offer.getName());
        Assert.assertEquals(homePage.getCurrentURL(), appURL + offer.getSlug());
        homePage.navigateToURL(searchURL);
        searchPage = PageGeneratorManager.getSearchPage(driver);
    }

    @Test
    public void SytnerHeader_009_InsideViewport_WhenPageScrollDown() {
        searchPage.moveToBottomPage();
        Assert.assertTrue(sytnerHeaderComp.isElmentOnLeftTopMostViewPort());
    }

    @Test
    public void SytnerHeader_010_InsideViewport_WhenPageScrollUp() {
        searchPage.moveOnTopPage();
        Assert.assertTrue(sytnerHeaderComp.isElmentOnLeftTopMostViewPort());
    }

    private SytnerHeaderComp sytnerHeaderComp;
    private SearchPage searchPage;
    private String searchURL;

    private String parentBrand = "Search";
    private String childBrand = "Search";
}
