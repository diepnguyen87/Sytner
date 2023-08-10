package homePage;

import commons.BaseTest;
import components.global.SytnerHeaderComp;
import data.DataController;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.SearchPage;
import utilities.model.Brand;
import utilities.model.MenuItems;
import utilities.model.Offer;

import java.util.Arrays;

public class HomePage_001_SytnerHeader extends BaseTest {

    @BeforeClass
    public void beforeClass() {
        sytnerHeaderComp = homePage.getSytnerHeaderComp();
    }

    @Test
    public void SytnerHeader_001_Elements_Visibility_Mode1() {
        Assert.assertTrue(sytnerHeaderComp.isSytnerHeader_Mode1_Visibility());
    }

    @Test
    public void SytnerHeader_002_Sytner_Logo() {
        sytnerHeaderComp.clickSytnerIcon();
        Assert.assertEquals(homePage.getCurrentURL(), appURL);
    }

    @Test
    public void SytnerHeader_003_Elements_Visibility_Mode2() {
        homePage.moveToNavigation();
        Assert.assertTrue(sytnerHeaderComp.isSytnerHeader_Mode2_Visibility_SearchComponentClosed());
    }

    @Test
    public void SytnerHeader_004_Mode2_Open_Search_Components() {
        homePage.moveToNavigation();

        sytnerHeaderComp.openSearchComponent();
        Assert.assertTrue(sytnerHeaderComp.isSytnerHeader_Mode2_Visibility_SearchComponentOpened());
        Assert.assertEquals(sytnerHeaderComp.getSearchPlaceHolder(), "Search for your next vehicle");
    }

    @Test
    public void SytnerHeader_005_Mode2_Close_Search_Components() {
        sytnerHeaderComp.inputSearchTextbox("search value");
        sytnerHeaderComp.closeSearchComponent();
        Assert.assertTrue(sytnerHeaderComp.isSytnerHeader_Mode2_Visibility_SearchComponentClosed());

        sytnerHeaderComp.openSearchComponent();
        Assert.assertEquals(sytnerHeaderComp.getSearchValue(), "");
    }

    @Test
    public void SytnerHeader_006_Mode2_Search() {
        String keyword = "Ferrari 812";
        sytnerHeaderComp.inputSearchTextbox(keyword);
        searchPage = sytnerHeaderComp.clickSearchBtn();

        Assert.assertTrue(searchPage.getCurrentURL().contains("https://www.sytner.co.uk/search?"));
        Assert.assertTrue(searchPage.isFilterByKeywordDisplayed(keyword));
        Assert.assertEquals(searchPage.getSearchValue(), keyword);
    }

    @Test
    public void SytnerHeader_007_OpenMenuItems() {
        sytnerHeaderComp.openMenuItem();
        Assert.assertTrue(sytnerHeaderComp.isMenuItemPopupDisplayed());
    }

    @Test
    public void SytnerHeader_008_CloseMenuItems() {
        sytnerHeaderComp.closeMenuItem();
        Assert.assertTrue(sytnerHeaderComp.isMenuItemPopupUndisplayed());
    }

    @Test
    public void SytnerHeader_009_MenuItems_Sytner_Logo() {
        sytnerHeaderComp.openMenuItem();
        sytnerHeaderComp.clickSytnerIcon();
        Assert.assertEquals(homePage.getCurrentURL(), appURL);
        Assert.assertTrue(sytnerHeaderComp.isMenuItemPopupUndisplayed());
    }

    @Test(dataProvider = "menuItems", dataProviderClass = DataController.class)
    public void SytnerHeader_010_MenuItems_By_Sections(MenuItems menuItems) {
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
            homePage.navigateToURL(appURL);
        });
    }

    @Test(dataProvider = "carBrands", dataProviderClass = DataController.class)
    public void SytnerHeader_011_CarBrands(Brand brand) {
        sytnerHeaderComp.openMenuItem();
        Assert.assertTrue(sytnerHeaderComp.isBrandMenuItemOpennedOnCurrentPage(brand.getName()));

        sytnerHeaderComp.clickMenuItemAtTabBrandByName(brand.getName());
        Assert.assertEquals(homePage.getCurrentURL(), appURL + brand.getSlug());
        homePage.navigateToURL(appURL);
    }

    @Test(dataProvider = "offers", dataProviderClass = DataController.class)
    public void SytnerHeader_012_Offers(Offer offer) {
        sytnerHeaderComp.openMenuItem();
        sytnerHeaderComp.clickTabByName("Offers");
        Assert.assertTrue(sytnerHeaderComp.isOfferMenuItemOpennedOnCurrentPage(offer.getName()));

        sytnerHeaderComp.clickMenuItemAtTabOfferByName(offer.getName());
        Assert.assertEquals(homePage.getCurrentURL(), appURL + offer.getSlug());
        homePage.navigateToURL(appURL);
    }

    private SytnerHeaderComp sytnerHeaderComp;
    private SearchPage searchPage;
}
