package maseratiPage;

import commons.BaseTest;
import components.global.SytnerHeaderComp;
import data.DataController;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.Maserati.MaseratiPage;
import pages.PageGeneratorManager;
import pages.SearchPage;
import utilities.model.Brand;
import utilities.model.MenuItems;
import utilities.model.Offer;

import java.util.Arrays;

public class MaseratiPage_001_SytnerHeader extends BaseTest {

    @BeforeClass
    public void beforeClass(){
        homePage.getSytnerHeaderComp().openBrandPageByName(childBrand);
        maseratiPage = PageGeneratorManager.getMaseratiPage(driver);
        sytnerHeaderComp = maseratiPage.getSytnerHeaderComp();
        maseratiURL = appURL.concat(DataController.getSlugByBrandName(childBrand));
    }

    @Test
    public void SytnerHeader_001_Elements_Visibility_Mode2() {
        Assert.assertTrue(sytnerHeaderComp.isSytnerHeader_Mode2_Visibility_SearchComponentClosed());
    }

    @Test
    public void SytnerHeader_002_Sytner_Logo() {
        homePage = sytnerHeaderComp.clickSytnerIcon();
        Assert.assertEquals(homePage.getCurrentURL(), appURL);
    }

    @Test
    public void SytnerHeader_003_Mode2_Open_Search_Components() {
        homePage.navigateToURL(maseratiURL);
        sytnerHeaderComp.openSearchComponent();
        Assert.assertTrue(sytnerHeaderComp.isSytnerHeader_Mode2_Visibility_SearchComponentOpened());
        Assert.assertEquals(sytnerHeaderComp.getSearchPlaceHolder(), "Search for your next vehicle");
    }

    @Test
    public void SytnerHeader_004_Mode2_Close_Search_Components() {
        sytnerHeaderComp.inputSearchTextbox("search value");
        sytnerHeaderComp.closeSearchComponent();
        Assert.assertTrue(sytnerHeaderComp.isSytnerHeader_Mode2_Visibility_SearchComponentClosed());

        sytnerHeaderComp.openSearchComponent();
        Assert.assertEquals(sytnerHeaderComp.getSearchValue(), "");
    }

    @Test
    public void SytnerHeader_005_Mode2_Search() {
        String keyword = "Ferrari 812";
        sytnerHeaderComp.inputSearchTextbox(keyword);
        searchPage = sytnerHeaderComp.clickSearchBtn();

        Assert.assertTrue(searchPage.getCurrentURL().contains("https://www.sytner.co.uk/search?"));
        Assert.assertTrue(searchPage.isFilterByKeywordDisplayed(keyword));
        Assert.assertEquals(searchPage.getSearchValue(), keyword);
    }

    @Test
    public void SytnerHeader_006_OpenMenuItems() {
        homePage.navigateToURL(maseratiURL);
        maseratiPage = PageGeneratorManager.getMaseratiPage(driver);

        sytnerHeaderComp.openMenuItem();
        Assert.assertTrue(sytnerHeaderComp.isMenuItemPopupDisplayed());
    }

    @Test
    public void SytnerHeader_007_CloseMenuItems() {
        sytnerHeaderComp.closeMenuItem();
        Assert.assertTrue(sytnerHeaderComp.isMenuItemPopupUndisplayed());
    }

    @Test
    public void SytnerHeader_008_MenuItems_Sytner_Logo() {
        sytnerHeaderComp.openMenuItem();
        homePage = sytnerHeaderComp.clickSytnerIcon();
        Assert.assertTrue(sytnerHeaderComp.isMenuItemPopupUndisplayed());
        Assert.assertEquals(homePage.getCurrentURL(), appURL);
    }

    @Test(dataProvider = "menuItems", dataProviderClass = DataController.class)
    public void SytnerHeader_009_MenuItems_By_Sections(MenuItems menuItems) {
        homePage.navigateToURL(maseratiURL);

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
            homePage.navigateToURL(maseratiURL);
        });
    }

    @Test(dataProvider = "carBrands", dataProviderClass = DataController.class)
    public void SytnerHeader_010_CarBrands(Brand brand) {
        sytnerHeaderComp.openMenuItem();
        Assert.assertTrue(sytnerHeaderComp.isBrandMenuItemOpennedOnCurrentPage(brand.getName()));

        sytnerHeaderComp.clickMenuItemAtTabBrandByName(brand.getName());
        Assert.assertEquals(homePage.getCurrentURL(), appURL + brand.getSlug());
        homePage.navigateToURL(maseratiURL);
    }

    @Test(dataProvider = "offers", dataProviderClass = DataController.class)
    public void SytnerHeader_011_Offers(Offer offer) {
        sytnerHeaderComp.openMenuItem();
        sytnerHeaderComp.clickTabByName("Offers");
        Assert.assertTrue(sytnerHeaderComp.isOfferMenuItemOpennedOnCurrentPage(offer.getName()));

        sytnerHeaderComp.clickMenuItemAtTabOfferByName(offer.getName());
        Assert.assertEquals(homePage.getCurrentURL(), appURL + offer.getSlug());
        homePage.navigateToURL(maseratiURL);
        maseratiPage = PageGeneratorManager.getMaseratiPage(driver);
    }

    @Test
    public void SytnerHeader_012_OutsideViewport_WhenPageScrollDown() {
        maseratiPage.moveToMainContent();
        Assert.assertTrue(sytnerHeaderComp.isSytnerHeaderOutsideViewport());
    }

    @Test
    public void SytnerHeader_013_InsideViewport_WhenPageScrollUp() {
        maseratiPage.moveToSytnerHeader();
        Assert.assertTrue(sytnerHeaderComp.isSytnerHeaderInsideViewport());
    }

    private SytnerHeaderComp sytnerHeaderComp;
    private MaseratiPage maseratiPage;
    private SearchPage searchPage;
    private String maseratiURL;

    private String parentBrand = "Maserati";
    private String childBrand = "Maserati";
}
